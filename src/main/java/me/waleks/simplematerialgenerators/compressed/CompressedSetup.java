package me.waleks.simplematerialgenerators.compressed;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import me.waleks.simplematerialgenerators.SMGItems;
import me.waleks.simplematerialgenerators.SimpleMaterialGenerators;
import me.waleks.simplematerialgenerators.guide.GroupListItemGroup;
import me.waleks.simplematerialgenerators.guide.HiddenItemGroup;
import me.waleks.simplematerialgenerators.items.MaterialGenerator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Registers the compressed generators (several generators combined into one), configured by {@code compressed.yml}.
 * <p>
 * Every generator gets a compressed version per multiplier. The first enabled multiplier is crafted from
 * the original generator in the Compressor, every next one from 2 of the previous multiplier.
 * Only the highest multiplier is listed in the guide, the lower ones are in a hidden group.
 */
public final class CompressedSetup {

    private static final int[] MULTIPLIERS = {8, 16, 32, 64};
    private static final String FILE_NAME = "compressed.yml";
    private static final String GENERATORS_SECTION = "generators";

    // the slot of the "Compressed Items" button in the addon's guide menu
    private static final int COMPRESSED_ITEMS_SLOT = 40;
    // the head texture of the "Compressed Items" icon (Mybezo's skin)
    private static final String COMPRESSED_ITEMS_TEXTURE = "82600941fab88efc41bb132d873d3b5c5f35f7720ebd9375476c1a18955bb03e";

    private CompressedSetup() {}

    public static void setup(@Nonnull SimpleMaterialGenerators plugin) {
        List<MaterialGenerator> generators = getGenerators(plugin);
        Config cfg = loadConfig(plugin, generators);

        if (!cfg.getBoolean("enabled")) {
            return;
        }

        // a multiplier is only available when all the lower multipliers are enabled
        List<Integer> multipliers = new ArrayList<>();
        for (int multiplier : MULTIPLIERS) {
            if (!cfg.getBoolean("multipliers.x" + multiplier)) {
                break;
            }
            multipliers.add(multiplier);
        }
        if (multipliers.isEmpty()) {
            return;
        }

        ItemGroup generatorsGroup = new HiddenItemGroup(
            new NamespacedKey(plugin, "compressed_generators"),
            new CustomItemStack(Material.SMOOTH_STONE, "&9Compressed Generator")
        );
        ItemGroup hiddenGroup = new HiddenItemGroup(
            new NamespacedKey(plugin, "compressed_hidden"),
            new CustomItemStack(Material.BARRIER, "&9Compressed Generator")
        );

        int count = 0;
        int highest = multipliers.get(multipliers.size() - 1);

        for (MaterialGenerator generator : generators) {
            if (!cfg.getBoolean(GENERATORS_SECTION + "." + generator.getId()) || generator.isDisabled()) {
                continue;
            }

            ItemStack previous = generator.getItem();
            int previousMultiplier = 1;

            for (int multiplier : multipliers) {
                SlimefunItemStack item = createItemStack(generator, multiplier);
                ItemStack[] recipe = new ItemStack[9];
                recipe[0] = previous.clone();
                recipe[0].setAmount(multiplier / previousMultiplier);

                // only the highest multiplier is listed in the guide, the lower ones are still craftable
                ItemGroup group = multiplier == highest ? generatorsGroup : hiddenGroup;
                new MaterialGenerator(group, item, RecipeType.COMPRESSOR, recipe, generator, multiplier).register(plugin);
                count++;

                previous = item;
                previousMultiplier = multiplier;
            }
        }

        if (count == 0) {
            return;
        }

        GroupListItemGroup compressedItems = new GroupListItemGroup(
            new NamespacedKey(plugin, "compressed_items"),
            new CustomItemStack(
                PlayerHead.getItemStack(PlayerSkin.fromHashCode(COMPRESSED_ITEMS_TEXTURE)),
                "&9Compressed Items",
                "",
                "&7Several generators combined into one",
                "",
                "&a> Click to open"
            ),
            false
        );
        compressedItems.addGroup(generatorsGroup);
        compressedItems.register(plugin);
        SMGItems.SMG_MAIN_GROUP.setFixedGroup(COMPRESSED_ITEMS_SLOT, compressedItems);

        plugin.getLogger().log(Level.INFO, "Registered {0} compressed generators.", count);
    }

    /**
     * Loads {@code compressed.yml}, missing options are added and existing values are kept.
     */
    @Nonnull
    private static Config loadConfig(@Nonnull SimpleMaterialGenerators plugin, @Nonnull List<MaterialGenerator> generators) {
        if (!new File(plugin.getDataFolder(), FILE_NAME).exists()) {
            plugin.saveResource(FILE_NAME, false);
        }

        Config cfg = new Config(plugin, FILE_NAME);
        cfg.setDefaultValue("enabled", true);
        for (int multiplier : MULTIPLIERS) {
            cfg.setDefaultValue("multipliers.x" + multiplier, true);
        }
        for (MaterialGenerator generator : generators) {
            cfg.setDefaultValue(GENERATORS_SECTION + "." + generator.getId(), true);
        }
        cfg.save();
        return cfg;
    }

    /**
     * The original (not compressed) generators of this addon, in registration order.
     */
    @Nonnull
    private static List<MaterialGenerator> getGenerators(@Nonnull SimpleMaterialGenerators plugin) {
        List<MaterialGenerator> list = new ArrayList<>();
        for (SlimefunItem item : Slimefun.getRegistry().getAllSlimefunItems()) {
            if (item instanceof MaterialGenerator && item.getAddon() == plugin
                && ((MaterialGenerator) item).getMultiplier() == 1) {
                list.add((MaterialGenerator) item);
            }
        }
        return list;
    }

    /**
     * The name is "<original name> &a(N)", the lore is the original lore plus the combined amount.
     */
    @Nonnull
    private static SlimefunItemStack createItemStack(@Nonnull MaterialGenerator generator, int multiplier) {
        ItemStack item = generator.getItem().clone();
        ItemMeta meta = item.getItemMeta();

        // only the multiplier at the end is green, the name keeps its color
        meta.setDisplayName(generator.getItemName() + " " + ChatColor.GREEN + "(" + multiplier + ")");

        List<String> lore = meta.hasLore() ? new ArrayList<>(meta.getLore()) : new ArrayList<>();
        lore.add(Math.min(1, lore.size()), ChatColor.GRAY + "Combines " + multiplier + " machines into one.");
        meta.setLore(lore);
        item.setItemMeta(meta);

        return new SlimefunItemStack(generator.getId() + "_X" + multiplier, item);
    }
}
