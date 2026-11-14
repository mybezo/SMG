package me.waleks.simplematerialgenerators;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public final class SMGItems {

    private SMGItems() {}

    public static final ItemGroup SMG_ITEM_CATEGORY = new ItemGroup(
        new NamespacedKey(SimpleMaterialGenerators.getInstance(), "simplematerialgenerators"),
        CustomItemStack.create(Material.SMOOTH_STONE, "&9Simple Material Generators")
    );

    public static final SlimefunItemStack SMG_GENERATOR_MULTIBLOCK = new SlimefunItemStack(
        "SMG_GENERATOR_MULTIBLOCK",
        Material.BEDROCK,
        "&9Material Generator - Multiblock",
        "",
        "&dEvery material generator in this addon",
        "&dis set up exactly like this",
        "&aJust place a chest on top of it"
    );
	
	/*	Template for adding more generator items
	public static final SlimefunItemStack SMG_GENERATOR_ = new SlimefunItemStack(
		"SMG_GENERATOR_",
		Material.,
		"& generator",
		"&6Speed: &e t",
		"",
		"&9&oSimple Material Generators"
	);
	*/

    public static final SlimefunItemStack SMG_GENERATOR_COBBLESTONE = new SlimefunItemStack(
        "SMG_GENERATOR_COBBLESTONE",
        Material.COBBLESTONE,
        "&7Cobblestone Generator",
        "&6Speed: &e4 t",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_STONE_BROKEN = new SlimefunItemStack(
        "SMG_GENERATOR_STONE_BROKEN",
        Material.STONE,
        "&7Stone Generator &8(Broken)",
        "&8Needs to be repaired",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_STONE = new SlimefunItemStack(
        "SMG_GENERATOR_STONE",
        Material.STONE,
        "&7Stone Generator",
        "&6Speed: &e8 t",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_SMOOTH_STONE_BROKEN = new SlimefunItemStack(
        "SMG_GENERATOR_SMOOTH_STONE_BROKEN",
        Material.SMOOTH_STONE,
        "&7Smooth Stone Generator &8(Broken)",
        "&8Needs to be repaired",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_SMOOTH_STONE = new SlimefunItemStack(
        "SMG_GENERATOR_SMOOTH_STONE",
        Material.SMOOTH_STONE,
        "&7Smooth Stone Generator",
        "&6Speed: &e12 t",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_GRAVEL_BROKEN = new SlimefunItemStack(
        "SMG_GENERATOR_GRAVEL_BROKEN",
        Material.GRAVEL,
        "&7Gravel Generator &8(Broken)",
        "&8Needs to be repaired",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_GRAVEL = new SlimefunItemStack(
        "SMG_GENERATOR_GRAVEL",
        Material.ANDESITE,
        "&7Gravel Generator",
        "&6Speed: &e6 t",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_SAND_BROKEN = new SlimefunItemStack(
        "SMG_GENERATOR_SAND_BROKEN",
        Material.SAND,
        "&eSand Generator &8(Broken)",
        "&8Needs to be repaired",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_SAND = new SlimefunItemStack(
        "SMG_GENERATOR_SAND",
        Material.SANDSTONE,
        "&eSand Generator",
        "&6Speed: &e8 t",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_GLASS = new SlimefunItemStack(
        "SMG_GENERATOR_GLASS",
        Material.GLASS,
        "&fGlass Generator",
        "&6Speed: &e12 t",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_NETHERRACK = new SlimefunItemStack(
        "SMG_GENERATOR_NETHERRACK",
        Material.NETHERRACK,
        "&cNetherrack Generator",
        "&6Speed: &e6 t",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_SOUL_SAND = new SlimefunItemStack(
        "SMG_GENERATOR_SOUL_SAND",
        Material.SOUL_SAND,
        "&8Soul Sand Generator",
        "&6Speed: &e8 t",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_REDSTONE_BADLY_FORMED = new SlimefunItemStack(
        "SMG_GENERATOR_REDSTONE_BADLY_FORMED",
        Material.REDSTONE_BLOCK,
        "&cRedstone Generator &8(Badly Formed)",
        "&8I need to rework this...",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_REDSTONE_BROKEN = new SlimefunItemStack(
        "SMG_GENERATOR_REDSTONE_BROKEN",
        Material.REDSTONE_BLOCK,
        "&cRedstone Generator &8(Broken)",
        "&8Needs to be repaired",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_REDSTONE = new SlimefunItemStack(
        "SMG_GENERATOR_REDSTONE",
        Material.REDSTONE_BLOCK,
        "&cRedstone Generator",
        "&6Speed: &e24 t",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_OBSIDIAN_BADLY_FORMED = new SlimefunItemStack(
        "SMG_GENERATOR_OBSIDIAN_BADLY_FORMED",
        Material.OBSIDIAN,
        "&5Obsidian Generator &8(Badly Formed)",
        "&8I need to rework this...",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_OBSIDIAN_BROKEN = new SlimefunItemStack(
        "SMG_GENERATOR_OBSIDIAN_BROKEN",
        Material.OBSIDIAN,
        "&5Obsidian Generator &8(Broken)",
        "&8Needs to be repaired",
        "",
        "&9&oSimple Material Generators"
    );

    public static final SlimefunItemStack SMG_GENERATOR_OBSIDIAN = new SlimefunItemStack(
        "SMG_GENERATOR_OBSIDIAN",
        Material.OBSIDIAN,
        "&5Obsidian Generator",
        "&6Speed: &e128 t",
        "",
        "&9&oSimple Material Generators"
    );
}
