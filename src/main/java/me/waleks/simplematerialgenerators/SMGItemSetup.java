package me.waleks.simplematerialgenerators;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.waleks.simplematerialgenerators.items.BrokenGenerator;
import me.waleks.simplematerialgenerators.items.GeneratorMultiblock;
import me.waleks.simplematerialgenerators.items.MaterialGenerator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public final class SMGItemSetup {

    private SMGItemSetup() {}

    public static void setup(@Nonnull SimpleMaterialGenerators plugin) {
        new GeneratorMultiblock(SMGItems.SMG_ITEM_CATEGORY, SMGItems.SMG_GENERATOR_MULTIBLOCK).register(plugin);

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
                SMGItems.SMG_GENERATOR_COBBLESTONE.item(), null, null,
                null, null, null,
                null, null, null
            })
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_STONE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.STONE), SlimefunItems.SOLDER_INGOT.item(), new ItemStack(Material.STONE),
                SlimefunItems.SOLDER_INGOT.item(), SMGItems.SMG_GENERATOR_STONE_BROKEN.item(), SlimefunItems.SOLDER_INGOT.item(),
                new ItemStack(Material.STONE), SlimefunItems.SOLDER_INGOT.item(), new ItemStack(Material.STONE)
            }, 8)
            .setItem(Material.STONE)
            .register(plugin);

        new BrokenGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_SMOOTH_STONE_BROKEN,
            RecipeType.SMELTERY,
            new ItemStack[] {
                SMGItems.SMG_GENERATOR_STONE.item(), null, null,
                null, null, null,
                null, null, null
            })
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_SMOOTH_STONE,
            RecipeType.SMELTERY,
            new ItemStack[] {
                new ItemStack(Material.SMOOTH_STONE), SlimefunItems.STEEL_INGOT.item(), new ItemStack(Material.SMOOTH_STONE),
                new ItemStack(Material.IRON_BARS), SMGItems.SMG_GENERATOR_SMOOTH_STONE_BROKEN.item(), new ItemStack(Material.IRON_BARS),
                new ItemStack(Material.SMOOTH_STONE), SlimefunItems.STEEL_INGOT.item(), new ItemStack(Material.SMOOTH_STONE)
            }, 12)
            .setItem(Material.SMOOTH_STONE)
            .register(plugin);

        new BrokenGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_GRAVEL_BROKEN,
            RecipeType.GRIND_STONE,
            new ItemStack[] {
                SMGItems.SMG_GENERATOR_COBBLESTONE.item(), null, null,
                null, null, null,
                null, null, null
            })
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_GRAVEL,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.STONE_BRICKS), SlimefunItems.BILLON_INGOT.item(), new ItemStack(Material.STONE_BRICKS),
                SlimefunItems.BILLON_INGOT.item(), SMGItems.SMG_GENERATOR_GRAVEL_BROKEN.item(), SlimefunItems.BILLON_INGOT.item(),
                new ItemStack(Material.STONE_BRICKS), SlimefunItems.BILLON_INGOT.item(), new ItemStack(Material.STONE_BRICKS)
            }, 6)
            .setItem(Material.GRAVEL)
            .register(plugin);

        new BrokenGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_SAND_BROKEN,
            RecipeType.ORE_CRUSHER,
            new ItemStack[] {
                SMGItems.SMG_GENERATOR_GRAVEL.item(), null, null,
                null, null, null,
                null, null, null
            })
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_SAND,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.SANDSTONE), SlimefunItems.DURALUMIN_INGOT.item(), new ItemStack(Material.SANDSTONE),
                SlimefunItems.DURALUMIN_INGOT.item(), SMGItems.SMG_GENERATOR_SAND_BROKEN.item(), SlimefunItems.DURALUMIN_INGOT.item(),
                new ItemStack(Material.SANDSTONE), SlimefunItems.DURALUMIN_INGOT.item(), new ItemStack(Material.SANDSTONE)
            }, 8)
            .setItem(Material.SAND)
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_GLASS,
            RecipeType.SMELTERY,
            new ItemStack[] {
                SlimefunItems.BRONZE_INGOT.item(), SlimefunItems.GOLD_8K.item(), SlimefunItems.BRONZE_INGOT.item(),
                SlimefunItems.BRASS_INGOT.item(), SMGItems.SMG_GENERATOR_SAND.item(), SlimefunItems.BRASS_INGOT.item(),
                null, null, null
            }, 12)
            .setItem(Material.GLASS)
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_NETHERRACK,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.SOLDER_INGOT.item(), SlimefunItems.SOLDER_INGOT.item(), SlimefunItems.SOLDER_INGOT.item(),
                SMGItems.SMG_GENERATOR_STONE.item(), new ItemStack(Material.LAVA_BUCKET), SMGItems.SMG_GENERATOR_STONE.item(),
                SMGItems.SMG_GENERATOR_STONE.item(), SlimefunItems.COMPOSTER.item(), SMGItems.SMG_GENERATOR_STONE.item()
            }, 6)
            .setItem(Material.NETHERRACK)
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_SOUL_SAND,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.STEEL_INGOT.item(), new ItemStack(Material.SPIDER_EYE), SlimefunItems.STEEL_INGOT.item(),
                new ItemStack(Material.ROTTEN_FLESH), new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.BONE),
                SMGItems.SMG_GENERATOR_SAND.item(), SlimefunItems.COMPOSTER.item(), SMGItems.SMG_GENERATOR_SAND.item()
            }, 8)
            .setItem(Material.SOUL_SAND)
            .register(plugin);

        new BrokenGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
                SMGItems.SMG_GENERATOR_REDSTONE_BADLY_FORMED,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                    SMGItems.SMG_GENERATOR_SAND.item(), null, SMGItems.SMG_GENERATOR_GRAVEL.item(),
                    null, null, null,
                    null, null, null
                })
            .register(plugin);

        new BrokenGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
                SMGItems.SMG_GENERATOR_REDSTONE_BROKEN,
                RecipeType.ORE_CRUSHER,
                new ItemStack[] {
                    SMGItems.SMG_GENERATOR_REDSTONE_BADLY_FORMED.item(), null, null,
                    null, null, null,
                    null, null, null
                })
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_REDSTONE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SMGItems.SMG_GENERATOR_REDSTONE_BROKEN.item(), new ItemStack(Material.REDSTONE_ORE), SMGItems.SMG_GENERATOR_REDSTONE_BROKEN.item(),
                SMGItems.SMG_GENERATOR_REDSTONE_BROKEN.item(), SMGItems.SMG_GENERATOR_GRAVEL.item(), SMGItems.SMG_GENERATOR_REDSTONE_BROKEN.item(),
                SlimefunItems.DAMASCUS_STEEL_INGOT.item(), SlimefunItems.DURALUMIN_INGOT.item(), SlimefunItems.DAMASCUS_STEEL_INGOT.item()
            }, 24)
            .setItem(Material.REDSTONE)
            .register(plugin);

        new BrokenGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_OBSIDIAN_BADLY_FORMED,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SMGItems.SMG_GENERATOR_COBBLESTONE.item(), null, SMGItems.SMG_GENERATOR_STONE.item(),
                null, SlimefunItems.COBALT_PICKAXE.item(), null,
                SMGItems.SMG_GENERATOR_SMOOTH_STONE.item(), null, SMGItems.SMG_GENERATOR_NETHERRACK.item()
            })
            .register(plugin);

        new BrokenGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_OBSIDIAN_BROKEN,
            RecipeType.ORE_CRUSHER,
            new ItemStack[] {
                SMGItems.SMG_GENERATOR_OBSIDIAN_BADLY_FORMED.item(), null, null,
                null, null, null,
                null, null, null
            })
            .register(plugin);

        new MaterialGenerator(
            SMGItems.SMG_ITEM_CATEGORY,
            SMGItems.SMG_GENERATOR_OBSIDIAN,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.REINFORCED_ALLOY_INGOT.item(), new ItemStack(Material.WATER_BUCKET), SlimefunItems.REINFORCED_ALLOY_INGOT.item(),
                SMGItems.SMG_GENERATOR_OBSIDIAN_BROKEN.item(), SMGItems.SMG_GENERATOR_STONE.item(), SMGItems.SMG_GENERATOR_OBSIDIAN_BROKEN.item(),
                SMGItems.SMG_GENERATOR_OBSIDIAN_BROKEN.item(), new ItemStack(Material.LAVA_BUCKET), SMGItems.SMG_GENERATOR_OBSIDIAN_BROKEN.item()
            }, 128)
            .setItem(Material.OBSIDIAN)
            .register(plugin);
    }
}
