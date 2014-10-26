/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014 Fabian Damken
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.blockhaus2000.ipm.minecraft.block;

import com.blockhaus2000.ipm.minecraft.block.meta.ActivatorRailBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.AnvilBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.BeaconBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.BedBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.BrewingStandBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.ButtonBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.CakeBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.CarrotAndPotatoBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.CauldronBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.ChestBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.CocoaBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.CommandBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.DaylightSensorBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.DetectorRailBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.DispenserAndDropperBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.DoorBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.EnchantmentTableBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.EndPortalFrameBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.FlowerPotBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.FurnaceBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.HopperBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.JukeboxBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.LeverBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.MobHeadBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.MonsterSpawnerBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.NetherWartBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.NoteBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.PistonBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.PoweredRailBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.PressurePlateBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.RawBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.RedstoneComparatorBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.RedstoneRepeaterBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.RedstoneWireBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.SignBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.StemBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.TrappedChestBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.TripwireBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.WeightedPressurePlateHeavyBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.WeightedPressurePlateLightBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteRawBlockMeta;
import com.blockhaus2000.ipm.minecraft.material.Material;

public enum BlockMaterial implements Material {
    AIR("minecraft:air", new ConcreteRawBlockMeta(0, 0, -1, false, false, true), 0),
    STONE("minecraft:stone", new ConcreteRawBlockMeta(0, 30, 1.5, false, true, false), 1),
    GRANITE("minecraft:stone", 1, 1),
    POLISHED_GRANITE("minecraft:stone", 1, 2),
    DIORITE("minecraft:stone", 1, 3),
    POLISHED_DIORITE("minecraft:stone", 1, 4),
    ANDESITE("minecraft:stone", 1, 5),
    POLISHED_ANDESITE("minecraft:stone", 1, 6),
    GRASS("minecraft:grass", new ConcreteRawBlockMeta(0, 3, 0.6, false, true, false), 2),
    DIRT("minecraft:dirt", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, true, false), 3),
    COARSE_DIRT("minecraft:dirt", 3, 1),
    PODZOL("minecraft:dirt", 3, 2),
    COBBLESTONE("minecraft:cobblestone", new ConcreteRawBlockMeta(0, 30, 2, false, true, false), 4),
    OAK_WOOD_PLANK("minecraft:planks", new ConcreteRawBlockMeta(0, 15, 2, false, true, false), 5),
    SPRUCE_WOOD_PLANK("minecraft:planks", 5, 1),
    BIRCH_WOOD_PLANK("minecraft:planks", 5, 2),
    JUNGLE_WOOD_PLANK("minecraft:planks", 5, 3),
    ACACIA_WOOD_PLANK("minecraft:planks", 5, 4),
    DARK_OAK_WOOD_PLANK("minecraft:planks", 5, 5),
    OAK_SAPLING("minecraft:sapling", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 6),
    SPRUCE_SAPLING("minecraft:sapling", 6, 1),
    BIRCH_SAPLING("minecraft:sapling", 6, 2),
    JUNGLE_SAPLING("minecraft:sapling", 6, 3),
    ACACIA_SAPLING("minecraft:sapling", 6, 4),
    DARK_OAK_SAPLING("minecraft:sapling", 6, 5),
    BEDROCK("minecraft:bedrock", new ConcreteRawBlockMeta(0, 18000000, -1, false, true, false), 7),
    FLOWING_WATER("minecraft:flowing_water", new ConcreteRawBlockMeta(0, 500, Double.NaN, false, false, true), 8),
    STILL_WATER("minecraft:water", new ConcreteRawBlockMeta(0, 500, 100, true, false, true), 9),
    FLOWING_LAVA("minecraft:flowing_lava", new ConcreteRawBlockMeta(15, 0, Double.NaN, false, false, true), 10),
    STILL_LAVA("minecraft:lava", new ConcreteRawBlockMeta(15, 500, 100, true, false, true), 11),
    SAND("minecraft:sand", new ConcreteRawBlockMeta(0, 2.5, 0.5, true, true, false), 12),
    RED_SAND("minecraft:sand", 12, 1),
    GRAVEL("minecraft:gravel", new ConcreteRawBlockMeta(0, 3, 0.6, true, true, false), 13),
    GOLD_ORE("minecraft:gold_ore", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 14),
    IRON_ORE("minecraft:iron_ore", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 15),
    COAL_ORE("minecraft:coal_ore", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 16),
    OAK_WOOD("minecraft:log", new ConcreteRawBlockMeta(0, 10, 2, false, true, false), 17),
    SPRUCE_WOOD("minecraft:log", 17, 1),
    BIRCH_WOOD("minecraft:log", 17, 2),
    JUNGLE_WOOD("minecraft:log", 17, 3),
    OAK_LEAVES("minecraft:leaves", new ConcreteRawBlockMeta(0, 1, 0.2, false, false, true), 18),
    SPRUCE_LEAVES("minecraft:leaves", 18, 1),
    BIRCH_LEAVES("minecraft:leaves", 18, 2),
    JUNGLE_LEAVES("minecraft:leaves", 18, 3),
    SPONGE("minecraft:sponge", new ConcreteRawBlockMeta(0, 3, 0.6, false, true, false), 19),
    WET_SPONGE("minecraft:sponge", 19, 1),
    GLASS("minecraft:glass", new ConcreteRawBlockMeta(0, 1.5, 0.3, false, true, true), 20),
    LAPIS_LAZULI_ORE("minecraft:lapis_ore", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 21),
    LAPIS_LAZULI_BLOCK("minecraft:lapis_block", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 22),
    DISPENSER(
            "minecraft:dispenser",
            new ConcreteRawBlockMeta(0, 17.5, 3.5, false, true, false),
            DispenserAndDropperBlockMeta.class,
            23),
    SANDSTONE("minecraft:sandstone", new ConcreteRawBlockMeta(0, 4, 0.8, false, true, false), 24),
    CHISELED_SANDSTONE("minecraft:sandstone", 24, 1),
    SMOOTH_SANDSTONE("minecraft:sandstone", 24, 2),
    NOTE_BLOCK("minecraft:noteblock", new ConcreteRawBlockMeta(0, 4, 0.8, false, true, false), NoteBlockMeta.class, 25),
    BED("minecraft:bed", new ConcreteRawBlockMeta(0, 1, 0.2, false, true, true), BedBlockMeta.class, 26),
    POWERED_RAIL(
            "minecraft:golden_rail",
            new ConcreteRawBlockMeta(0, 3.5, 0.7, false, false, true),
            PoweredRailBlockMeta.class,
            27),
    DETECTOR_RAIL(
            "minecraft:detector_rail",
            new ConcreteRawBlockMeta(0, 3.5, 0.7, false, false, true),
            DetectorRailBlockMeta.class,
            28),
    STICKY_PISTON("minecraft:sticky_piston", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, true, true), PistonBlockMeta.class, 29),
    COBWEB("minecraft:web", new ConcreteRawBlockMeta(0, 20, 4, false, false, true), 30),
    GRASS_TALL("minecraft:tallgrass", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 31),
    GRASS_TALL_2("minecraft:tallgrass", 31, 1),
    FERN("minecraft:tallgrass", 31, 2),
    DEAD_SHRUB("minecraft:deadbush", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 32),
    PISTON("minecraft:piston", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, true, true), PistonBlockMeta.class, 33),
    PISTON_HEAD("minecraft:piston_head", new ConcreteRawBlockMeta(0, 2.5, Double.NaN, false, false, true), 34),
    WHITE_WOOL("minecraft:wool", new ConcreteRawBlockMeta(0, 4, 0.8, false, true, false), 35),
    ORANGE_WOOL("minecraft:wool", 35, 1),
    MAGENTA_WOOL("minecraft:wool", 35, 2),
    LIGHT_BLUE_WOOL("minecraft:wool", 35, 3),
    YELLOW_WOOL("minecraft:wool", 35, 4),
    LIME_WOOL("minecraft:wool", 35, 5),
    PINK_WOOL("minecraft:wool", 35, 6),
    GRAY_WOOL("minecraft:wool", 35, 7),
    LIGHT_GRAY_WOOL("minecraft:wool", 35, 8),
    CYAN_WOOL("minecraft:wool", 35, 9),
    PURPLE_WOOL("minecraft:wool", 35, 0),
    BLUE_WOOL("minecraft:wool", 35, 1),
    BROWN_WOOL("minecraft:wool", 35, 2),
    GREEN_WOOL("minecraft:wool", 35, 3),
    RED_WOOL("minecraft:wool", 35, 4),
    BLACK_WOOL("minecraft:wool", 35, 5),
    MOVED_BLOCK("minecraft:piston_extension", new ConcreteRawBlockMeta(0, 0, Double.NaN, false, false, true), 36),
    DANDELION("minecraft:yellow_flower", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 37),
    POPPY("minecraft:red_flower", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 38),
    BLUE_ORCHID("minecraft:red_flower", 38, 1),
    ALLIUM("minecraft:red_flower", 38, 2),
    AZURE_BLUET("minecraft:red_flower", 38, 3),
    RED_TULIP("minecraft:red_flower", 38, 4),
    ORANGE_TULIP("minecraft:red_flower", 38, 5),
    WHITE_TULIP("minecraft:red_flower", 38, 6),
    PINK_TULIP("minecraft:red_flower", 38, 7),
    OXEYE_DAISY("minecraft:red_flower", 38, 8),
    BROWN_MUSHROOM("minecraft:brown_mushroom", new ConcreteRawBlockMeta(1, 0, 0, false, false, true), 39),
    RED_MUSHROOM("minecraft:red_mushroom", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 40),
    GOLD_BLOCK("minecraft:gold_block", new ConcreteRawBlockMeta(0, 30, 3, false, true, false), 41),
    IRON_BLOCK("minecraft:iron_block", new ConcreteRawBlockMeta(0, 30, 5, false, true, false), 42),
    DOUBLE_STONE_SLAB("minecraft:double_stone_slab", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 43),
    DOUBLE_SANDSTONE_SLAB("minecraft:double_stone_slab", 43, 1),
    DOUBLE_WOODEN_SLAB("minecraft:double_stone_slab", 43, 2),
    DOUBLE_COBBLESTONE_SLAB("minecraft:double_stone_slab", 43, 3),
    DOUBLE_BRICK_SLAB("minecraft:double_stone_slab", 43, 4),
    DOUBLE_STONE_BRICK_SLAB("minecraft:double_stone_slab", 43, 5),
    DOUBLE_NETHER_BRICK_SLAB("minecraft:double_stone_slab", 43, 6),
    DOUBLE_QUARTZ_SLAB("minecraft:double_stone_slab", 43, 7),
    STONE_SLAB("minecraft:stone_slab", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 44),
    SANDSTONE_SLAB("minecraft:stone_slab", 44, 1),
    WOODEN_SLAB("minecraft:stone_slab", 44, 2),
    COBBLESTONE_SLAB("minecraft:stone_slab", 44, 3),
    BRICK_SLAB("minecraft:stone_slab", 44, 4),
    STONE_BRICK_SLAB("minecraft:stone_slab", 44, 5),
    NETHER_BRICK_SLAB("minecraft:stone_slab", 44, 6),
    QUARTZ_SLAB("minecraft:stone_slab", 44, 7),
    BRICKS("minecraft:brick_block", new ConcreteRawBlockMeta(0, 30, 2, false, true, false), 45),
    TNT("minecraft:tnt", new ConcreteRawBlockMeta(0, 0, 0, false, true, false), 46),
    BOOKSHELF("minecraft:bookshelf", new ConcreteRawBlockMeta(0, 7.5, 1.5, false, true, false), 47),
    MOSS_STONE("minecraft:mossy_cobblestone", new ConcreteRawBlockMeta(0, 30, 2, false, true, false), 48),
    OBSIDIAN("minecraft:obsidian", new ConcreteRawBlockMeta(0, 6000, 50, false, true, false), 49),
    TORCH("minecraft:torch", new ConcreteRawBlockMeta(14, 0, 0, false, false, true), 50),
    FIRE("minecraft:fire", new ConcreteRawBlockMeta(15, 0, 0, false, false, true), 51),
    MONSTER_SPAWNER(
            "minecraft:mob_spawner",
            new ConcreteRawBlockMeta(0, 25, 5, false, true, false),
            MonsterSpawnerBlockMeta.class,
            52),
    OAK_WOOD_STAIRS("minecraft:oak_stairs", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 53),
    CHEST("minecraft:chest", new ConcreteRawBlockMeta(0, 12.5, 2.5, false, false, true), ChestBlockMeta.class, 54),
    REDSTONE_WIRE(
            "minecraft:redstone_wire",
            new ConcreteRawBlockMeta(0, 0, 0, false, false, true),
            RedstoneWireBlockMeta.class,
            55),
    DIAMOND_ORE("minecraft:diamond_ore", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 56),
    DIAMOND_BLOCK("minecraft:diamond_block", new ConcreteRawBlockMeta(0, 30, 5, false, true, false), 57),
    CRAFTING_TABLE("minecraft:crafting_table", new ConcreteRawBlockMeta(0, 12.5, 2.5, false, true, false), 58),
    WHEAT_CROPS("minecraft:wheat", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 59),
    FARMLAND("minecraft:farmland", new ConcreteRawBlockMeta(0, 3, 0.6, false, true, true), 60),
    FURNACE("minecraft:furnace", new ConcreteRawBlockMeta(0, 17.5, 3.5, false, true, false), FurnaceBlockMeta.class, 61),
    BURNING_FURNACE(
            "minecraft:lit_furnace",
            new ConcreteRawBlockMeta(0, 17.5, Double.NaN, false, true, false),
            FurnaceBlockMeta.class,
            62),
    STANDING_SIGN_BLOCK("minecraft:standing_sign", new ConcreteRawBlockMeta(0, 5, 1, false, false, true), SignBlockMeta.class, 63),
    OAK_DOOR_BLOCK("minecraft:wooden_door", new ConcreteRawBlockMeta(0, 15, 3, false, true, true), DoorBlockMeta.class, 64),
    LADDER("minecraft:ladder", new ConcreteRawBlockMeta(0, 2, 0.4, false, true, false), 65),
    RAIL("minecraft:rail", new ConcreteRawBlockMeta(0, 3.5, 0.7, false, false, true), 66),
    COBBLESTONE_STAIRS("minecraft:stone_stairs", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 67),
    WALL_MOUNTED_SIGN_BLOCK("minecraft:wall_sign", new ConcreteRawBlockMeta(0, 5, 1, false, false, true), SignBlockMeta.class, 68),
    LEVER("minecraft:lever", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, false, true), LeverBlockMeta.class, 69),
    STONE_PRESSURE_PLATE(
            "minecraft:stone_pressure_plate",
            new ConcreteRawBlockMeta(0, 2.5, 0.5, false, false, true),
            PressurePlateBlockMeta.class,
            70),
    IRON_DOOR_BLOCK("minecraft:iron_door", new ConcreteRawBlockMeta(0, 25, 5, false, true, true), DoorBlockMeta.class, 71),
    WOODEN_PRESSURE_PLATE(
            "minecraft:wooden_pressure_plate",
            new ConcreteRawBlockMeta(0, 2.5, 0.5, false, false, true),
            PressurePlateBlockMeta.class,
            72),
    REDSTONE_ORE("minecraft:redstone_ore", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 73),
    GLOWING_REDSTONE_ORE("minecraft:lit_redstone_ore", new ConcreteRawBlockMeta(9, 15, Double.NaN, false, true, true), 74),
    REDSTONE_TORCH_OFF("minecraft:unlit_redstone_torch", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 75),
    REDSTONE_TORCH_ON("minecraft:redstone_torch", new ConcreteRawBlockMeta(7, 0, 0, false, false, true), 76),
    STONE_BUTTON("minecraft:stone_button", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, false, true), ButtonBlockMeta.class, 77),
    SNOW("minecraft:snow_layer", new ConcreteRawBlockMeta(0, 1, 0.1, false, false, true), 78),
    ICE("minecraft:ice", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, true, true), 79),
    SNOW_BLOCK("minecraft:snow", new ConcreteRawBlockMeta(0, 1, 0.1, false, true, false), 80),
    CACTUS("minecraft:cactus", new ConcreteRawBlockMeta(0, 2, 0.4, false, true, true), 81),
    CLAY("minecraft:clay", new ConcreteRawBlockMeta(0, 3, 0.6, false, true, false), 82),
    SUGAR_CANES("minecraft:reeds", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 83),
    JUKEBOX("minecraft:jukebox", new ConcreteRawBlockMeta(0, 30, 2, false, true, false), JukeboxBlockMeta.class, 84),
    OAK_FENCE("minecraft:fence", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 85),
    PUMPKIN("minecraft:pumpkin", new ConcreteRawBlockMeta(0, 5, 1, false, true, false), 86),
    NETHERRACK("minecraft:netherrack", new ConcreteRawBlockMeta(0, 2, 0.4, false, true, false), 87),
    SOUL_SAND("minecraft:soul_sand", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, true, false), 88),
    GLOWSTONE("minecraft:glowstone", new ConcreteRawBlockMeta(15, 1.5, 0.3, false, true, true), 89),
    NETHER_PORTAL("minecraft:portal", new ConcreteRawBlockMeta(11, 0, -1, false, false, true), 90),
    JACK_O_LANTERN("minecraft:lit_pumpkin", new ConcreteRawBlockMeta(15, 5, 1, false, true, false), 91),
    CAKE_BLOCK("minecraft:cake", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, true, true), CakeBlockMeta.class, 92),
    REDSTONE_REPEATER_BLOCK_OFF(
            "minecraft:unpowered_repeater",
            new ConcreteRawBlockMeta(0, 0, 0, false, false, true),
            RedstoneRepeaterBlockMeta.class,
            93),
    REDSTONE_REPEATER_BLOCK_ON(
            "minecraft:powered_repeater",
            new ConcreteRawBlockMeta(15, 0, Double.NaN, false, false, true),
            RedstoneRepeaterBlockMeta.class,
            94),
    WHITE_STAINED_GLASS("minecraft:stained_glass", new ConcreteRawBlockMeta(0, 1.5, 0.3, false, true, true), 95),
    ORANGE_STAINED_GLASS("minecraft:stained_glass", 95, 1),
    MAGENTA_STAINED_GLASS("minecraft:stained_glass", 95, 2),
    LIGHT_BLUE_STAINED_GLASS("minecraft:stained_glass", 95, 3),
    YELLOW_STAINED_GLASS("minecraft:stained_glass", 95, 4),
    LIME_STAINED_GLASS("minecraft:stained_glass", 95, 5),
    PINK_STAINED_GLASS("minecraft:stained_glass", 95, 6),
    GRAY_STAINED_GLASS("minecraft:stained_glass", 95, 7),
    LIGHT_GRAY_STAINED_GLASS("minecraft:stained_glass", 95, 8),
    CYAN_STAINED_GLASS("minecraft:stained_glass", 95, 9),
    PURPLE_STAINED_GLASS("minecraft:stained_glass", 95, 0),
    BLUE_STAINED_GLASS("minecraft:stained_glass", 95, 1),
    BROWN_STAINED_GLASS("minecraft:stained_glass", 95, 2),
    GREEN_STAINED_GLASS("minecraft:stained_glass", 95, 3),
    RED_STAINED_GLASS("minecraft:stained_glass", 95, 4),
    BLACK_STAINED_GLASS("minecraft:stained_glass", 95, 5),
    WOODEN_TRAPDOOR("minecraft:trapdoor", new ConcreteRawBlockMeta(0, 15, 3, false, true, true), DoorBlockMeta.class, 96),
    STONE_MONSTER_EGG("minecraft:monster_egg", new ConcreteRawBlockMeta(0, 3.75, 0.75, false, true, false), 97),
    COBBLESTONE_MONSTER_EGG("minecraft:monster_egg", 97, 1),
    STONE_BRICK_MONSTER_EGG("minecraft:monster_egg", 97, 2),
    MOSSY_STONE_BRICK_MONSTER_EGG("minecraft:monster_egg", 97, 3),
    CRACKED_STONE_BRICK_MONSTER_EGG("minecraft:monster_egg", 97, 4),
    CHISELED_STONE_BRICK_MONSTER_EGG("minecraft:monster_egg", 97, 5),
    STONE_BRICKS("minecraft:stonebrick", new ConcreteRawBlockMeta(0, 30, 1.5, false, true, false), 98),
    MOSSY_STONE_BRICKS("minecraft:stonebrick", 98, 1),
    CRACKED_STONE_BRICKS("minecraft:stonebrick", 98, 2),
    CHISELED_STONE_BRICKS("minecraft:stonebrick", 98, 3),
    RED_MUSHROOM_CAP("minecraft:stonebrick", new ConcreteRawBlockMeta(0, 1, 0.2, false, true, false), 99),
    BROWN_MUSHROOM_CAP("minecraft:stonebrick", new ConcreteRawBlockMeta(0, 1, 0.2, false, true, false), 100),
    IRON_BARS("minecraft:iron_bars", new ConcreteRawBlockMeta(0, 30, 5, false, true, true), 101),
    GLASS_PANE("minecraft:glass_pane", new ConcreteRawBlockMeta(0, 1.5, 0.3, false, true, true), 102),
    MELON_BLOCK("minecraft:melon_block", new ConcreteRawBlockMeta(0, 5, 1, false, true, false), 103),
    PUMPKIN_STEM("minecraft:pumpkin_stem", new ConcreteRawBlockMeta(0, 0, 1, false, false, true), StemBlockMeta.class, 104),
    MELON_STEM("minecraft:melon_stem", new ConcreteRawBlockMeta(0, 0, 1, false, false, true), StemBlockMeta.class, 105),
    VINES("minecraft:vine", new ConcreteRawBlockMeta(0, 1, 0.2, false, false, true), 106),
    OAK_FENCE_GATE("minecraft:fence_gate", new ConcreteRawBlockMeta(0, 15, 2, false, false, true), 107),
    BRICK_STAIRS("minecraft:brick_stairs", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 108),
    STONE_BRICK_STAIRS("minecraft:stone_brick_stairs", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 109),
    MYCELIUM("minecraft:mycelium", new ConcreteRawBlockMeta(0, 2.5, 0.6, false, true, false), 110),
    LILY_PAD("minecraft:waterlily", new ConcreteRawBlockMeta(0, 0, 0.6, false, true, true), 111),
    NETHER_BRICK("minecraft:nether_brick", new ConcreteRawBlockMeta(0, 30, 2, false, true, false), 112),
    NETHER_BRICK_FENCE("minecraft:nether_brick_fence", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 113),
    NETHER_BRICK_STAIRS("minecraft:nether_brick_stairs", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 114),
    NETHER_WART("minecraft:nether_wart", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), NetherWartBlockMeta.class, 115),
    ENCHANTMENT_TABLE(
            "minecraft:enchanting_table",
            new ConcreteRawBlockMeta(0, 6000, 5, false, true, true),
            EnchantmentTableBlockMeta.class,
            116),
    BREWING_STAND(
            "minecraft:brewing_stand",
            new ConcreteRawBlockMeta(1, 2.5, 0.5, false, true, true),
            BrewingStandBlockMeta.class,
            117),
    CAULDRON("minecraft:cauldron", new ConcreteRawBlockMeta(0, 10, 2, false, true, true), CauldronBlockMeta.class, 118),
    END_PORTAL("minecraft:end_portal", new ConcreteRawBlockMeta(15, 18000000, -1, false, false, true), 119),
    END_PORTAL_FRAME(
            "minecraft:end_portal_frame",
            new ConcreteRawBlockMeta(0, 18000000, -1, false, true, false),
            EndPortalFrameBlockMeta.class,
            120),
    END_STONE("minecraft:end_stone", new ConcreteRawBlockMeta(0, 45, 3, false, true, false), 121),
    DRAGON_EGG("minecraft:dragon_egg", new ConcreteRawBlockMeta(1, 45, 3, true, true, true), 122),
    REDSTONE_LAMP_INACTIVE("minecraft:redstone_lamp", new ConcreteRawBlockMeta(0, 1.5, 0.3, false, true, true), 123),
    REDSTONE_LAMP_ACTIVE("minecraft:lit_redstone_lamp", new ConcreteRawBlockMeta(15, 1.5, 0.3, false, true, true), 124),
    DOUBLE_OAK_WOOD_SLAB("minecraft:double_wooden_slab", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 125),
    DOUBLE_SPRUCE_WOOD_SLAB("minecraft:double_wooden_slab", 125, 1),
    DOUBLE_BIRCH_WOOD_SLAB("minecraft:double_wooden_slab", 125, 2),
    DOUBLE_JUNGLE_WOOD_SLAB("minecraft:double_wooden_slab", 125, 3),
    DOUBLE_ACACIA_WOOD_SLAB("minecraft:double_wooden_slab", 125, 4),
    DOUBLE_DARK_OAK_WOOD_SLAB("minecraft:double_wooden_slab", 125, 5),
    OAK_WOOD_SLAB("minecraft:wooden_slab", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 126),
    SPRUCE_WOOD_SLAB("minecraft:wooden_slab", 126, 1),
    BIRCH_WOOD_SLAB("minecraft:wooden_slab", 126, 2),
    JUNGLE_WOOD_SLAB("minecraft:wooden_slab", 126, 3),
    ACACIA_WOOD_SLAB("minecraft:wooden_slab", 126, 4),
    DARK_OAK_WOOD_SLAB("minecraft:wooden_slab", 126, 5),
    COCOA("minecraft:cocoa", new ConcreteRawBlockMeta(0, 15, 0.2, false, true, true), CocoaBlockMeta.class, 127),
    SANDSTONE_STAIRS("minecraft:sandstone_stairs", new ConcreteRawBlockMeta(0, 4, 0.8, false, true, true), 128),
    EMERALD_ORE("minecraft:emerald_ore", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 129),
    ENDER_CHEST("minecraft:ender_chest", new ConcreteRawBlockMeta(7, 3000, 22.5, false, true, true), 130),
    TRIPWIRE_HOOK("minecraft:tripwire_hook", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), TripwireBlockMeta.class, 131),
    TRIPWIRE("minecraft:tripwire_hook", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), TripwireBlockMeta.class, 132),
    EMERALD_BLOCK("minecraft:emerald_block", new ConcreteRawBlockMeta(0, 30, 5, false, true, false), 133),
    SPRUCE_WOOD_STAIRS("minecraft:spruce_stairs", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 134),
    BIRCH_WOOD_STAIRS("minecraft:birch_stairs", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 135),
    JUNGLE_WOOD_STAIRS("minecraft:jungle_stairs", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 136),
    COMMAND_BLOCK(
            "minecraft:command_block",
            new ConcreteRawBlockMeta(0, 18000000, -1, false, true, false),
            CommandBlockMeta.class,
            137),
    BEACON("minecraft:beacon", new ConcreteRawBlockMeta(15, 15, 3, false, true, true), BeaconBlockMeta.class, 138),
    COBBLESTONE_WALL("minecraft:cobblestone_wall", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 139),
    MOSSY_COBBLESTONE_WALL("minecraft:cobblestone_wall", 139, 1),
    FLOWER_POT("minecraft:flower_pot", new ConcreteRawBlockMeta(0, 0, 0, false, true, true), FlowerPotBlockMeta.class, 140),
    CARROTS("minecraft:carrots", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), CarrotAndPotatoBlockMeta.class, 141),
    POTATOES("minecraft:potatoes", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), CarrotAndPotatoBlockMeta.class, 142),
    WOODEN_BUTTON(
            "minecraft:wooden_button",
            new ConcreteRawBlockMeta(0, 2.5, 0.5, false, false, true),
            ButtonBlockMeta.class,
            143),
    MOB_HEAD("minecraft:skull", new ConcreteRawBlockMeta(0, 5, 1, false, true, true), MobHeadBlockMeta.class, 144),
    ANVIL("minecraft:anvil", new ConcreteRawBlockMeta(0, 6000, 5, false, true, true), AnvilBlockMeta.class, 145),
    TRAPPED_CHEST(
            "minecraft:trapped_chest",
            new ConcreteRawBlockMeta(0, 12.5, 2.5, false, true, true),
            TrappedChestBlockMeta.class,
            146),
    WEIGHTED_PRESSURE_PLATE_LIGHT("minecraft:light_weighted_pressure_plate", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, false,
            true), WeightedPressurePlateLightBlockMeta.class, 147),
    WEIGHTED_PRESSURE_PLATE_HEAVY("minecraft:heavy_weighted_pressure_plate", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, false,
            true), WeightedPressurePlateHeavyBlockMeta.class, 148),
    REDSTONE_COMPARATOR_INACTIVE(
            "minecraft:unpowered_comparator",
            new ConcreteRawBlockMeta(0, 0, 0, false, true, true),
            RedstoneComparatorBlockMeta.class,
            149),
    REDSTONE_COMPARATOR_ACTIVE(
            "minecraft:powered_comparator",
            new ConcreteRawBlockMeta(0, 0, 0, false, true, true),
            RedstoneComparatorBlockMeta.class,
            150),
    DAYLIGHT_SENSOR(
            "minecraft:daylight_detector",
            new ConcreteRawBlockMeta(0, 1, 0.2, false, true, true),
            DaylightSensorBlockMeta.class,
            151),
    REDSTONE_BLOCK("minecraft:redstone_block", new ConcreteRawBlockMeta(0, 30, 5, false, true, false), 152),
    NETHER_QUARTZ_ORE("minecraft:quartz_ore", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 153),
    HOPPER("minecraft:hopper", new ConcreteRawBlockMeta(0, 15, 3, false, true, true), HopperBlockMeta.class, 154),
    QUARTZ_BLOCK("minecraft:quartz_block", new ConcreteRawBlockMeta(0, 4, 0.8, false, true, false), 155),
    CHISELED_QUARTZ_BLOCK("minecraft:quartz_block", 155, 1),
    PILLAR_QUARTZ_BLOCK("minecraft:quartz_block", 155, 2),
    QUARTZ_STAIRS("minecraft:quartz_stairs", new ConcreteRawBlockMeta(0, 4, 0.8, false, true, true), 156),
    ACTIVATOR_RAIL(
            "minecraft:activator_rail",
            new ConcreteRawBlockMeta(0, 3.5, 0.7, false, true, true),
            ActivatorRailBlockMeta.class,
            157),
    DROPPER(
            "minecraft:dropper",
            new ConcreteRawBlockMeta(0, 17.5, 3.5, false, true, false),
            DispenserAndDropperBlockMeta.class,
            158),
    WHITE_STAINED_CLAY("minecraft:stained_hardened_clay", new ConcreteRawBlockMeta(0, 30, 1.25, false, true, false), 159),
    ORANGE_STAINED_CLAY("minecraft:stained_hardened_clay", 159, 1),
    MAGENTA_STAINED_CLAY("minecraft:stained_hardened_clay", 159, 2),
    LIGHT_BLUE_STAINED_CLAY("minecraft:stained_hardened_clay", 159, 3),
    YELLOW_STAINED_CLAY("minecraft:stained_hardened_clay", 159, 4),
    LIME_STAINED_CLAY("minecraft:stained_hardened_clay", 159, 5),
    PINK_STAINED_CLAY("minecraft:stained_hardened_clay", 159, 6),
    GRAY_STAINED_CLAY("minecraft:stained_hardened_clay", 159, 7),
    LIGHT_GRAY_STAINED_CLAY("minecraft:stained_hardened_clay", 159, 8),
    CYAN_STAINED_CLAY("minecraft:stained_hardened_clay", 159, 9),
    PURPLE_STAINED_CLAY("minecraft:stained_hardened_clay", 159, 0),
    BLUE_STAINED_CLAY("minecraft:stained_hardened_clay", 159, 1),
    BROWN_STAINED_CLAY("minecraft:stained_hardened_clay", 159, 2),
    GREEN_STAINED_CLAY("minecraft:stained_hardened_clay", 159, 3),
    RED_STAINED_CLAY("minecraft:stained_hardened_clay", 159, 4),
    BLACK_STAINED_CLAY("minecraft:stained_hardened_clay", 159, 5),
    WHITE_STAINED_GLASS_PANE("minecraft:stained_glass_pane", new ConcreteRawBlockMeta(0, 1.5, 0.3, false, true, true), 160),
    ORANGE_STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160, 1),
    MAGENTA_STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160, 2),
    LIGHT_BLUE_STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160, 3),
    YELLOW_STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160, 4),
    LIME_STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160, 5),
    PINK_STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160, 6),
    GRAY_STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160, 7),
    LIGHT_GRAY_STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160, 8),
    CYAN_STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160, 9),
    PURPLE_STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160, 0),
    BLUE_STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160, 1),
    BROWN_STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160, 2),
    GREEN_STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160, 3),
    RED_STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160, 4),
    BLACK_STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160, 5),
    ACACIA_LEAVES("minecraft:leaves2", new ConcreteRawBlockMeta(0, 1, 0.2, false, true, true), 161),
    DARK_OAK_LEAVES("minecraft:leaves2", 161, 1),
    ACACIA_WOOD("minecraft:logs2", new ConcreteRawBlockMeta(0, 10, 2, false, true, false), 162),
    DARK_OAK_WOOD("minecraft:logs2", 162, 1),
    ACACIA_WOOD_STAIRS("minecraft:acacia_stairs", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 163),
    DARK_OAK_WOOD_STAIRS("minecraft:dark_oak_stairs", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 164),
    SLIME_BLOCK("minecraft:slime", new ConcreteRawBlockMeta(0, 0, 0, false, true, true), 165),
    BARRIER("minecraft:barrier", new ConcreteRawBlockMeta(0, 18000000, -1, false, true, true), 166),
    IRON_TRAPDOOR("minecraft:iron_trapdoor", new ConcreteRawBlockMeta(0, 25, 3, false, true, true), DoorBlockMeta.class, 167),
    PRISMARINE("minecraft:prismarine", new ConcreteRawBlockMeta(0, 30, 1.5, false, true, false), 168),
    PRISMARINE_BRICKS("minecraft:prismarine", 168, 1),
    DARK_PRISMARINE("minecraft:prismarine", 168, 2),
    SEA_LANTERN("minecraft:sea_lantern", new ConcreteRawBlockMeta(15, 1.5, 0.3, false, true, false), 169),
    HAY_BALE("minecraft:hay_block", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, true, false), 170),
    WHITE_CARPET("minecraft:carpet", new ConcreteRawBlockMeta(0, 0, 0.1, false, false, true), 171),
    ORANGE_CARPET("minecraft:carpet", 171, 1),
    MAGENTA_CARPET("minecraft:carpet", 171, 2),
    LIGHT_BLUE_CARPET("minecraft:carpet", 171, 3),
    YELLOW_CARPET("minecraft:carpet", 171, 4),
    LIME_CARPET("minecraft:carpet", 171, 5),
    PINK_CARPET("minecraft:carpet", 171, 6),
    GRAY_CARPET("minecraft:carpet", 171, 7),
    LIGHT_GRAY_CARPET("minecraft:carpet", 171, 8),
    CYAN_CARPET("minecraft:carpet", 171, 9),
    PURPLE_CARPET("minecraft:carpet", 171, 0),
    BLUE_CARPET("minecraft:carpet", 171, 1),
    BROWN_CARPET("minecraft:carpet", 171, 2),
    GREEN_CARPET("minecraft:carpet", 171, 3),
    RED_CARPET("minecraft:carpet", 171, 4),
    BLACK_CARPET("minecraft:carpet", 171, 5),
    HARDENED_CLAY("minecraft:hardened_clay", new ConcreteRawBlockMeta(0, 30, 1.25, false, true, false), 172),
    BLOCK_OF_COAL("minecraft:coal_block", new ConcreteRawBlockMeta(0, 30, 5, false, true, false), 173),
    PACKED_ICE("minecraft:packed_ice", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, true, false), 174),
    SUNFLOWER("minecraft:double_plant", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 175),
    LILAC("minecraft:double_plant", 175, 1),
    DOUBLE_TALLGRASS("minecraft:double_plant", 175, 2),
    LARGE_FERN("minecraft:double_plant", 175, 3),
    ROSE_BUSH("minecraft:double_plant", 175, 4),
    PEONY("minecraft:double_plant", 175, 5),
    FREESTANDING_BANNER("minecraft:standing_banner", new ConcreteRawBlockMeta(0, 5, Double.NaN, false, false, true), 176),
    WALLMOUNTED_BANNER("minecraft:wall_banner", new ConcreteRawBlockMeta(0, 5, Double.NaN, false, false, true), 177),
    INVERTED_DAYLIGHT_SENSOR("minecraft:daylight_detector_inverted", new ConcreteRawBlockMeta(0, 1, 0.2, false, true, true), 178),
    RED_SANDSTONE("minecraft:red_sandstone", new ConcreteRawBlockMeta(0, 4, 0.8, false, true, false), 179),
    SMOOTH_RED_SANDSTONE("minecraft:red_sandstone", 179, 1),
    CHISELED_RED_SANDSTONE("minecraft:red_sandstone", 179, 2),
    RED_SANDSTONE_STAIRS("minecraft:red_sandstone_stairs", new ConcreteRawBlockMeta(0, 4, 0.8, false, true, true), 180),
    DOUBLE_RED_SANDSTONE_SLAB("minecraft:stone_slab2", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 181),
    RED_SANDSTONE_SLAB("minecraft:double_stone_slab2", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 182),
    SPRUCE_FENCE_GATE("minecraft:spruce_fence_gate", new ConcreteRawBlockMeta(0, 15, 2, false, false, true), 183),
    BIRCH_FENCE_GATE("minecraft:birch_fence_gate", new ConcreteRawBlockMeta(0, 15, 2, false, false, true), 184),
    JUNGLE_FENCE_GATE("minecraft:jungle_fence_gate", new ConcreteRawBlockMeta(0, 15, 2, false, false, true), 185),
    DARK_OAK_FENCE_GATE("minecraft:dark_oak_fence_gate", new ConcreteRawBlockMeta(0, 15, 2, false, false, true), 186),
    ACACIA_FENCE_GATE("minecraft:acacia_fence_gate", new ConcreteRawBlockMeta(0, 15, 2, false, false, true), 187),
    SPRUCE_FENCE("minecraft:spruce_fence", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 188),
    BIRCH_FENCE("minecraft:birch_fence", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 189),
    JUNGLE_FENCE("minecraft:jungle_fence", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 190),
    DARK_OAK_FENCE("minecraft:dark_oak_fence", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 191),
    ACACIA_FENCE("minecraft:acacia_fence", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 192),
    SPRUCE_DOOR_BLOCK("minecraft:spruce_door", new ConcreteRawBlockMeta(0, 15, 3, false, true, true), 193),
    BIRCH_DOOR_BLOCK("minecraft:birch_door", new ConcreteRawBlockMeta(0, 15, 3, false, true, true), 194),
    JUNGLE_DOOR_BLOCK("minecraft:jungle_door", new ConcreteRawBlockMeta(0, 15, 3, false, true, true), 195),
    ACACIA_DOOR_BLOCK("minecraft:acacia_door", new ConcreteRawBlockMeta(0, 15, 3, false, true, true), 196),
    DARK_OAK_DOOR_BLOCK("minecraft:dark_oak_door", new ConcreteRawBlockMeta(0, 15, 3, false, true, true), 197), ;

    private final String materialName;

    private final int materialId;
    private final byte materialData;

    private final RawBlockMeta rawBlockMeta;

    private final Class<? extends BlockMeta> blockMetaClass;

    private BlockMaterial(final String materialName, final RawBlockMeta rawBlockMeta,
            final Class<? extends BlockMeta> blockMetaClass, final int materialId, final byte materialData) {
        this.materialName = materialName;
        this.rawBlockMeta = rawBlockMeta;
        this.blockMetaClass = blockMetaClass;
        this.materialId = materialId;
        this.materialData = materialData;
    }

    private BlockMaterial(final String materialName, final RawBlockMeta rawBlockMeta,
            final Class<? extends BlockMeta> blockMetaClass, final int materialId, final int materialData) {
        this(materialName, rawBlockMeta, blockMetaClass, materialId, (byte) materialData);
    }

    private BlockMaterial(final String materialName, final RawBlockMeta rawBlockMeta,
            final Class<? extends BlockMeta> blockMetaClass, final int materialId) {
        this(materialName, rawBlockMeta, blockMetaClass, materialId, -1);
    }

    private BlockMaterial(final String materialName, final Class<? extends BlockMeta> blockMetaClass, final int materialId,
            final byte materialData) {
        this(materialName, BlockMaterial.getById(materialId).getRawBlockMeta(), blockMetaClass, materialId, materialData);
    }

    private BlockMaterial(final String materialName, final RawBlockMeta rawBlockMeta, final int materialId) {
        this(materialName, rawBlockMeta, BlockMeta.class, materialId);
    }

    private BlockMaterial(final String materialName, final int materialId, final byte materialData) {
        this(materialName, BlockMeta.class, materialId, materialData);
    }

    private BlockMaterial(final String materialName, final int materialId, final int materialData) {
        this(materialName, materialId, (byte) materialData);
    }

    @Deprecated
    public static BlockMaterial getById(final int id, final byte data) {
        for (final BlockMaterial mat : BlockMaterial.values()) {
            if (mat.getMaterialId() == id && (data == -1 || mat.getMaterialData() == data)) {
                return mat;
            }
        }
        return null;
    }

    @Deprecated
    public static BlockMaterial getById(final int id, final int data) {
        return BlockMaterial.getById(id, (byte) data);
    }

    @Deprecated
    public static BlockMaterial getById(final int id) {
        return BlockMaterial.getById(id, -1);
    }

    public static BlockMaterial getByName(final String name) {
        for (final BlockMaterial mat : BlockMaterial.values()) {
            if (mat.getMaterialName().equalsIgnoreCase(name)) {
                return mat;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.material.Material#getMaterialName()
     */
    @Override
    public String getMaterialName() {
        return this.materialName;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.material.Material#getMaterialId()
     */
    @Deprecated
    @Override
    public int getMaterialId() {
        return this.materialId;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.material.Material#getMaterialData()
     */
    @Deprecated
    @Override
    public byte getMaterialData() {
        return this.materialData;
    }

    public RawBlockMeta getRawBlockMeta() {
        return this.rawBlockMeta;
    }

    public Class<? extends BlockMeta> getBlockMetaClass() {
        return this.blockMetaClass;
    }
}
