package me.waleks.simplematerialgenerators.items;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.InventoryBlock;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A generator that produces one material into its own 54 slot inventory.
 * The output slots can be emptied by players, Slimefun cargo and Networks.
 */
public class MaterialGenerator extends SlimefunItem implements InventoryBlock {

    private static final int STATUS_SLOT = 4;
    private static final int[] BACKGROUND_SLOTS = {0, 1, 2, 3, 5, 6, 7, 8};
    private static final int[] OUTPUT_SLOTS = new int[45];

    static {
        for (int i = 0; i < OUTPUT_SLOTS.length; i++) {
            OUTPUT_SLOTS[i] = 9 + i;
        }
    }

    private final Map<Location, Integer> progress = new ConcurrentHashMap<>();
    private final ItemSetting<Integer> rate;
    private ItemStack item;

    @ParametersAreNonnullByDefault
    public MaterialGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int defaultRate) {
        super(itemGroup, item, recipeType, recipe);
        this.rate = new IntRangeSetting(this, "rate", 1, defaultRate, 1000);
        addItemSetting(this.rate);
    }

    @Override
    public int[] getInputSlots() {
        return new int[0];
    }

    @Override
    public int[] getOutputSlots() {
        return OUTPUT_SLOTS;
    }

    private void constructMenu(@Nonnull BlockMenuPreset preset) {
        preset.setSize(54);

        for (int slot : BACKGROUND_SLOTS) {
            preset.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        // Item settings are not loaded yet at this point, so the progress is filled in by the ticker.
        preset.addItem(STATUS_SLOT, new CustomItemStack(this.item.getType(), "&aGenerating..."), ChestMenuUtils.getEmptyClickHandler());

        // Players may take items out of the output slots, but not put items in.
        for (int slot : OUTPUT_SLOTS) {
            preset.addMenuClickHandler(slot, new AdvancedMenuClickHandler() {

                @Override
                public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                    return false;
                }

                @Override
                public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                    return cursor == null || cursor.getType() == Material.AIR;
                }
            });
        }
    }

    @Nonnull
    private BlockBreakHandler onBlockBreak() {
        return new SimpleBlockBreakHandler() {

            @Override
            public void onBlockBreak(@Nonnull Block b) {
                progress.remove(b.getLocation());

                BlockMenu menu = StorageCacheUtils.getMenu(b.getLocation());
                if (menu != null) {
                    menu.dropItems(b.getLocation(), OUTPUT_SLOTS);
                }
            }
        };
    }

    @Override
    public void preRegister() {
        // Created here instead of the constructor, so the output item is already set.
        createPreset(this, this::constructMenu);
        addItemHandler(onBlockBreak());

        addItemHandler(new BlockTicker() {

            @Override
            @ParametersAreNonnullByDefault
            public void tick(Block b, SlimefunItem sf, SlimefunBlockData data) {
                MaterialGenerator.this.tick(b, data);
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        });
    }

    private void tick(@Nonnull Block b, @Nonnull SlimefunBlockData data) {
        BlockMenu menu = data.getBlockMenu();
        if (menu == null || this.item == null) {
            return;
        }

        Location l = b.getLocation();
        int total = this.rate.getValue();

        if (!menu.fits(this.item, OUTPUT_SLOTS)) {
            updateStatus(menu, progress.getOrDefault(l, 0), true);
            return;
        }

        int current = progress.getOrDefault(l, 0) + 1;
        if (current >= total) {
            menu.pushItem(this.item.clone(), OUTPUT_SLOTS);
            current = 0;
        }

        progress.put(l, current);
        updateStatus(menu, current, false);
    }

    private void updateStatus(@Nonnull BlockMenu menu, int current, boolean full) {
        if (menu.hasViewer()) {
            menu.replaceExistingItem(STATUS_SLOT, getStatusItem(current, full));
        }
    }

    @Nonnull
    private ItemStack getStatusItem(int current, boolean full) {
        Material icon = this.item == null ? Material.BARRIER : this.item.getType();

        if (full) {
            return new CustomItemStack(icon, "&cOutput is full", "", "&7Empty the generator to continue");
        }

        int total = this.rate.getValue();
        return new CustomItemStack(icon, "&aGenerating...", "", "&7Progress: &e" + current + "&7/&e" + total + " t");
    }

    public final MaterialGenerator setItem(@Nonnull Material material) {
        this.item = new ItemStack(material);
        return this;
    }
}
