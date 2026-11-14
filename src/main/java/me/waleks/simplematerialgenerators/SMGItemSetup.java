package me.waleks.simplematerialgenerators;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.waleks.simplematerialgenerators.items.BrokenGenerator;
import me.waleks.simplematerialgenerators.items.MaterialGenerator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public final class SMGItemSetup {

    private SMGItemSetup() {}

    public static void setup(@Nonnull SimpleMaterialGenerators plugin) {
		/*	Template for adding more items
		new MaterialGenerator(SMGItems.SMG_ITEM_CATEGORY,
			SMGItems.SMG_GENERATOR_,
			RecipeType.ENHANCED_CRAFTING_TABLE,
			new ItemStack[] {
				null, null, null,
				null, null, null,
		 		null, null, null
		 	})
		.setItem(Material.)
		.setRate(1)
		.register(plugin);
		*/

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_COBBLESTONE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.SMOOTH_STONE), new ItemStack(Material.SMOOTH_STONE), new ItemStack(Material.SMOOTH_STONE),
                new ItemStack(Material.LAVA_BUCKET), new ItemStack(Material.IRON_PICKAXE), new ItemStack(Material.WATER_BUCKET),
                new ItemStack(Material.SMOOTH_STONE), new ItemStack(Material.SMOOTH_STONE), new ItemStack(Material.SMOOTH_STONE)
            }, 4)
            .setItem(Material.COBBLESTONE)
            .register(plugin);

        new BrokenGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_STONE_BROKEN,
            RecipeType.SMELTERY,
            new ItemStack[] {
                SMGItems.SMG_GENERATOR_COBBLESTONE, null, null,
                null, null, null,
                null, null, null
            })
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_STONE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.STONE), SlimefunItems.SOLDER_INGOT, new ItemStack(Material.STONE),
                SlimefunItems.SOLDER_INGOT, SMGItems.SMG_GENERATOR_STONE_BROKEN, SlimefunItems.SOLDER_INGOT,
                new ItemStack(Material.STONE), SlimefunItems.SOLDER_INGOT, new ItemStack(Material.STONE)
            }, 8)
            .setItem(Material.STONE)
            .register(plugin);

        new BrokenGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_SMOOTH_STONE_BROKEN,
            RecipeType.SMELTERY,
            new ItemStack[] {
                SMGItems.SMG_GENERATOR_STONE, null, null,
                null, null, null,
                null, null, null
            })
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_SMOOTH_STONE,
            RecipeType.SMELTERY,
            new ItemStack[] {
                new ItemStack(Material.SMOOTH_STONE), SlimefunItems.STEEL_INGOT, new ItemStack(Material.SMOOTH_STONE),
                new ItemStack(Material.IRON_BARS), SMGItems.SMG_GENERATOR_SMOOTH_STONE_BROKEN, new ItemStack(Material.IRON_BARS),
                new ItemStack(Material.SMOOTH_STONE), SlimefunItems.STEEL_INGOT, new ItemStack(Material.SMOOTH_STONE)
            }, 12)
            .setItem(Material.SMOOTH_STONE)
            .register(plugin);

        new BrokenGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_GRAVEL_BROKEN,
            RecipeType.GRIND_STONE,
            new ItemStack[] {
                SMGItems.SMG_GENERATOR_COBBLESTONE, null, null,
                null, null, null,
                null, null, null
            })
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_GRAVEL,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.STONE_BRICKS), SlimefunItems.BILLON_INGOT, new ItemStack(Material.STONE_BRICKS),
                SlimefunItems.BILLON_INGOT, SMGItems.SMG_GENERATOR_GRAVEL_BROKEN, SlimefunItems.BILLON_INGOT,
                new ItemStack(Material.STONE_BRICKS), SlimefunItems.BILLON_INGOT, new ItemStack(Material.STONE_BRICKS)
            }, 6)
            .setItem(Material.GRAVEL)
            .register(plugin);

        new BrokenGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_SAND_BROKEN,
            RecipeType.ORE_CRUSHER,
            new ItemStack[] {
                SMGItems.SMG_GENERATOR_GRAVEL, null, null,
                null, null, null,
                null, null, null
            })
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_SAND,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.SANDSTONE), SlimefunItems.DURALUMIN_INGOT, new ItemStack(Material.SANDSTONE),
                SlimefunItems.DURALUMIN_INGOT, SMGItems.SMG_GENERATOR_SAND_BROKEN, SlimefunItems.DURALUMIN_INGOT,
                new ItemStack(Material.SANDSTONE), SlimefunItems.DURALUMIN_INGOT, new ItemStack(Material.SANDSTONE)
            }, 8)
            .setItem(Material.SAND)
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_GLASS,
            RecipeType.SMELTERY,
            new ItemStack[] {
                SlimefunItems.BRONZE_INGOT, SlimefunItems.GOLD_8K, SlimefunItems.BRONZE_INGOT,
                SlimefunItems.BRASS_INGOT, SMGItems.SMG_GENERATOR_SAND, SlimefunItems.BRASS_INGOT,
                null, null, null
            }, 12)
            .setItem(Material.GLASS)
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_NETHERRACK,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.SOLDER_INGOT, SlimefunItems.SOLDER_INGOT, SlimefunItems.SOLDER_INGOT,
                SMGItems.SMG_GENERATOR_STONE, new ItemStack(Material.LAVA_BUCKET), SMGItems.SMG_GENERATOR_STONE,
                SMGItems.SMG_GENERATOR_STONE, SlimefunItems.COMPOSTER, SMGItems.SMG_GENERATOR_STONE
            }, 6)
            .setItem(Material.NETHERRACK)
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_SOUL_SAND,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.STEEL_INGOT, new ItemStack(Material.SPIDER_EYE), SlimefunItems.STEEL_INGOT,
                new ItemStack(Material.ROTTEN_FLESH), new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.BONE),
                SMGItems.SMG_GENERATOR_SAND, SlimefunItems.COMPOSTER, SMGItems.SMG_GENERATOR_SAND
            }, 8)
            .setItem(Material.SOUL_SAND)
            .register(plugin);

        new BrokenGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
                SMGItems.SMG_GENERATOR_REDSTONE_BADLY_FORMED,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                    SMGItems.SMG_GENERATOR_SAND, null, SMGItems.SMG_GENERATOR_GRAVEL,
                    null, null, null,
                    null, null, null
                })
            .register(plugin);

        new BrokenGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
                SMGItems.SMG_GENERATOR_REDSTONE_BROKEN,
                RecipeType.ORE_CRUSHER,
                new ItemStack[] {
                    SMGItems.SMG_GENERATOR_REDSTONE_BADLY_FORMED, null, null,
                    null, null, null,
                    null, null, null
                })
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_REDSTONE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SMGItems.SMG_GENERATOR_REDSTONE_BROKEN, new ItemStack(Material.REDSTONE_ORE), SMGItems.SMG_GENERATOR_REDSTONE_BROKEN,
                SMGItems.SMG_GENERATOR_REDSTONE_BROKEN, SMGItems.SMG_GENERATOR_GRAVEL, SMGItems.SMG_GENERATOR_REDSTONE_BROKEN,
                SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DURALUMIN_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT
            }, 24)
            .setItem(Material.REDSTONE)
            .register(plugin);

        new BrokenGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_OBSIDIAN_BADLY_FORMED,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SMGItems.SMG_GENERATOR_COBBLESTONE, null, SMGItems.SMG_GENERATOR_STONE,
                null, SlimefunItems.COBALT_PICKAXE, null,
                SMGItems.SMG_GENERATOR_SMOOTH_STONE, null, SMGItems.SMG_GENERATOR_NETHERRACK
            })
            .register(plugin);

        new BrokenGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_OBSIDIAN_BROKEN,
            RecipeType.ORE_CRUSHER,
            new ItemStack[] {
                SMGItems.SMG_GENERATOR_OBSIDIAN_BADLY_FORMED, null, null,
                null, null, null,
                null, null, null
            })
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_OBSIDIAN,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.REINFORCED_ALLOY_INGOT, new ItemStack(Material.WATER_BUCKET), SlimefunItems.REINFORCED_ALLOY_INGOT,
                SMGItems.SMG_GENERATOR_OBSIDIAN_BROKEN, SMGItems.SMG_GENERATOR_STONE, SMGItems.SMG_GENERATOR_OBSIDIAN_BROKEN,
                SMGItems.SMG_GENERATOR_OBSIDIAN_BROKEN, new ItemStack(Material.LAVA_BUCKET), SMGItems.SMG_GENERATOR_OBSIDIAN_BROKEN
            }, 128)
            .setItem(Material.OBSIDIAN)
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_DIRT,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.DIRT), new ItemStack(Material.DIRT), new ItemStack(Material.DIRT),
                SlimefunItems.BRONZE_INGOT, SMGItems.SMG_GENERATOR_GRAVEL, SlimefunItems.BRONZE_INGOT,
                new ItemStack(Material.DIRT), SlimefunItems.COMPOSTER, new ItemStack(Material.DIRT)
            }, 6)
            .setItem(Material.DIRT)
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_GRASS_BLOCK,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.GRASS_BLOCK), new ItemStack(Material.WHEAT_SEEDS), new ItemStack(Material.GRASS_BLOCK),
                new ItemStack(Material.BONE_MEAL), SMGItems.SMG_GENERATOR_DIRT, new ItemStack(Material.BONE_MEAL),
                new ItemStack(Material.GRASS_BLOCK), SlimefunItems.FERTILIZER, new ItemStack(Material.GRASS_BLOCK)
            }, 12)
            .setItem(Material.GRASS_BLOCK)
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_CLAY,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.CLAY), new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.CLAY),
                SlimefunItems.REDSTONE_ALLOY, SMGItems.SMG_GENERATOR_SAND, SlimefunItems.REDSTONE_ALLOY,
                new ItemStack(Material.CLAY), new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.CLAY)
            }, 8)
            .setItem(Material.CLAY)
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_DIORITE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.DIORITE), new ItemStack(Material.QUARTZ), new ItemStack(Material.DIORITE),
                SlimefunItems.ALUMINUM_INGOT, SMGItems.SMG_GENERATOR_COBBLESTONE, SlimefunItems.ALUMINUM_INGOT,
                new ItemStack(Material.DIORITE), new ItemStack(Material.QUARTZ), new ItemStack(Material.DIORITE)
            }, 8)
            .setItem(Material.DIORITE)
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_GRANITE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.GRANITE), new ItemStack(Material.QUARTZ), new ItemStack(Material.GRANITE),
                SlimefunItems.COPPER_INGOT, SMGItems.SMG_GENERATOR_DIORITE, SlimefunItems.COPPER_INGOT,
                new ItemStack(Material.GRANITE), new ItemStack(Material.QUARTZ), new ItemStack(Material.GRANITE)
            }, 8)
            .setItem(Material.GRANITE)
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_SNOW_BLOCK,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.SNOW_BLOCK), new ItemStack(Material.SNOWBALL), new ItemStack(Material.SNOW_BLOCK),
                SlimefunItems.HARDENED_METAL_INGOT, SMGItems.SMG_GENERATOR_STONE, SlimefunItems.HARDENED_METAL_INGOT,
                new ItemStack(Material.SNOW_BLOCK), new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.SNOW_BLOCK)
            }, 6)
            .setItem(Material.SNOW_BLOCK)
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_ICE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.ICE), new ItemStack(Material.SNOW_BLOCK), new ItemStack(Material.ICE),
                SlimefunItems.ALUMINUM_INGOT, SMGItems.SMG_GENERATOR_SNOW_BLOCK, SlimefunItems.ALUMINUM_INGOT,
                new ItemStack(Material.ICE), new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.ICE)
            }, 10)
            .setItem(Material.ICE)
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_PACKED_ICE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.PACKED_ICE), new ItemStack(Material.ICE), new ItemStack(Material.PACKED_ICE),
                SlimefunItems.DAMASCUS_STEEL_INGOT, SMGItems.SMG_GENERATOR_ICE, SlimefunItems.DAMASCUS_STEEL_INGOT,
                new ItemStack(Material.PACKED_ICE), new ItemStack(Material.ICE), new ItemStack(Material.PACKED_ICE)
            }, 16)
            .setItem(Material.PACKED_ICE)
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_BLUE_ICE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.BLUE_ICE), new ItemStack(Material.PACKED_ICE), new ItemStack(Material.BLUE_ICE),
                SlimefunItems.STEEL_INGOT, SMGItems.SMG_GENERATOR_PACKED_ICE, SlimefunItems.STEEL_INGOT,
                new ItemStack(Material.BLUE_ICE), SlimefunItems.COOLING_UNIT, new ItemStack(Material.BLUE_ICE)
            }, 32)
            .setItem(Material.BLUE_ICE)
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_END_STONE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.END_STONE), new ItemStack(Material.ENDER_PEARL), new ItemStack(Material.END_STONE),
                SlimefunItems.REINFORCED_ALLOY_INGOT, SMGItems.SMG_GENERATOR_STONE, SlimefunItems.REINFORCED_ALLOY_INGOT,
                new ItemStack(Material.END_STONE), new ItemStack(Material.ENDER_PEARL), new ItemStack(Material.END_STONE)
            }, 16)
            .setItem(Material.END_STONE)
            .register(plugin);
    }
}
