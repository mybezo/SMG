package me.waleks.simplematerialgenerators.items;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
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
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A generator that produces one material into its own 54 slot inventory.
 * The output slots can be emptied by players, Slimefun cargo and Networks.
 * <p>
 * A compressed generator combines several generators into one: it runs at the speed of
 * the original generator, and every finished cycle produces {@code multiplier} items at once.
 */
public class MaterialGenerator extends SlimefunItem implements InventoryBlock, RecipeDisplayItem {

    private static final int STATUS_SLOT = 4;
    private static final int[] BACKGROUND_SLOTS = {0, 1, 2, 3, 5, 6, 7, 8};
    private static final int[] OUTPUT_SLOTS = new int[45];

    static {
        for (int i = 0; i < OUTPUT_SLOTS.length; i++) {
            OUTPUT_SLOTS[i] = 9 + i;
        }
    }

    private final Map<Location, Integer> progress = new ConcurrentHashMap<>();
    @Nullable
    private final ItemSetting<Integer> rate;
    // the original generator of a compressed generator, it decides the speed
    @Nullable
    private final MaterialGenerator base;
    private final int multiplier;
    private ItemStack item;

    @ParametersAreNonnullByDefault
    public MaterialGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int defaultRate) {
        super(itemGroup, item, recipeType, recipe);
        this.rate = new IntRangeSetting(this, "rate", 1, defaultRate, 1000);
        this.base = null;
        this.multiplier = 1;
        addItemSetting(this.rate);
    }

    /**
     * Creates a compressed version of the given generator, which produces {@code multiplier} items per cycle.
     */
    @ParametersAreNonnullByDefault
    public MaterialGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe,
                             MaterialGenerator base, int multiplier) {
        super(itemGroup, item, recipeType, recipe);
        this.rate = null;
        this.base = base;
        this.multiplier = multiplier;
        this.item = base.item == null ? null : base.item.clone();
    }

    /**
     * The amount of generators combined into this one, 1 for a normal generator.
     */
    public int getMultiplier() {
        return multiplier;
    }

    /**
     * The ticks per cycle. A compressed generator uses the setting of its original generator.
     */
    public int getRate() {
        if (base != null) {
            return base.getRate();
        }
        return rate == null ? 1 : rate.getValue();
    }

    @Nonnull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        if (multiplier <= 1) {
            return Collections.emptyList();
        }
        Material icon = this.item == null ? Material.BARRIER : this.item.getType();
        return Collections.singletonList(new CustomItemStack(icon,
            "&aMultiplier: &ex" + multiplier,
            "",
            "&7Combines " + multiplier + " generators into one.",
            "&7Produces &e" + multiplier + " &7items per cycle."
        ));
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

                // vanilla turns broken ice into water (without Silk Touch), remove that water
                if (b.getType() == Material.ICE) {
                    Bukkit.getScheduler().runTask(getAddon().getJavaPlugin(), () -> {
                        if (b.getType() == Material.WATER) {
                            b.setType(Material.AIR);
                        }
                    });
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
        int total = getRate();
        int current = progress.getOrDefault(l, 0) + 1;

        // the free space is only counted when a cycle is finished
        if (current >= total) {
            int space = getFreeSpace(menu);

            if (space <= 0) {
                // wait at the end of the cycle until there is room
                progress.put(l, total);
                updateStatus(menu, total, true);
                return;
            }

            // only produce what fits, so no items are lost
            output(menu, Math.min(multiplier, space));
            current = 0;
        }

        progress.put(l, current);
        updateStatus(menu, current, false);
    }

    /**
     * Counts how many output items still fit into the output slots, at most {@code multiplier}.
     */
    private int getFreeSpace(@Nonnull BlockMenu menu) {
        int maxStack = this.item.getMaxStackSize();
        int space = 0;

        for (int slot : OUTPUT_SLOTS) {
            ItemStack stack = menu.getItemInSlot(slot);
            if (stack == null || stack.getType() == Material.AIR) {
                space += maxStack;
            } else if (stack.isSimilar(this.item)) {
                space += Math.max(0, maxStack - stack.getAmount());
            }

            if (space >= multiplier) {
                return multiplier;
            }
        }

        return space;
    }

    /**
     * Puts the given amount of items into the output slots, split into valid stacks.
     */
    private void output(@Nonnull BlockMenu menu, int amount) {
        int maxStack = this.item.getMaxStackSize();

        while (amount > 0) {
            int size = Math.min(amount, maxStack);
            ItemStack stack = this.item.clone();
            stack.setAmount(size);
            menu.pushItem(stack, OUTPUT_SLOTS);
            amount -= size;
        }
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

        String output = multiplier > 1 ? "&7Output: &e" + multiplier + " &7items per cycle" : "&7Output: &e1 &7item per cycle";
        return new CustomItemStack(icon, "&aGenerating...", "", "&7Progress: &e" + current + "&7/&e" + getRate() + " t", output);
    }

    public final MaterialGenerator setItem(@Nonnull Material material) {
        this.item = new ItemStack(material);
        return this;
    }
}
