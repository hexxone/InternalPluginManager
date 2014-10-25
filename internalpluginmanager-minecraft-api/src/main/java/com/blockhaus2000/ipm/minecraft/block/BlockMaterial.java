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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.blockhaus2000.ipm.base.NullsafeMap;
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
import com.blockhaus2000.ipm.minecraft.block.meta.RedstoneComparatorBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.RedstoneRepeaterBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.RedstoneWireBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.SignBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.StemBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.TrappedChestBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.TripwireBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.WeightedPressurePlateHeavyBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.WeightedPressurePlateLightBlockMeta;
import com.blockhaus2000.ipm.minecraft.material.Material;

public enum BlockMaterial implements Material {
    AIR("minecraft:air", 0),
    STONE("minecraft:stone", 1),
    GRANITE("minecraft:stone", 1, 1),
    POLISHED_GRANITE("minecraft:stone", 1, 2),
    DIORITE("minecraft:stone", 1, 3),
    POLISHED_DIORITE("minecraft:stone", 1, 4),
    ANDESITE("minecraft:stone", 1, 5),
    POLISHED_ANDESITE("minecraft:stone", 1, 6),
    GRASS("minecraft:grass", 2),
    DIRT("minecraft:dirt", 3),
    COARSE_DIRT("minecraft:dirt", 3, 1),
    PODZOL("minecraft:dirt", 3, 2),
    COBBLESTONE("minecraft:cobblestone", 4),
    OAK_WOOD_PLANK("minecraft:planks", 5),
    SPRUCE_WOOD_PLANK("minecraft:planks", 5, 1),
    BIRCH_WOOD_PLANK("minecraft:planks", 5, 2),
    JUNGLE_WOOD_PLANK("minecraft:planks", 5, 3),
    ACACIA_WOOD_PLANK("minecraft:planks", 5, 4),
    DARK_OAK_WOOD_PLANK("minecraft:planks", 5, 5),
    OAK_SAPLING("minecraft:sapling", 6),
    SPRUCE_SAPLING("minecraft:sapling", 6, 1),
    BIRCH_SAPLING("minecraft:sapling", 6, 2),
    JUNGLE_SAPLING("minecraft:sapling", 6, 3),
    ACACIA_SAPLING("minecraft:sapling", 6, 4),
    DARK_OAK_SAPLING("minecraft:sapling", 6, 5),
    BEDROCK("minecraft:bedrock", 7),
    FLOWING_WATER("minecraft:flowing_water", 8),
    STILL_WATER("minecraft:water", 9),
    FLOWING_LAVA("minecraft:flowing_lava", 10),
    STILL_LAVA("minecraft:lava", 11),
    SAND("minecraft:sand", 12),
    RED_SAND("minecraft:sand", 12, 1),
    GRAVEL("minecraft:gravel", 13),
    GOLD_ORE("minecraft:gold_ore", 14),
    IRON_ORE("minecraft:iron_ore", 15),
    COAL_ORE("minecraft:coal_ore", 16),
    OAK_WOOD("minecraft:log", 17),
    SPRUCE_WOOD("minecraft:log", 17, 1),
    BIRCH_WOOD("minecraft:log", 17, 2),
    JUNGLE_WOOD("minecraft:log", 17, 3),
    OAK_LEAVES("minecraft:leaves", 18),
    SPRUCE_LEAVES("minecraft:leaves", 18, 1),
    BIRCH_LEAVES("minecraft:leaves", 18, 2),
    JUNGLE_LEAVES("minecraft:leaves", 18, 3),
    SPONGE("minecraft:sponge", 19),
    WET_SPONGE("minecraft:sponge", 19, 1),
    GLASS("minecraft:glass", 20),
    LAPIS_LAZULI_ORE("minecraft:lapis_ore", 21),
    LAPIS_LAZULI_BLOCK("minecraft:lapis_block", 22),
    DISPENSER("minecraft:dispenser", DispenserAndDropperBlockMeta.class, 23),
    SANDSTONE("minecraft:sandstone", 24),
    CHISELED_SANDSTONE("minecraft:sandstone", 24, 1),
    SMOOTH_SANDSTONE("minecraft:sandstone", 24, 2),
    NOTE_BLOCK("minecraft:noteblock", NoteBlockMeta.class, 25),
    BED("minecraft:bed", BedBlockMeta.class, 26),
    POWERED_RAIL("minecraft:golden_rail", PoweredRailBlockMeta.class, 27),
    DETECTOR_RAIL("minecraft:detector_rail", DetectorRailBlockMeta.class, 28),
    STICKY_PISTON("minecraft:sticky_piston", PistonBlockMeta.class, 29),
    COBWEB("minecraft:web", 30),
    DEAD_SHRUB_TALL("minecraft:tallgrass", 31),
    GRASS_TALL("minecraft:tallgrass", 31, 1),
    FERN("minecraft:tallgrass", 31, 2),
    DEAD_SHRUB("minecraft:deadbush", 32),
    PISTON("minecraft:piston", PistonBlockMeta.class, 33),
    PISTON_HEAD("minecraft:piston_head", 34),
    WHITE_WOOL("minecraft:wool", 35),
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
    DANDELION("minecraft:yellow_flower", 37),
    POPPY("minecraft:red_flower", 38),
    BLUE_ORCHID("minecraft:red_flower", 38, 1),
    ALLIUM("minecraft:red_flower", 38, 2),
    AZURE_BLUET("minecraft:red_flower", 38, 3),
    RED_TULIP("minecraft:red_flower", 38, 4),
    ORANGE_TULIP("minecraft:red_flower", 38, 5),
    WHITE_TULIP("minecraft:red_flower", 38, 6),
    PINK_TULIP("minecraft:red_flower", 38, 7),
    OXEYE_DAISY("minecraft:red_flower", 38, 8),
    BROWN_MUSHROOM("minecraft:brown_mushroom", 39),
    RED_MUSHROOM("minecraft:red_mushroom", 40),
    GOLD_BLOCK("minecraft:gold_block", 41),
    IRON_BLOCK("minecraft:iron_block", 42),
    DOUBLE_STONE_SLAB("minecraft:double_stone_slab", 43),
    DOUBLE_SANDSTONE_SLAB("minecraft:double_stone_slab", 43, 1),
    DOUBLE_WOODEN_SLAB("minecraft:double_stone_slab", 43, 2),
    DOUBLE_COBBLESTONE_SLAB("minecraft:double_stone_slab", 43, 3),
    DOUBLE_BRICK_SLAB("minecraft:double_stone_slab", 43, 4),
    DOUBLE_STONE_BRICK_SLAB("minecraft:double_stone_slab", 43, 5),
    DOUBLE_NETHER_BRICK_SLAB("minecraft:double_stone_slab", 43, 6),
    DOUBLE_QUARTZ_SLAB("minecraft:double_stone_slab", 43, 7),
    STONE_SLAB("minecraft:stone_slab", 44),
    SANDSTONE_SLAB("minecraft:stone_slab", 44, 1),
    WOODEN_SLAB("minecraft:stone_slab", 44, 2),
    COBBLESTONE_SLAB("minecraft:stone_slab", 44, 3),
    BRICK_SLAB("minecraft:stone_slab", 44, 4),
    STONE_BRICK_SLAB("minecraft:stone_slab", 44, 5),
    NETHER_BRICK_SLAB("minecraft:stone_slab", 44, 6),
    QUARTZ_SLAB("minecraft:stone_slab", 44, 7),
    BRICKS("minecraft:brick_block", 45),
    TNT("minecraft:tnt", 46),
    BOOKSHELF("minecraft:bookshelf", 47),
    MOSS_STONE("minecraft:mossy_cobblestone", 48),
    OBSIDIAN("minecraft:obsidian", 49),
    TORCH("minecraft:torch", 50),
    FIRE("minecraft:fire", 51),
    MONSTER_SPAWNER("minecraft:mob_spawner", MonsterSpawnerBlockMeta.class, 52),
    OAK_WOOD_STAIRS("minecraft:oak_stairs", 53),
    CHEST("minecraft:chest", ChestBlockMeta.class, 54),
    REDSTONE_WIRE("minecraft:redstone_wire", RedstoneWireBlockMeta.class, 55),
    DIAMOND_ORE("minecraft:diamond_ore", 56),
    DIAMOND_BLOCK("minecraft:diamond_block", 57),
    CRAFTING_TABLE("minecraft:crafting_table", 58),
    WHEAT_CROPS("minecraft:wheat", 59),
    FARMLAND("minecraft:farmland", 60),
    FURNACE("minecraft:furnace", FurnaceBlockMeta.class, 61),
    BURNING_FURNACE("minecraft:lit_furnace", FurnaceBlockMeta.class, 62),
    STANDING_SIGN_BLOCK("minecraft:standing_sign", SignBlockMeta.class, 63),
    OAK_DOOR_BLOCK("minecraft:wooden_door", DoorBlockMeta.class, 64),
    LADDER("minecraft:ladder", 65),
    RAIL("minecraft:rail", 66),
    COBBLESTONE_STAIRS("minecraft:stone_stairs", 67),
    WALL_MOUNTED_SIGN_BLOCK("minecraft:wall_sign", SignBlockMeta.class, 68),
    LEVER("minecraft:lever", LeverBlockMeta.class, 69),
    STONE_PRESSURE_PLATE("minecraft:stone_pressure_plate", PressurePlateBlockMeta.class, 70),
    IRON_DOOR_BLOCK("minecraft:iron_door", DoorBlockMeta.class, 71),
    WOODEN_PRESSURE_PLATE("minecraft:wooden_pressure_plate", PressurePlateBlockMeta.class, 72),
    REDSTONE_ORE("minecraft:redstone_ore", 73),
    GLOWING_REDSTONE_ORE("minecraft:lit_redstone_ore", 74),
    REDSTONE_TORCH_OFF("minecraft:unlit_redstone_torch", 75),
    REDSTONE_TORCH_ON("minecraft:redstone_torch", 76),
    STONE_BUTTON("minecraft:stone_button", ButtonBlockMeta.class, 77),
    SNOW("minecraft:snow_layer", 78),
    ICE("minecraft:ice", 79),
    SNOW_BLOCK("minecraft:snow", 80),
    CACTUS("minecraft:cactus", 81),
    CLAY("minecraft:clay", 82),
    SUGAR_CANES("minecraft:reeds", 83),
    JUKEBOX("minecraft:jukebox", JukeboxBlockMeta.class, 84),
    OAK_FENCE("minecraft:fence", 85),
    PUMPKIN("minecraft:pumpkin", 86),
    NETHERRACK("minecraft:netherrack", 87),
    SOUL_SAND("minecraft:soul_sand", 88),
    GLOWSTONE("minecraft:glowstone", 89),
    NETHER_PORTAL("minecraft:portal", 90),
    JACK_O_LANTERN("minecraft:lit_pumpkin", 91),
    CAKE_BLOCK("minecraft:cake", CakeBlockMeta.class, 92),
    REDSTONE_REPEATER_BLOCK_OFF("minecraft:unpowered_repeater", RedstoneRepeaterBlockMeta.class, 93),
    REDSTONE_REPEATER_BLOCK_ON("minecraft:powered_repeater", RedstoneRepeaterBlockMeta.class, 94),
    WHITE_STAINED_GLASS("minecraft:stained_glass", 95),
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
    WOODEN_TRAPDOOR("minecraft:trapdoor", DoorBlockMeta.class, 96),
    STONE_MONSTER_EGG("minecraft:monster_egg", 97),
    COBBLESTONE_MONSTER_EGG("minecraft:monster_egg", 97, 1),
    STONE_BRICK_MONSTER_EGG("minecraft:monster_egg", 97, 2),
    MOSSY_STONE_BRICK_MONSTER_EGG("minecraft:monster_egg", 97, 3),
    CRACKED_STONE_BRICK_MONSTER_EGG("minecraft:monster_egg", 97, 4),
    CHISELED_STONE_BRICK_MONSTER_EGG("minecraft:monster_egg", 97, 5),
    STONE_BRICKS("minecraft:stonebrick", 98),
    MOSSY_STONE_BRICKS("minecraft:stonebrick", 98, 1),
    CRACKED_STONE_BRICKS("minecraft:stonebrick", 98, 2),
    CHISELED_STONE_BRICKS("minecraft:stonebrick", 98, 3),
    RED_MUSHROOM_CAP("minecraft:stonebrick", 99),
    BROWN_MUSHROOM_CAP("minecraft:stonebrick", 100),
    IRON_BARS("minecraft:iron_bars", 101),
    GLASS_PANE("minecraft:glass_pane", 102),
    MELON_BLOCK("minecraft:melon_block", 103),
    PUMPKIN_STEM("minecraft:pumpkin_stem", StemBlockMeta.class, 104),
    MELON_STEM("minecraft:melon_stem", StemBlockMeta.class, 105),
    VINES("minecraft:vine", 106),
    OAK_FENCE_GATE("minecraft:fence_gate", 107),
    BRICK_STAIRS("minecraft:brick_stairs", 108),
    STONE_BRICK_STAIRS("minecraft:stone_brick_stairs", 109),
    MYCELIUM("minecraft:mycelium", 110),
    LILY_PAD("minecraft:waterlily", 111),
    NETHER_BRICK("minecraft:nether_brick", 112),
    NETHER_BRICK_FENCE("minecraft:nether_brick_fence", 113),
    NETHER_BRICK_STAIRS("minecraft:nether_brick_stairs", 114),
    NETHER_WART("minecraft:nether_wart", NetherWartBlockMeta.class, 115),
    ENCHANTMENT_TABLE("minecraft:enchanting_table", EnchantmentTableBlockMeta.class, 116),
    BREWING_STAND("minecraft:brewing_stand", BrewingStandBlockMeta.class, 117),
    CAULDRON("minecraft:cauldron", CauldronBlockMeta.class, 118),
    END_PORTAL("minecraft:end_portal", 119),
    END_PORTAL_FRAME("minecraft:end_portal_frame", EndPortalFrameBlockMeta.class, 120),
    END_STONE("minecraft:end_stone", 121),
    DRAGON_EGG("minecraft:dragon_egg", 122),
    REDSTONE_LAMP_INACTIVE("minecraft:redstone_lamp", 123),
    REDSTONE_LAMP_ACTIVE("minecraft:lit_redstone_lamp", 124),
    DOUBLE_OAK_WOOD_SLAB("minecraft:double_wooden_slab", 125),
    DOUBLE_SPRUCE_WOOD_SLAB("minecraft:double_wooden_slab", 125, 1),
    DOUBLE_BIRCH_WOOD_SLAB("minecraft:double_wooden_slab", 125, 2),
    DOUBLE_JUNGLE_WOOD_SLAB("minecraft:double_wooden_slab", 125, 3),
    DOUBLE_ACACIA_WOOD_SLAB("minecraft:double_wooden_slab", 125, 4),
    DOUBLE_DARK_OAK_WOOD_SLAB("minecraft:double_wooden_slab", 125, 5),
    OAK_WOOD_SLAB("minecraft:wooden_slab", 126),
    SPRUCE_WOOD_SLAB("minecraft:wooden_slab", 126, 1),
    BIRCH_WOOD_SLAB("minecraft:wooden_slab", 126, 2),
    JUNGLE_WOOD_SLAB("minecraft:wooden_slab", 126, 3),
    ACACIA_WOOD_SLAB("minecraft:wooden_slab", 126, 4),
    DARK_OAK_WOOD_SLAB("minecraft:wooden_slab", 126, 5),
    COCOA("minecraft:cocoa", CocoaBlockMeta.class, 127),
    SANDSTONE_STAIRS("minecraft:sandstone_stairs", 128),
    EMERALD_ORE("minecraft:emerald_ore", 129),
    ENDER_CHEST("minecraft:ender_chest", 130),
    TRIPWIRE_HOOK("minecraft:tripwire_hook", TripwireBlockMeta.class, 131),
    TRIPWIRE("minecraft:tripwire_hook", TripwireBlockMeta.class, 132),
    EMERALD_BLOCK("minecraft:emerald_block", 133),
    SPRUCE_WOOD_STAIRS("minecraft:spruce_stairs", 134),
    BIRCH_WOOD_STAIRS("minecraft:birch_stairs", 135),
    JUNGLE_WOOD_STAIRS("minecraft:jungle_stairs", 136),
    COMMAND_BLOCK("minecraft:command_block", CommandBlockMeta.class, 137),
    BEACON("minecraft:beacon", BeaconBlockMeta.class, 138),
    COBBLESTONE_WALL("minecraft:cobblestone_wall", 139),
    MOSSY_COBBLESTONE_WALL("minecraft:cobblestone_wall", 139, 1),
    FLOWER_POT("minecraft:flower_pot", FlowerPotBlockMeta.class, 140),
    CARROTS("minecraft:carrots", CarrotAndPotatoBlockMeta.class, 141),
    POTATOES("minecraft:potatoes", CarrotAndPotatoBlockMeta.class, 142),
    WOODEN_BUTTON("minecraft:wooden_button", ButtonBlockMeta.class, 143),
    MOB_HEAD("minecraft:skull", MobHeadBlockMeta.class, 144),
    ANVIL("minecraft:anvil", AnvilBlockMeta.class, 145),
    TRAPPED_CHEST("minecraft:trapped_chest", TrappedChestBlockMeta.class, 146),
    WEIGHTED_PRESSURE_PLATE_LIGHT("minecraft:light_weighted_pressure_plate", WeightedPressurePlateLightBlockMeta.class, 147),
    WEIGHTED_PRESSURE_PLATE_HEAVY("minecraft:heavy_weighted_pressure_plate", WeightedPressurePlateHeavyBlockMeta.class, 148),
    REDSTONE_COMPARATOR_INACTIVE("minecraft:unpowered_comparator", RedstoneComparatorBlockMeta.class, 149),
    REDSTONE_COMPARATOR_ACTIVE("minecraft:powered_comparator", RedstoneComparatorBlockMeta.class, 150),
    DAYLIGHT_SENSOR("minecraft:daylight_detector", DaylightSensorBlockMeta.class, 151),
    REDSTONE_BLOCK("minecraft:redstone_block", 152),
    NETHER_QUARTZ_ORE("minecraft:quartz_ore", 153),
    HOPPER("minecraft:hopper", HopperBlockMeta.class, 154),
    QUARTZ_BLOCK("minecraft:quartz_block", 155),
    CHISELED_QUARTZ_BLOCK("minecraft:quartz_block", 155, 1),
    PILLAR_QUARTZ_BLOCK("minecraft:quartz_block", 155, 2),
    QUARTZ_STAIRS("minecraft:quartz_stairs", 156),
    ACTIVATOR_RAIL("minecraft:activator_rail", ActivatorRailBlockMeta.class, 157),
    DROPPER("minecraft:dropper", DispenserAndDropperBlockMeta.class, 158),
    WHITE_STAINED_CLAY("minecraft:stained_hardened_clay", 159),
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
    WHITE_STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160),
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
    ACACIA_LEAVES("minecraft:leaves2", 161),
    DARK_OAK_LEAVES("minecraft:leaves2", 161, 1),
    ACACIA_WOOD("minecraft:logs2", 162),
    DARK_OAK_WOOD("minecraft:logs2", 162, 1),
    ACACIA_WOOD_STAIRS("minecraft:acacia_stairs", 163),
    DARK_OAK_WOOD_STAIRS("minecraft:dark_oak_stairs", 164),
    SLIME_BLOCK("minecraft:slime", 165),
    BARRIER("minecraft:barrier", 166),
    IRON_TRAPDOOR("minecraft:iron_trapdoor", DoorBlockMeta.class, 167),
    PRISMARINE("minecraft:prismarine", 168),
    PRISMARINE_BRICKS("minecraft:prismarine", 168, 1),
    DARK_PRISMARINE("minecraft:prismarine", 168, 2),
    SEA_LANTERN("minecraft:sea_lantern", 169),
    HAY_BALE("minecraft:hay_block", 170),
    WHITE_CARPET("minecraft:carpet", 171),
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
    HARDENED_CLAY("minecraft:hardened_clay", 172),
    BLOCK_OF_COAL("minecraft:coal_block", 173),
    PACKED_ICE("minecraft:packed_ice", 174),
    SUNFLOWER("minecraft:double_plant", 175),
    LILAC("minecraft:double_plant", 175, 1),
    DOUBLE_TALLGRASS("minecraft:double_plant", 175, 2),
    LARGE_FERN("minecraft:double_plant", 175, 3),
    ROSE_BUSH("minecraft:double_plant", 175, 4),
    PEONY("minecraft:double_plant", 175, 5),
    FREESTANDING_BANNER("minecraft:standing_banner", 176),
    WALLMOUNTED_BANNER("minecraft:wall_banner", 177),
    INVERTED_DAYLIGHT_SENSOR("minecraft:daylight_detector_inverted", 178),
    RED_SANDSTONE("minecraft:red_sandstone", 179),
    SMOOTH_RED_SANDSTONE("minecraft:red_sandstone", 179, 1),
    CHISELED_RED_SANDSTONE("minecraft:red_sandstone", 179, 2),
    RED_SANDSTONE_STAIRS("minecraft:red_sandstone_stairs", 180),
    DOUBLE_RED_SANDSTONE_SLAB("minecraft:stone_slab2", 181),
    RED_SANDSTONE_SLAB("minecraft:double_stone_slab2", 182),
    SPRUCE_FENCE_GATE("minecraft:spruce_fence_gate", 183),
    BIRCH_FENCE_GATE("minecraft:birch_fence_gate", 184),
    JUNGLE_FENCE_GATE("minecraft:jungle_fence_gate", 185),
    DARK_OAK_FENCE_GATE("minecraft:dark_oak_fence_gate", 186),
    ACACIA_FENCE_GATE("minecraft:acacia_fence_gate", 187),
    SPRUCE_FENCE("minecraft:spruce_fence", 188),
    BIRCH_FENCE("minecraft:birch_fence", 189),
    JUNGLE_FENCE("minecraft:jungle_fence", 190),
    DARK_OAK_FENCE("minecraft:dark_oak_fence", 191),
    ACACIA_FENCE("minecraft:acacia_fence", 192),
    SPRUCE_DOOR_BLOCK("minecraft:spruce_door", 193),
    BIRCH_DOOR_BLOCK("minecraft:birch_door", 194),
    JUNGLE_DOOR_BLOCK("minecraft:jungle_door", 195),
    ACACIA_DOOR_BLOCK("minecraft:acacia_door", 196),
    DARK_OAK_DOOR_BLOCK("minecraft:dark_oak_door", 197);

    private static final Map<Integer, Map<Byte, BlockMaterial>> LOOKUP_MAP;
    private static final Map<String, BlockMaterial> NAME_LOOKUP_MAP;

    private final String materialName;

    private final int materialId;
    private final byte materialData;

    private final Class<? extends BlockMeta> blockMetaClass;

    static {
        final Map<Integer, Map<Byte, BlockMaterial>> rawLookupMap = new NullsafeMap<Integer, Map<Byte, BlockMaterial>>(
                new HashMap<Byte, BlockMaterial>());
        final Map<String, BlockMaterial> nameLookupMap = new HashMap<String, BlockMaterial>();

        for (final BlockMaterial blockMaterial : BlockMaterial.values()) {
            rawLookupMap.get(blockMaterial.getMaterialId()).put(blockMaterial.getMaterialData(), blockMaterial);
            nameLookupMap.put(blockMaterial.getMaterialName(), blockMaterial);
        }

        final Map<Integer, Map<Byte, BlockMaterial>> lookupMap = new HashMap<Integer, Map<Byte, BlockMaterial>>();
        for (final Map.Entry<Integer, Map<Byte, BlockMaterial>> entry : rawLookupMap.entrySet()) {
            lookupMap.put(entry.getKey(), Collections.unmodifiableMap(entry.getValue()));
        }

        LOOKUP_MAP = Collections.unmodifiableMap(lookupMap);
        NAME_LOOKUP_MAP = Collections.unmodifiableMap(nameLookupMap);
    }

    private BlockMaterial(final String materialName, final Class<? extends BlockMeta> blockMetaClass, final int materialId,
            final byte materialData) {
        this.materialName = materialName;
        this.blockMetaClass = blockMetaClass;
        this.materialId = materialId;
        this.materialData = materialData;
    }

    // TODO: Has to be implemented!
    // public abstract BlockMeta createDefaultBlockMeta();

    private BlockMaterial(final String materialName, final Class<? extends BlockMeta> blockMetaClass, final int materialId,
            final int materialData) {
        this(materialName, blockMetaClass, materialId, (byte) materialData);
    }

    private BlockMaterial(final String materialName, final Class<? extends BlockMeta> blockMetaClass, final int materialId) {
        this(materialName, blockMetaClass, materialId, -1);
    }

    private BlockMaterial(final String materialName, final int materialId, final byte materialData) {
        this(materialName, BlockMeta.class, materialId, materialData);
    }

    private BlockMaterial(final String materialName, final int materialId, final int materialData) {
        this(materialName, materialId, (byte) materialData);
    }

    private BlockMaterial(final String materialName, final int materialId) {
        this(materialName, materialId, -1);
    }

    @Deprecated
    public static BlockMaterial getById(final int id, final byte data) {
        return BlockMaterial.LOOKUP_MAP.get(id).get(data);
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
        return BlockMaterial.NAME_LOOKUP_MAP.get(name);
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

    public Class<? extends BlockMeta> getBlockMetaClass() {
        return this.blockMetaClass;
    }
}
