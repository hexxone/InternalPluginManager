/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014-2015 Fabian Damken
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
import com.blockhaus2000.ipm.minecraft.block.meta.DetectorRailBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.DispenserAndDropperBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.DoorBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.EndPortalFrameBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.FarmlandBlockMeta;
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
import com.blockhaus2000.ipm.minecraft.block.meta.TripwireHookBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteRawBlockMeta;
import com.blockhaus2000.ipm.minecraft.material.Material;

/**
 * All {@link Material}s that are blocks.
 *
 */
public enum BlockMaterial implements Material {
    /**
     * Air.
     *
     */
    AIR("minecraft:air", new ConcreteRawBlockMeta(0, 0, -1, false, false, true), 0),
    /**
     * Stone.
     *
     */
    STONE("minecraft:stone", new ConcreteRawBlockMeta(0, 30, 1.5, false, true, false), 1),
    /**
     * Granite.
     *
     */
    GRANITE("minecraft:stone", BlockMaterial.STONE, 1, 1),
    /**
     * Polished granite.
     *
     */
    POLISHED_GRANITE("minecraft:stone", BlockMaterial.STONE, 1, 2),
    /**
     * Diorite.
     *
     */
    DIORITE("minecraft:stone", BlockMaterial.STONE, 1, 3),
    /**
     * Polished diorite.
     *
     */
    POLISHED_DIORITE("minecraft:stone", BlockMaterial.STONE, 1, 4),
    /**
     * Andesite.
     *
     */
    ANDESITE("minecraft:stone", BlockMaterial.STONE, 1, 5),
    /**
     * Polished andesite.
     *
     */
    POLISHED_ANDESITE("minecraft:stone", BlockMaterial.STONE, 1, 6),
    /**
     * Grass.
     *
     */
    GRASS("minecraft:grass", new ConcreteRawBlockMeta(0, 3, 0.6, false, true, false), 2),
    /**
     * Dirt.
     *
     */
    DIRT("minecraft:dirt", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, true, false), 3),
    /**
     * Coarse dirt.
     *
     */
    COARSE_DIRT("minecraft:dirt", BlockMaterial.DIRT, 3, 1),
    /**
     * Podzol.
     *
     */
    PODZOL("minecraft:dirt", BlockMaterial.DIRT, 3, 2),
    /**
     * Cobblestone.
     *
     */
    COBBLESTONE("minecraft:cobblestone", new ConcreteRawBlockMeta(0, 30, 2, false, true, false), 4),
    /**
     * Oak wood plank.
     *
     */
    OAK_WOOD_PLANK("minecraft:planks", new ConcreteRawBlockMeta(0, 15, 2, false, true, false), 5),
    /**
     * Spruce wood plank.
     *
     */
    SPRUCE_WOOD_PLANK("minecraft:planks", BlockMaterial.OAK_WOOD_PLANK, 5, 1),
    /**
     * Birch wood plank.
     *
     */
    BIRCH_WOOD_PLANK("minecraft:planks", BlockMaterial.OAK_WOOD_PLANK, 5, 2),
    /**
     * Jungle wood plank.
     *
     */
    JUNGLE_WOOD_PLANK("minecraft:planks", BlockMaterial.OAK_WOOD_PLANK, 5, 3),
    /**
     * Acacia wood plank.
     *
     */
    ACACIA_WOOD_PLANK("minecraft:planks", BlockMaterial.OAK_WOOD_PLANK, 5, 4),
    /**
     * Dark oak wood plank.
     *
     */
    DARK_OAK_WOOD_PLANK("minecraft:planks", BlockMaterial.OAK_WOOD_PLANK, 5, 5),
    /**
     * Oak sapling.
     *
     */
    OAK_SAPLING("minecraft:sapling", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 6),
    /**
     * Spruce sapling.
     *
     */
    SPRUCE_SAPLING("minecraft:sapling", OAK_SAPLING, 6, 1),
    /**
     * Birch sapling.
     *
     */
    BIRCH_SAPLING("minecraft:sapling", OAK_SAPLING, 6, 2),
    /**
     * Jungle sapling.
     *
     */
    JUNGLE_SAPLING("minecraft:sapling", OAK_SAPLING, 6, 3),
    /**
     * Acacia sapling.
     *
     */
    ACACIA_SAPLING("minecraft:sapling", OAK_SAPLING, 6, 4),
    /**
     * Dark oak sapling.
     *
     */
    DARK_OAK_SAPLING("minecraft:sapling", OAK_SAPLING, 6, 5),
    /**
     * Bedrock.
     *
     */
    BEDROCK("minecraft:bedrock", new ConcreteRawBlockMeta(0, 18000000, -1, false, true, false), 7),
    /**
     * Flowing water.
     *
     */
    FLOWING_WATER("minecraft:flowing_water", new ConcreteRawBlockMeta(0, 500, Double.NaN, false, false, true), 8),
    /**
     * Still water.
     *
     */
    STILL_WATER("minecraft:water", new ConcreteRawBlockMeta(0, 500, 100, true, false, true), 9),
    /**
     * Flowing lava.
     *
     */
    FLOWING_LAVA("minecraft:flowing_lava", new ConcreteRawBlockMeta(15, 0, Double.NaN, false, false, true), 10),
    /**
     * Still lava.
     *
     */
    STILL_LAVA("minecraft:lava", new ConcreteRawBlockMeta(15, 500, 100, true, false, true), 11),
    /**
     * Sand.
     *
     */
    SAND("minecraft:sand", new ConcreteRawBlockMeta(0, 2.5, 0.5, true, true, false), 12),
    /**
     * Red sand.
     *
     */
    RED_SAND("minecraft:sand", SAND, 12, 1),
    /**
     * Gravel.
     *
     */
    GRAVEL("minecraft:gravel", new ConcreteRawBlockMeta(0, 3, 0.6, true, true, false), 13),
    /**
     * Gold ore.
     *
     */
    GOLD_ORE("minecraft:gold_ore", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 14),
    /**
     * Iron ore.
     *
     */
    IRON_ORE("minecraft:iron_ore", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 15),
    /**
     * Coal ore.
     *
     */
    COAL_ORE("minecraft:coal_ore", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 16),
    /**
     * Oak wood.
     *
     */
    OAK_WOOD("minecraft:log", new ConcreteRawBlockMeta(0, 10, 2, false, true, false), 17),
    /**
     * Spruce wood.
     *
     */
    SPRUCE_WOOD("minecraft:log", OAK_WOOD, 17, 1),
    /**
     * Birch wood.
     *
     */
    BIRCH_WOOD("minecraft:log", OAK_WOOD, 17, 2),
    /**
     * Jungle wood.
     *
     */
    JUNGLE_WOOD("minecraft:log", OAK_WOOD, 17, 3),
    /**
     * Oak leaves.
     *
     */
    OAK_LEAVES("minecraft:leaves", new ConcreteRawBlockMeta(0, 1, 0.2, false, false, true), 18),
    /**
     * Spruce leaves.
     *
     */
    SPRUCE_LEAVES("minecraft:leaves", OAK_LEAVES, 18, 1),
    /**
     * Birch leaves.
     *
     */
    BIRCH_LEAVES("minecraft:leaves", OAK_LEAVES, 18, 2),
    /**
     * Jungle leaves.
     *
     */
    JUNGLE_LEAVES("minecraft:leaves", OAK_LEAVES, 18, 3),
    /**
     * Sponge.
     *
     */
    SPONGE("minecraft:sponge", new ConcreteRawBlockMeta(0, 3, 0.6, false, true, false), 19),
    /**
     * Wet sponge.
     *
     */
    WET_SPONGE("minecraft:sponge", SPONGE, 19, 1),
    /**
     * Glass.
     *
     */
    GLASS("minecraft:glass", new ConcreteRawBlockMeta(0, 1.5, 0.3, false, true, true), 20),
    /**
     * Lapis lazuli ore.
     *
     */
    LAPIS_LAZULI_ORE("minecraft:lapis_ore", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 21),
    /**
     * Lapis lazuli block.
     *
     */
    LAPIS_LAZULI_BLOCK("minecraft:lapis_block", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 22),
    /**
     * Dispenser.
     *
     */
    DISPENSER(
            "minecraft:dispenser",
            new ConcreteRawBlockMeta(0, 17.5, 3.5, false, true, false),
            DispenserAndDropperBlockMeta.class,
            23),
    /**
     * Sandstone.
     *
     */
    SANDSTONE("minecraft:sandstone", new ConcreteRawBlockMeta(0, 4, 0.8, false, true, false), 24),
    /**
     * Chiseled sandstone.
     *
     */
    CHISELED_SANDSTONE("minecraft:sandstone", SANDSTONE, 24, 1),
    /**
     * Smooth sandstone.
     *
     */
    SMOOTH_SANDSTONE("minecraft:sandstone", SANDSTONE, 24, 2),
    /**
     * Note block.
     *
     */
    NOTE_BLOCK("minecraft:noteblock", new ConcreteRawBlockMeta(0, 4, 0.8, false, true, false), NoteBlockMeta.class, 25),
    /**
     * Bed.
     *
     */
    BED("minecraft:bed", new ConcreteRawBlockMeta(0, 1, 0.2, false, true, true), BedBlockMeta.class, 26),
    /**
     * Powered rail.
     *
     */
    POWERED_RAIL(
            "minecraft:golden_rail",
            new ConcreteRawBlockMeta(0, 3.5, 0.7, false, false, true),
            PoweredRailBlockMeta.class,
            27),
    /**
     * Detector rail.
     *
     */
    DETECTOR_RAIL(
            "minecraft:detector_rail",
            new ConcreteRawBlockMeta(0, 3.5, 0.7, false, false, true),
            DetectorRailBlockMeta.class,
            28),
    /**
     * Sticky piston.
     *
     */
    STICKY_PISTON("minecraft:sticky_piston", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, true, true), PistonBlockMeta.class, 29),
    /**
     * Cobweb.
     *
     */
    COBWEB("minecraft:web", new ConcreteRawBlockMeta(0, 20, 4, false, false, true), 30),
    /**
     * Grass tall.
     *
     */
    GRASS_TALL("minecraft:tallgrass", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 31),
    /**
     * Grass tall 2.
     *
     */
    GRASS_TALL_2("minecraft:tallgrass", GRASS_TALL, 31, 1),
    /**
     * Fern.
     *
     */
    FERN("minecraft:tallgrass", GRASS_TALL, 31, 2),
    /**
     * Dead shrub.
     *
     */
    DEAD_SHRUB("minecraft:deadbush", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 32),
    /**
     * Piston.
     *
     */
    PISTON("minecraft:piston", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, true, true), PistonBlockMeta.class, 33),
    /**
     * Piston head.
     *
     */
    PISTON_HEAD("minecraft:piston_head", new ConcreteRawBlockMeta(0, 2.5, Double.NaN, false, false, true), 34),
    /**
     * White wool.
     *
     */
    WHITE_WOOL("minecraft:wool", new ConcreteRawBlockMeta(0, 4, 0.8, false, true, false), 35),
    /**
     * Orange wool.
     *
     */
    ORANGE_WOOL("minecraft:wool", WHITE_WOOL, 35, 1),
    /**
     * Magenta wool.
     *
     */
    MAGENTA_WOOL("minecraft:wool", WHITE_WOOL, 35, 2),
    /**
     * Light blue wool.
     *
     */
    LIGHT_BLUE_WOOL("minecraft:wool", WHITE_WOOL, 35, 3),
    /**
     * Yellow wool.
     *
     */
    YELLOW_WOOL("minecraft:wool", WHITE_WOOL, 35, 4),
    /**
     * Lime wool.
     *
     */
    LIME_WOOL("minecraft:wool", WHITE_WOOL, 35, 5),
    /**
     * Pink wool.
     *
     */
    PINK_WOOL("minecraft:wool", WHITE_WOOL, 35, 6),
    /**
     * Gray wool.
     *
     */
    GRAY_WOOL("minecraft:wool", WHITE_WOOL, 35, 7),
    /**
     * Light gray wool.
     *
     */
    LIGHT_GRAY_WOOL("minecraft:wool", WHITE_WOOL, 35, 8),
    /**
     * Cyan wool.
     *
     */
    CYAN_WOOL("minecraft:wool", WHITE_WOOL, 35, 9),
    /**
     * Purple wool.
     *
     */
    PURPLE_WOOL("minecraft:wool", WHITE_WOOL, 35, 0),
    /**
     * Blue wool.
     *
     */
    BLUE_WOOL("minecraft:wool", WHITE_WOOL, 35, 1),
    /**
     * Brown wool.
     *
     */
    BROWN_WOOL("minecraft:wool", WHITE_WOOL, 35, 2),
    /**
     * Green wool.
     *
     */
    GREEN_WOOL("minecraft:wool", WHITE_WOOL, 35, 3),
    /**
     * Red wool.
     *
     */
    RED_WOOL("minecraft:wool", WHITE_WOOL, 35, 4),
    /**
     * Black wool.
     *
     */
    BLACK_WOOL("minecraft:wool", WHITE_WOOL, 35, 5),
    /**
     * Moved block.
     *
     */
    MOVED_BLOCK("minecraft:piston_extension", new ConcreteRawBlockMeta(0, 0, Double.NaN, false, false, true), 36),
    /**
     * Dandelion.
     *
     */
    DANDELION("minecraft:yellow_flower", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 37),
    /**
     * Poppy.
     *
     */
    POPPY("minecraft:red_flower", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 38),
    /**
     * Blue orchid.
     *
     */
    BLUE_ORCHID("minecraft:red_flower", POPPY, 38, 1),
    /**
     * Allium.
     *
     */
    ALLIUM("minecraft:red_flower", POPPY, 38, 2),
    /**
     * Azure bluet.
     *
     */
    AZURE_BLUET("minecraft:red_flower", POPPY, 38, 3),
    /**
     * Red tulip.
     *
     */
    RED_TULIP("minecraft:red_flower", POPPY, 38, 4),
    /**
     * Orange tulip.
     *
     */
    ORANGE_TULIP("minecraft:red_flower", POPPY, 38, 5),
    /**
     * White tulip.
     *
     */
    WHITE_TULIP("minecraft:red_flower", POPPY, 38, 6),
    /**
     * Pink tulip.
     *
     */
    PINK_TULIP("minecraft:red_flower", POPPY, 38, 7),
    /**
     * Oxeye daisy.
     *
     */
    OXEYE_DAISY("minecraft:red_flower", POPPY, 38, 8),
    /**
     * Brown mushroom.
     *
     */
    BROWN_MUSHROOM("minecraft:brown_mushroom", new ConcreteRawBlockMeta(1, 0, 0, false, false, true), 39),
    /**
     * Red mushroom.
     *
     */
    RED_MUSHROOM("minecraft:red_mushroom", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 40),
    /**
     * Gold block.
     *
     */
    GOLD_BLOCK("minecraft:gold_block", new ConcreteRawBlockMeta(0, 30, 3, false, true, false), 41),
    /**
     * Iron block.
     *
     */
    IRON_BLOCK("minecraft:iron_block", new ConcreteRawBlockMeta(0, 30, 5, false, true, false), 42),
    /**
     * Double stone slab.
     *
     */
    DOUBLE_STONE_SLAB("minecraft:double_stone_slab", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 43),
    /**
     * Double sandstone slab.
     *
     */
    DOUBLE_SANDSTONE_SLAB("minecraft:double_stone_slab", DOUBLE_STONE_SLAB, 43, 1),
    /**
     * Double wooden slab.
     *
     */
    DOUBLE_WOODEN_SLAB("minecraft:double_stone_slab", DOUBLE_STONE_SLAB, 43, 2),
    /**
     * Double cobblestone slab.
     *
     */
    DOUBLE_COBBLESTONE_SLAB("minecraft:double_stone_slab", DOUBLE_STONE_SLAB, 43, 3),
    /**
     * Double brick slab.
     *
     */
    DOUBLE_BRICK_SLAB("minecraft:double_stone_slab", DOUBLE_STONE_SLAB, 43, 4),
    /**
     * Double stone brick slab.
     *
     */
    DOUBLE_STONE_BRICK_SLAB("minecraft:double_stone_slab", DOUBLE_STONE_SLAB, 43, 5),
    /**
     * Double nether brick slab.
     *
     */
    DOUBLE_NETHER_BRICK_SLAB("minecraft:double_stone_slab", DOUBLE_STONE_SLAB, 43, 6),
    /**
     * Double quartz slab.
     *
     */
    DOUBLE_QUARTZ_SLAB("minecraft:double_stone_slab", DOUBLE_STONE_SLAB, 43, 7),
    /**
     * Stone slab.
     *
     */
    STONE_SLAB("minecraft:stone_slab", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 44),
    /**
     * Sandstone slab.
     *
     */
    SANDSTONE_SLAB("minecraft:stone_slab", STONE_SLAB, 44, 1),
    /**
     * Wooden slab.
     *
     */
    WOODEN_SLAB("minecraft:stone_slab", STONE_SLAB, 44, 2),
    /**
     * Cobblestone slab.
     *
     */
    COBBLESTONE_SLAB("minecraft:stone_slab", STONE_SLAB, 44, 3),
    /**
     * Brick slab.
     *
     */
    BRICK_SLAB("minecraft:stone_slab", STONE_SLAB, 44, 4),
    /**
     * Stone brick slab.
     *
     */
    STONE_BRICK_SLAB("minecraft:stone_slab", STONE_SLAB, 44, 5),
    /**
     * Nether brick slab.
     *
     */
    NETHER_BRICK_SLAB("minecraft:stone_slab", STONE_SLAB, 44, 6),
    /**
     * Quartz slab.
     *
     */
    QUARTZ_SLAB("minecraft:stone_slab", STONE_SLAB, 44, 7),
    /**
     * Bricks.
     *
     */
    BRICKS("minecraft:brick_block", new ConcreteRawBlockMeta(0, 30, 2, false, true, false), 45),
    /**
     * Tnt.
     *
     */
    TNT("minecraft:tnt", new ConcreteRawBlockMeta(0, 0, 0, false, true, false), 46),
    /**
     * Bookshelf.
     *
     */
    BOOKSHELF("minecraft:bookshelf", new ConcreteRawBlockMeta(0, 7.5, 1.5, false, true, false), 47),
    /**
     * Moss stone.
     *
     */
    MOSS_STONE("minecraft:mossy_cobblestone", new ConcreteRawBlockMeta(0, 30, 2, false, true, false), 48),
    /**
     * Obsidian.
     *
     */
    OBSIDIAN("minecraft:obsidian", new ConcreteRawBlockMeta(0, 6000, 50, false, true, false), 49),
    /**
     * Torch.
     *
     */
    TORCH("minecraft:torch", new ConcreteRawBlockMeta(14, 0, 0, false, false, true), 50),
    /**
     * Fire.
     *
     */
    FIRE("minecraft:fire", new ConcreteRawBlockMeta(15, 0, 0, false, false, true), 51),
    /**
     * Monster spawner.
     *
     */
    MONSTER_SPAWNER(
            "minecraft:mob_spawner",
            new ConcreteRawBlockMeta(0, 25, 5, false, true, false),
            MonsterSpawnerBlockMeta.class,
            52),
    /**
     * Oak wood stairs.
     *
     */
    OAK_WOOD_STAIRS("minecraft:oak_stairs", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 53),
    /**
     * Chest.
     *
     */
    CHEST("minecraft:chest", new ConcreteRawBlockMeta(0, 12.5, 2.5, false, false, true), ChestBlockMeta.class, 54),
    /**
     * Redstone wire.
     *
     */
    REDSTONE_WIRE(
            "minecraft:redstone_wire",
            new ConcreteRawBlockMeta(0, 0, 0, false, false, true),
            RedstoneWireBlockMeta.class,
            55),
    /**
     * Diamond ore.
     *
     */
    DIAMOND_ORE("minecraft:diamond_ore", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 56),
    /**
     * Diamond block.
     *
     */
    DIAMOND_BLOCK("minecraft:diamond_block", new ConcreteRawBlockMeta(0, 30, 5, false, true, false), 57),
    /**
     * Crafting table.
     *
     */
    CRAFTING_TABLE("minecraft:crafting_table", new ConcreteRawBlockMeta(0, 12.5, 2.5, false, true, false), 58),
    /**
     * Wheat crops.
     *
     */
    WHEAT_CROPS("minecraft:wheat", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 59),
    /**
     * Farmland.
     *
     */
    FARMLAND("minecraft:farmland", new ConcreteRawBlockMeta(0, 3, 0.6, false, true, true), FarmlandBlockMeta.class, 60),
    /**
     * Furnace.
     *
     */
    FURNACE("minecraft:furnace", new ConcreteRawBlockMeta(0, 17.5, 3.5, false, true, false), FurnaceBlockMeta.class, 61),
    /**
     * Burning furnace.
     *
     */
    BURNING_FURNACE(
            "minecraft:lit_furnace",
            new ConcreteRawBlockMeta(0, 17.5, Double.NaN, false, true, false),
            FurnaceBlockMeta.class,
            62),
    /**
     * Standing sign block.
     *
     */
    STANDING_SIGN_BLOCK("minecraft:standing_sign", new ConcreteRawBlockMeta(0, 5, 1, false, false, true), SignBlockMeta.class, 63),
    /**
     * Oak door block.
     *
     */
    OAK_DOOR_BLOCK("minecraft:wooden_door", new ConcreteRawBlockMeta(0, 15, 3, false, true, true), DoorBlockMeta.class, 64),
    /**
     * Ladder.
     *
     */
    LADDER("minecraft:ladder", new ConcreteRawBlockMeta(0, 2, 0.4, false, true, false), 65),
    /**
     * Rail.
     *
     */
    RAIL("minecraft:rail", new ConcreteRawBlockMeta(0, 3.5, 0.7, false, false, true), 66),
    /**
     * Cobblestone stairs.
     *
     */
    COBBLESTONE_STAIRS("minecraft:stone_stairs", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 67),
    /**
     * Wall mounted sign block.
     *
     */
    WALL_MOUNTED_SIGN_BLOCK("minecraft:wall_sign", new ConcreteRawBlockMeta(0, 5, 1, false, false, true), SignBlockMeta.class, 68),
    /**
     * Lever.
     *
     */
    LEVER("minecraft:lever", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, false, true), LeverBlockMeta.class, 69),
    /**
     * Stone pressure plate.
     *
     */
    STONE_PRESSURE_PLATE(
            "minecraft:stone_pressure_plate",
            new ConcreteRawBlockMeta(0, 2.5, 0.5, false, false, true),
            PressurePlateBlockMeta.class,
            70),
    /**
     * Iron door block.
     *
     */
    IRON_DOOR_BLOCK("minecraft:iron_door", new ConcreteRawBlockMeta(0, 25, 5, false, true, true), DoorBlockMeta.class, 71),
    /**
     * Wooden pressure plate.
     *
     */
    WOODEN_PRESSURE_PLATE(
            "minecraft:wooden_pressure_plate",
            new ConcreteRawBlockMeta(0, 2.5, 0.5, false, false, true),
            PressurePlateBlockMeta.class,
            72),
    /**
     * Redstone ore.
     *
     */
    REDSTONE_ORE("minecraft:redstone_ore", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 73),
    /**
     * Glowing redstone ore.
     *
     */
    GLOWING_REDSTONE_ORE("minecraft:lit_redstone_ore", new ConcreteRawBlockMeta(9, 15, Double.NaN, false, true, true), 74),
    /**
     * Redstone torch off.
     *
     */
    REDSTONE_TORCH_OFF("minecraft:unlit_redstone_torch", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 75),
    /**
     * Redstone torch on.
     *
     */
    REDSTONE_TORCH_ON("minecraft:redstone_torch", new ConcreteRawBlockMeta(7, 0, 0, false, false, true), 76),
    /**
     * Stone button.
     *
     */
    STONE_BUTTON("minecraft:stone_button", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, false, true), ButtonBlockMeta.class, 77),
    /**
     * Snow.
     *
     */
    SNOW("minecraft:snow_layer", new ConcreteRawBlockMeta(0, 1, 0.1, false, false, true), 78),
    /**
     * Ice.
     *
     */
    ICE("minecraft:ice", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, true, true), 79),
    /**
     * Snow block.
     *
     */
    SNOW_BLOCK("minecraft:snow", new ConcreteRawBlockMeta(0, 1, 0.1, false, true, false), 80),
    /**
     * Cactus.
     *
     */
    CACTUS("minecraft:cactus", new ConcreteRawBlockMeta(0, 2, 0.4, false, true, true), 81),
    /**
     * Clay.
     *
     */
    CLAY("minecraft:clay", new ConcreteRawBlockMeta(0, 3, 0.6, false, true, false), 82),
    /**
     * Sugar canes.
     *
     */
    SUGAR_CANES("minecraft:reeds", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 83),
    /**
     * Jukebox.
     *
     */
    JUKEBOX("minecraft:jukebox", new ConcreteRawBlockMeta(0, 30, 2, false, true, false), JukeboxBlockMeta.class, 84),
    /**
     * Oak fence.
     *
     */
    OAK_FENCE("minecraft:fence", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 85),
    /**
     * Pumpkin.
     *
     */
    PUMPKIN("minecraft:pumpkin", new ConcreteRawBlockMeta(0, 5, 1, false, true, false), 86),
    /**
     * Netherrack.
     *
     */
    NETHERRACK("minecraft:netherrack", new ConcreteRawBlockMeta(0, 2, 0.4, false, true, false), 87),
    /**
     * Soul sand.
     *
     */
    SOUL_SAND("minecraft:soul_sand", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, true, false), 88),
    /**
     * Glowstone.
     *
     */
    GLOWSTONE("minecraft:glowstone", new ConcreteRawBlockMeta(15, 1.5, 0.3, false, true, true), 89),
    /**
     * Nether portal.
     *
     */
    NETHER_PORTAL("minecraft:portal", new ConcreteRawBlockMeta(11, 0, -1, false, false, true), 90),
    /**
     * Jack o lantern.
     *
     */
    JACK_O_LANTERN("minecraft:lit_pumpkin", new ConcreteRawBlockMeta(15, 5, 1, false, true, false), 91),
    /**
     * Cake block.
     *
     */
    CAKE_BLOCK("minecraft:cake", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, true, true), CakeBlockMeta.class, 92),
    /**
     * Redstone repeater block off.
     *
     */
    REDSTONE_REPEATER_BLOCK_OFF(
            "minecraft:unpowered_repeater",
            new ConcreteRawBlockMeta(0, 0, 0, false, false, true),
            RedstoneRepeaterBlockMeta.class,
            93),
    /**
     * Redstone repeater block on.
     *
     */
    REDSTONE_REPEATER_BLOCK_ON(
            "minecraft:powered_repeater",
            new ConcreteRawBlockMeta(15, 0, Double.NaN, false, false, true),
            RedstoneRepeaterBlockMeta.class,
            94),
    /**
     * White stained glass.
     *
     */
    WHITE_STAINED_GLASS("minecraft:stained_glass", new ConcreteRawBlockMeta(0, 1.5, 0.3, false, true, true), 95),
    /**
     * Orange stained glass.
     *
     */
    ORANGE_STAINED_GLASS("minecraft:stained_glass", WHITE_STAINED_GLASS, 95, 1),
    /**
     * Magenta stained glass.
     *
     */
    MAGENTA_STAINED_GLASS("minecraft:stained_glass", WHITE_STAINED_GLASS, 95, 2),
    /**
     * Light blue stained glass.
     *
     */
    LIGHT_BLUE_STAINED_GLASS("minecraft:stained_glass", WHITE_STAINED_GLASS, 95, 3),
    /**
     * Yellow stained glass.
     *
     */
    YELLOW_STAINED_GLASS("minecraft:stained_glass", WHITE_STAINED_GLASS, 95, 4),
    /**
     * Lime stained glass.
     *
     */
    LIME_STAINED_GLASS("minecraft:stained_glass", WHITE_STAINED_GLASS, 95, 5),
    /**
     * Pink stained glass.
     *
     */
    PINK_STAINED_GLASS("minecraft:stained_glass", WHITE_STAINED_GLASS, 95, 6),
    /**
     * Gray stained glass.
     *
     */
    GRAY_STAINED_GLASS("minecraft:stained_glass", WHITE_STAINED_GLASS, 95, 7),
    /**
     * Light gray stained glass.
     *
     */
    LIGHT_GRAY_STAINED_GLASS("minecraft:stained_glass", WHITE_STAINED_GLASS, 95, 8),
    /**
     * Cyan stained glass.
     *
     */
    CYAN_STAINED_GLASS("minecraft:stained_glass", WHITE_STAINED_GLASS, 95, 9),
    /**
     * Purple stained glass.
     *
     */
    PURPLE_STAINED_GLASS("minecraft:stained_glass", WHITE_STAINED_GLASS, 95, 0),
    /**
     * Blue stained glass.
     *
     */
    BLUE_STAINED_GLASS("minecraft:stained_glass", WHITE_STAINED_GLASS, 95, 1),
    /**
     * Brown stained glass.
     *
     */
    BROWN_STAINED_GLASS("minecraft:stained_glass", WHITE_STAINED_GLASS, 95, 2),
    /**
     * Green stained glass.
     *
     */
    GREEN_STAINED_GLASS("minecraft:stained_glass", WHITE_STAINED_GLASS, 95, 3),
    /**
     * Red stained glass.
     *
     */
    RED_STAINED_GLASS("minecraft:stained_glass", WHITE_STAINED_GLASS, 95, 4),
    /**
     * Black stained glass.
     *
     */
    BLACK_STAINED_GLASS("minecraft:stained_glass", WHITE_STAINED_GLASS, 95, 5),
    /**
     * Wooden trapdoor.
     *
     */
    WOODEN_TRAPDOOR("minecraft:trapdoor", new ConcreteRawBlockMeta(0, 15, 3, false, true, true), DoorBlockMeta.class, 96),
    /**
     * Stone monster egg.
     *
     */
    STONE_MONSTER_EGG("minecraft:monster_egg", new ConcreteRawBlockMeta(0, 3.75, 0.75, false, true, false), 97),
    /**
     * Cobblestone monster egg.
     *
     */
    COBBLESTONE_MONSTER_EGG("minecraft:monster_egg", STONE_MONSTER_EGG, 97, 1),
    /**
     * Stone brick monster egg.
     *
     */
    STONE_BRICK_MONSTER_EGG("minecraft:monster_egg", STONE_MONSTER_EGG, 97, 2),
    /**
     * Mossy stone brick monster egg.
     *
     */
    MOSSY_STONE_BRICK_MONSTER_EGG("minecraft:monster_egg", STONE_MONSTER_EGG, 97, 3),
    /**
     * Cracked stone brick monster egg.
     *
     */
    CRACKED_STONE_BRICK_MONSTER_EGG("minecraft:monster_egg", STONE_MONSTER_EGG, 97, 4),
    /**
     * Chiseled stone brick monster egg.
     *
     */
    CHISELED_STONE_BRICK_MONSTER_EGG("minecraft:monster_egg", STONE_MONSTER_EGG, 97, 5),
    /**
     * Stone bricks.
     *
     */
    STONE_BRICKS("minecraft:stonebrick", new ConcreteRawBlockMeta(0, 30, 1.5, false, true, false), 98),
    /**
     * Mossy stone bricks.
     *
     */
    MOSSY_STONE_BRICKS("minecraft:stonebrick", STONE_BRICKS, 98, 1),
    /**
     * Cracked stone bricks.
     *
     */
    CRACKED_STONE_BRICKS("minecraft:stonebrick", STONE_BRICKS, 98, 2),
    /**
     * Chiseled stone bricks.
     *
     */
    CHISELED_STONE_BRICKS("minecraft:stonebrick", STONE_BRICKS, 98, 3),
    /**
     * Red mushroom cap.
     *
     */
    RED_MUSHROOM_CAP("minecraft:stonebrick", new ConcreteRawBlockMeta(0, 1, 0.2, false, true, false), 99),
    /**
     * Brown mushroom cap.
     *
     */
    BROWN_MUSHROOM_CAP("minecraft:stonebrick", new ConcreteRawBlockMeta(0, 1, 0.2, false, true, false), 100),
    /**
     * Iron bars.
     *
     */
    IRON_BARS("minecraft:iron_bars", new ConcreteRawBlockMeta(0, 30, 5, false, true, true), 101),
    /**
     * Glass pane.
     *
     */
    GLASS_PANE("minecraft:glass_pane", new ConcreteRawBlockMeta(0, 1.5, 0.3, false, true, true), 102),
    /**
     * Melon block.
     *
     */
    MELON_BLOCK("minecraft:melon_block", new ConcreteRawBlockMeta(0, 5, 1, false, true, false), 103),
    /**
     * Pumpkin stem.
     *
     */
    PUMPKIN_STEM("minecraft:pumpkin_stem", new ConcreteRawBlockMeta(0, 0, 1, false, false, true), StemBlockMeta.class, 104),
    /**
     * Melon stem.
     *
     */
    MELON_STEM("minecraft:melon_stem", new ConcreteRawBlockMeta(0, 0, 1, false, false, true), StemBlockMeta.class, 105),
    /**
     * Vines.
     *
     */
    VINES("minecraft:vine", new ConcreteRawBlockMeta(0, 1, 0.2, false, false, true), 106),
    /**
     * Oak fence gate.
     *
     */
    OAK_FENCE_GATE("minecraft:fence_gate", new ConcreteRawBlockMeta(0, 15, 2, false, false, true), 107),
    /**
     * Brick stairs.
     *
     */
    BRICK_STAIRS("minecraft:brick_stairs", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 108),
    /**
     * Stone brick stairs.
     *
     */
    STONE_BRICK_STAIRS("minecraft:stone_brick_stairs", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 109),
    /**
     * Mycelium.
     *
     */
    MYCELIUM("minecraft:mycelium", new ConcreteRawBlockMeta(0, 2.5, 0.6, false, true, false), 110),
    /**
     * Lily pad.
     *
     */
    LILY_PAD("minecraft:waterlily", new ConcreteRawBlockMeta(0, 0, 0.6, false, true, true), 111),
    /**
     * Nether brick.
     *
     */
    NETHER_BRICK("minecraft:nether_brick", new ConcreteRawBlockMeta(0, 30, 2, false, true, false), 112),
    /**
     * Nether brick fence.
     *
     */
    NETHER_BRICK_FENCE("minecraft:nether_brick_fence", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 113),
    /**
     * Nether brick stairs.
     *
     */
    NETHER_BRICK_STAIRS("minecraft:nether_brick_stairs", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 114),
    /**
     * Nether wart.
     *
     */
    NETHER_WART("minecraft:nether_wart", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), NetherWartBlockMeta.class, 115),
    /**
     * Enchantment table.
     *
     */
    ENCHANTMENT_TABLE("minecraft:enchanting_table", new ConcreteRawBlockMeta(0, 6000, 5, false, true, true),

    116),
    /**
     * Brewing stand.
     *
     */
    BREWING_STAND(
            "minecraft:brewing_stand",
            new ConcreteRawBlockMeta(1, 2.5, 0.5, false, true, true),
            BrewingStandBlockMeta.class,
            117),
    /**
     * Cauldron.
     *
     */
    CAULDRON("minecraft:cauldron", new ConcreteRawBlockMeta(0, 10, 2, false, true, true), CauldronBlockMeta.class, 118),
    /**
     * End portal.
     *
     */
    END_PORTAL("minecraft:end_portal", new ConcreteRawBlockMeta(15, 18000000, -1, false, false, true), 119),
    /**
     * End portal frame.
     *
     */
    END_PORTAL_FRAME(
            "minecraft:end_portal_frame",
            new ConcreteRawBlockMeta(0, 18000000, -1, false, true, false),
            EndPortalFrameBlockMeta.class,
            120),
    /**
     * End stone.
     *
     */
    END_STONE("minecraft:end_stone", new ConcreteRawBlockMeta(0, 45, 3, false, true, false), 121),
    /**
     * Dragon egg.
     *
     */
    DRAGON_EGG("minecraft:dragon_egg", new ConcreteRawBlockMeta(1, 45, 3, true, true, true), 122),
    /**
     * Redstone lamp inactive.
     *
     */
    REDSTONE_LAMP_INACTIVE("minecraft:redstone_lamp", new ConcreteRawBlockMeta(0, 1.5, 0.3, false, true, true), 123),
    /**
     * Redstone lamp active.
     *
     */
    REDSTONE_LAMP_ACTIVE("minecraft:lit_redstone_lamp", new ConcreteRawBlockMeta(15, 1.5, 0.3, false, true, true), 124),
    /**
     * Double oak wood slab.
     *
     */
    DOUBLE_OAK_WOOD_SLAB("minecraft:double_wooden_slab", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 125),
    /**
     * Double spruce wood slab.
     *
     */
    DOUBLE_SPRUCE_WOOD_SLAB("minecraft:double_wooden_slab", DOUBLE_OAK_WOOD_SLAB, 125, 1),
    /**
     * Double birch wood slab.
     *
     */
    DOUBLE_BIRCH_WOOD_SLAB("minecraft:double_wooden_slab", DOUBLE_OAK_WOOD_SLAB, 125, 2),
    /**
     * Double jungle wood slab.
     *
     */
    DOUBLE_JUNGLE_WOOD_SLAB("minecraft:double_wooden_slab", DOUBLE_OAK_WOOD_SLAB, 125, 3),
    /**
     * Double acacia wood slab.
     *
     */
    DOUBLE_ACACIA_WOOD_SLAB("minecraft:double_wooden_slab", DOUBLE_OAK_WOOD_SLAB, 125, 4),
    /**
     * Double dark oak wood slab.
     *
     */
    DOUBLE_DARK_OAK_WOOD_SLAB("minecraft:double_wooden_slab", DOUBLE_OAK_WOOD_SLAB, 125, 5),
    /**
     * Oak wood slab.
     *
     */
    OAK_WOOD_SLAB("minecraft:wooden_slab", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 126),
    /**
     * Spruce wood slab.
     *
     */
    SPRUCE_WOOD_SLAB("minecraft:wooden_slab", OAK_WOOD_SLAB, 126, 1),
    /**
     * Birch wood slab.
     *
     */
    BIRCH_WOOD_SLAB("minecraft:wooden_slab", OAK_WOOD_SLAB, 126, 2),
    /**
     * Jungle wood slab.
     *
     */
    JUNGLE_WOOD_SLAB("minecraft:wooden_slab", OAK_WOOD_SLAB, 126, 3),
    /**
     * Acacia wood slab.
     *
     */
    ACACIA_WOOD_SLAB("minecraft:wooden_slab", OAK_WOOD_SLAB, 126, 4),
    /**
     * Dark oak wood slab.
     *
     */
    DARK_OAK_WOOD_SLAB("minecraft:wooden_slab", OAK_WOOD_SLAB, 126, 5),
    /**
     * Cocoa.
     *
     */
    COCOA("minecraft:cocoa", new ConcreteRawBlockMeta(0, 15, 0.2, false, true, true), CocoaBlockMeta.class, 127),
    /**
     * Sandstone stairs.
     *
     */
    SANDSTONE_STAIRS("minecraft:sandstone_stairs", new ConcreteRawBlockMeta(0, 4, 0.8, false, true, true), 128),
    /**
     * Emerald ore.
     *
     */
    EMERALD_ORE("minecraft:emerald_ore", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 129),
    /**
     * Ender chest.
     *
     */
    ENDER_CHEST("minecraft:ender_chest", new ConcreteRawBlockMeta(7, 3000, 22.5, false, true, true), 130),
    /**
     * Tripwire hook.
     *
     */
    TRIPWIRE_HOOK(
            "minecraft:tripwire_hook",
            new ConcreteRawBlockMeta(0, 0, 0, false, false, true),
            TripwireHookBlockMeta.class,
            131),
    /**
     * Tripwire.
     *
     */
    TRIPWIRE("minecraft:tripwire_hook", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), TripwireBlockMeta.class, 132),
    /**
     * Emerald block.
     *
     */
    EMERALD_BLOCK("minecraft:emerald_block", new ConcreteRawBlockMeta(0, 30, 5, false, true, false), 133),
    /**
     * Spruce wood stairs.
     *
     */
    SPRUCE_WOOD_STAIRS("minecraft:spruce_stairs", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 134),
    /**
     * Birch wood stairs.
     *
     */
    BIRCH_WOOD_STAIRS("minecraft:birch_stairs", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 135),
    /**
     * Jungle wood stairs.
     *
     */
    JUNGLE_WOOD_STAIRS("minecraft:jungle_stairs", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 136),
    /**
     * Command block.
     *
     */
    COMMAND_BLOCK(
            "minecraft:command_block",
            new ConcreteRawBlockMeta(0, 18000000, -1, false, true, false),
            CommandBlockMeta.class,
            137),
    /**
     * Beacon.
     *
     */
    BEACON("minecraft:beacon", new ConcreteRawBlockMeta(15, 15, 3, false, true, true), BeaconBlockMeta.class, 138),
    /**
     * Cobblestone wall.
     *
     */
    COBBLESTONE_WALL("minecraft:cobblestone_wall", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 139),
    /**
     * Mossy cobblestone wall.
     *
     */
    MOSSY_COBBLESTONE_WALL("minecraft:cobblestone_wall", COBBLESTONE_WALL, 139, 1),
    /**
     * Flower pot.
     *
     */
    FLOWER_POT("minecraft:flower_pot", new ConcreteRawBlockMeta(0, 0, 0, false, true, true), FlowerPotBlockMeta.class, 140),
    /**
     * Carrots.
     *
     */
    CARROTS("minecraft:carrots", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), CarrotAndPotatoBlockMeta.class, 141),
    /**
     * Potatoes.
     *
     */
    POTATOES("minecraft:potatoes", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), CarrotAndPotatoBlockMeta.class, 142),
    /**
     * Wooden button.
     *
     */
    WOODEN_BUTTON(
            "minecraft:wooden_button",
            new ConcreteRawBlockMeta(0, 2.5, 0.5, false, false, true),
            ButtonBlockMeta.class,
            143),
    /**
     * Mob head.
     *
     */
    MOB_HEAD("minecraft:skull", new ConcreteRawBlockMeta(0, 5, 1, false, true, true), MobHeadBlockMeta.class, 144),
    /**
     * Anvil.
     *
     */
    ANVIL("minecraft:anvil", new ConcreteRawBlockMeta(0, 6000, 5, false, true, true), AnvilBlockMeta.class, 145),
    /**
     * Trapped chest.
     *
     */
    TRAPPED_CHEST(
            "minecraft:trapped_chest",
            new ConcreteRawBlockMeta(0, 12.5, 2.5, false, true, true),
            TrappedChestBlockMeta.class,
            146),
    /**
     * Weighted pressure plate light.
     *
     */
    WEIGHTED_PRESSURE_PLATE_LIGHT("minecraft:light_weighted_pressure_plate", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, false,
            true), PressurePlateBlockMeta.class, 147),
    /**
     * Weighted pressure plate heavy.
     *
     */
    WEIGHTED_PRESSURE_PLATE_HEAVY("minecraft:heavy_weighted_pressure_plate", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, false,
            true), PressurePlateBlockMeta.class, 148),
    /**
     * Redstone comparator inactive.
     *
     */
    REDSTONE_COMPARATOR_INACTIVE(
            "minecraft:unpowered_comparator",
            new ConcreteRawBlockMeta(0, 0, 0, false, true, true),
            RedstoneComparatorBlockMeta.class,
            149),
    /**
     * Redstone comparator active.
     *
     */
    REDSTONE_COMPARATOR_ACTIVE(
            "minecraft:powered_comparator",
            new ConcreteRawBlockMeta(0, 0, 0, false, true, true),
            RedstoneComparatorBlockMeta.class,
            150),
    /**
     * Daylight sensor.
     *
     */
    DAYLIGHT_SENSOR("minecraft:daylight_detector", new ConcreteRawBlockMeta(0, 1, 0.2, false, true, true), 151),
    /**
     * Redstone block.
     *
     */
    REDSTONE_BLOCK("minecraft:redstone_block", new ConcreteRawBlockMeta(0, 30, 5, false, true, false), 152),
    /**
     * Nether quartz ore.
     *
     */
    NETHER_QUARTZ_ORE("minecraft:quartz_ore", new ConcreteRawBlockMeta(0, 15, 3, false, true, false), 153),
    /**
     * Hopper.
     *
     */
    HOPPER("minecraft:hopper", new ConcreteRawBlockMeta(0, 15, 3, false, true, true), HopperBlockMeta.class, 154),
    /**
     * Quartz block.
     *
     */
    QUARTZ_BLOCK("minecraft:quartz_block", new ConcreteRawBlockMeta(0, 4, 0.8, false, true, false), 155),
    /**
     * Chiseled quartz block.
     *
     */
    CHISELED_QUARTZ_BLOCK("minecraft:quartz_block", QUARTZ_BLOCK, 155, 1),
    /**
     * Pillar quartz block.
     *
     */
    PILLAR_QUARTZ_BLOCK("minecraft:quartz_block", QUARTZ_BLOCK, 155, 2),
    /**
     * Quartz stairs.
     *
     */
    QUARTZ_STAIRS("minecraft:quartz_stairs", new ConcreteRawBlockMeta(0, 4, 0.8, false, true, true), 156),
    /**
     * Activator rail.
     *
     */
    ACTIVATOR_RAIL(
            "minecraft:activator_rail",
            new ConcreteRawBlockMeta(0, 3.5, 0.7, false, true, true),
            ActivatorRailBlockMeta.class,
            157),
    /**
     * Dropper.
     *
     */
    DROPPER(
            "minecraft:dropper",
            new ConcreteRawBlockMeta(0, 17.5, 3.5, false, true, false),
            DispenserAndDropperBlockMeta.class,
            158),
    /**
     * White stained clay.
     *
     */
    WHITE_STAINED_CLAY("minecraft:stained_hardened_clay", new ConcreteRawBlockMeta(0, 30, 1.25, false, true, false), 159),
    /**
     * Orange stained clay.
     *
     */
    ORANGE_STAINED_CLAY("minecraft:stained_hardened_clay", WHITE_STAINED_CLAY, 159, 1),
    /**
     * Magenta stained clay.
     *
     */
    MAGENTA_STAINED_CLAY("minecraft:stained_hardened_clay", WHITE_STAINED_CLAY, 159, 2),
    /**
     * Light blue stained clay.
     *
     */
    LIGHT_BLUE_STAINED_CLAY("minecraft:stained_hardened_clay", WHITE_STAINED_CLAY, 159, 3),
    /**
     * Yellow stained clay.
     *
     */
    YELLOW_STAINED_CLAY("minecraft:stained_hardened_clay", WHITE_STAINED_CLAY, 159, 4),
    /**
     * Lime stained clay.
     *
     */
    LIME_STAINED_CLAY("minecraft:stained_hardened_clay", WHITE_STAINED_CLAY, 159, 5),
    /**
     * Pink stained clay.
     *
     */
    PINK_STAINED_CLAY("minecraft:stained_hardened_clay", WHITE_STAINED_CLAY, 159, 6),
    /**
     * Gray stained clay.
     *
     */
    GRAY_STAINED_CLAY("minecraft:stained_hardened_clay", WHITE_STAINED_CLAY, 159, 7),
    /**
     * Light gray stained clay.
     *
     */
    LIGHT_GRAY_STAINED_CLAY("minecraft:stained_hardened_clay", WHITE_STAINED_CLAY, 159, 8),
    /**
     * Cyan stained clay.
     *
     */
    CYAN_STAINED_CLAY("minecraft:stained_hardened_clay", WHITE_STAINED_CLAY, 159, 9),
    /**
     * Purple stained clay.
     *
     */
    PURPLE_STAINED_CLAY("minecraft:stained_hardened_clay", WHITE_STAINED_CLAY, 159, 0),
    /**
     * Blue stained clay.
     *
     */
    BLUE_STAINED_CLAY("minecraft:stained_hardened_clay", WHITE_STAINED_CLAY, 159, 1),
    /**
     * Brown stained clay.
     *
     */
    BROWN_STAINED_CLAY("minecraft:stained_hardened_clay", WHITE_STAINED_CLAY, 159, 2),
    /**
     * Green stained clay.
     *
     */
    GREEN_STAINED_CLAY("minecraft:stained_hardened_clay", WHITE_STAINED_CLAY, 159, 3),
    /**
     * Red stained clay.
     *
     */
    RED_STAINED_CLAY("minecraft:stained_hardened_clay", WHITE_STAINED_CLAY, 159, 4),
    /**
     * Black stained clay.
     *
     */
    BLACK_STAINED_CLAY("minecraft:stained_hardened_clay", WHITE_STAINED_CLAY, 159, 5),
    /**
     * White stained glass pane.
     *
     */
    WHITE_STAINED_GLASS_PANE("minecraft:stained_glass_pane", new ConcreteRawBlockMeta(0, 1.5, 0.3, false, true, true), 160),
    /**
     * Orange stained glass pane.
     *
     */
    ORANGE_STAINED_GLASS_PANE("minecraft:stained_glass_pane", WHITE_STAINED_GLASS_PANE, 160, 1),
    /**
     * Magenta stained glass pane.
     *
     */
    MAGENTA_STAINED_GLASS_PANE("minecraft:stained_glass_pane", WHITE_STAINED_GLASS_PANE, 160, 2),
    /**
     * Light blue stained glass pane.
     *
     */
    LIGHT_BLUE_STAINED_GLASS_PANE("minecraft:stained_glass_pane", WHITE_STAINED_GLASS_PANE, 160, 3),
    /**
     * Yellow stained glass pane.
     *
     */
    YELLOW_STAINED_GLASS_PANE("minecraft:stained_glass_pane", WHITE_STAINED_GLASS_PANE, 160, 4),
    /**
     * Lime stained glass pane.
     *
     */
    LIME_STAINED_GLASS_PANE("minecraft:stained_glass_pane", WHITE_STAINED_GLASS_PANE, 160, 5),
    /**
     * Pink stained glass pane.
     *
     */
    PINK_STAINED_GLASS_PANE("minecraft:stained_glass_pane", WHITE_STAINED_GLASS_PANE, 160, 6),
    /**
     * Gray stained glass pane.
     *
     */
    GRAY_STAINED_GLASS_PANE("minecraft:stained_glass_pane", WHITE_STAINED_GLASS_PANE, 160, 7),
    /**
     * Light gray stained glass pane.
     *
     */
    LIGHT_GRAY_STAINED_GLASS_PANE("minecraft:stained_glass_pane", WHITE_STAINED_GLASS_PANE, 160, 8),
    /**
     * Cyan stained glass pane.
     *
     */
    CYAN_STAINED_GLASS_PANE("minecraft:stained_glass_pane", WHITE_STAINED_GLASS_PANE, 160, 9),
    /**
     * Purple stained glass pane.
     *
     */
    PURPLE_STAINED_GLASS_PANE("minecraft:stained_glass_pane", WHITE_STAINED_GLASS_PANE, 160, 0),
    /**
     * Blue stained glass pane.
     *
     */
    BLUE_STAINED_GLASS_PANE("minecraft:stained_glass_pane", WHITE_STAINED_GLASS_PANE, 160, 1),
    /**
     * Brown stained glass pane.
     *
     */
    BROWN_STAINED_GLASS_PANE("minecraft:stained_glass_pane", WHITE_STAINED_GLASS_PANE, 160, 2),
    /**
     * Green stained glass pane.
     *
     */
    GREEN_STAINED_GLASS_PANE("minecraft:stained_glass_pane", WHITE_STAINED_GLASS_PANE, 160, 3),
    /**
     * Red stained glass pane.
     *
     */
    RED_STAINED_GLASS_PANE("minecraft:stained_glass_pane", WHITE_STAINED_GLASS_PANE, 160, 4),
    /**
     * Black stained glass pane.
     *
     */
    BLACK_STAINED_GLASS_PANE("minecraft:stained_glass_pane", WHITE_STAINED_GLASS_PANE, 160, 5),
    /**
     * Acacia leaves.
     *
     */
    ACACIA_LEAVES("minecraft:leaves2", new ConcreteRawBlockMeta(0, 1, 0.2, false, true, true), 161),
    /**
     * Dark oak leaves.
     *
     */
    DARK_OAK_LEAVES("minecraft:leaves2", ACACIA_LEAVES, 161, 1),
    /**
     * Acacia wood.
     *
     */
    ACACIA_WOOD("minecraft:logs2", new ConcreteRawBlockMeta(0, 10, 2, false, true, false), 162),
    /**
     * Dark oak wood.
     *
     */
    DARK_OAK_WOOD("minecraft:logs2", ACACIA_WOOD, 162, 1),
    /**
     * Acacia wood stairs.
     *
     */
    ACACIA_WOOD_STAIRS("minecraft:acacia_stairs", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 163),
    /**
     * Dark oak wood stairs.
     *
     */
    DARK_OAK_WOOD_STAIRS("minecraft:dark_oak_stairs", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 164),
    /**
     * Slime block.
     *
     */
    SLIME_BLOCK("minecraft:slime", new ConcreteRawBlockMeta(0, 0, 0, false, true, true), 165),
    /**
     * Barrier.
     *
     */
    BARRIER("minecraft:barrier", new ConcreteRawBlockMeta(0, 18000000, -1, false, true, true), 166),
    /**
     * Iron trapdoor.
     *
     */
    IRON_TRAPDOOR("minecraft:iron_trapdoor", new ConcreteRawBlockMeta(0, 25, 3, false, true, true), DoorBlockMeta.class, 167),
    /**
     * Prismarine.
     *
     */
    PRISMARINE("minecraft:prismarine", new ConcreteRawBlockMeta(0, 30, 1.5, false, true, false), 168),
    /**
     * Prismarine bricks.
     *
     */
    PRISMARINE_BRICKS("minecraft:prismarine", PRISMARINE, 168, 1),
    /**
     * Dark prismarine.
     *
     */
    DARK_PRISMARINE("minecraft:prismarine", PRISMARINE, 168, 2),
    /**
     * Sea lantern.
     *
     */
    SEA_LANTERN("minecraft:sea_lantern", new ConcreteRawBlockMeta(15, 1.5, 0.3, false, true, false), 169),
    /**
     * Hay bale.
     *
     */
    HAY_BALE("minecraft:hay_block", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, true, false), 170),
    /**
     * White carpet.
     *
     */
    WHITE_CARPET("minecraft:carpet", new ConcreteRawBlockMeta(0, 0, 0.1, false, false, true), 171),
    /**
     * Orange carpet.
     *
     */
    ORANGE_CARPET("minecraft:carpet", WHITE_CARPET, 171, 1),
    /**
     * Magenta carpet.
     *
     */
    MAGENTA_CARPET("minecraft:carpet", WHITE_CARPET, 171, 2),
    /**
     * Light blue carpet.
     *
     */
    LIGHT_BLUE_CARPET("minecraft:carpet", WHITE_CARPET, 171, 3),
    /**
     * Yellow carpet.
     *
     */
    YELLOW_CARPET("minecraft:carpet", WHITE_CARPET, 171, 4),
    /**
     * Lime carpet.
     *
     */
    LIME_CARPET("minecraft:carpet", WHITE_CARPET, 171, 5),
    /**
     * Pink carpet.
     *
     */
    PINK_CARPET("minecraft:carpet", WHITE_CARPET, 171, 6),
    /**
     * Gray carpet.
     *
     */
    GRAY_CARPET("minecraft:carpet", WHITE_CARPET, 171, 7),
    /**
     * Light gray carpet.
     *
     */
    LIGHT_GRAY_CARPET("minecraft:carpet", WHITE_CARPET, 171, 8),
    /**
     * Cyan carpet.
     *
     */
    CYAN_CARPET("minecraft:carpet", WHITE_CARPET, 171, 9),
    /**
     * Purple carpet.
     *
     */
    PURPLE_CARPET("minecraft:carpet", WHITE_CARPET, 171, 0),
    /**
     * Blue carpet.
     *
     */
    BLUE_CARPET("minecraft:carpet", WHITE_CARPET, 171, 1),
    /**
     * Brown carpet.
     *
     */
    BROWN_CARPET("minecraft:carpet", WHITE_CARPET, 171, 2),
    /**
     * Green carpet.
     *
     */
    GREEN_CARPET("minecraft:carpet", WHITE_CARPET, 171, 3),
    /**
     * Red carpet.
     *
     */
    RED_CARPET("minecraft:carpet", WHITE_CARPET, 171, 4),
    /**
     * Black carpet.
     *
     */
    BLACK_CARPET("minecraft:carpet", WHITE_CARPET, 171, 5),
    /**
     * Hardened clay.
     *
     */
    HARDENED_CLAY("minecraft:hardened_clay", new ConcreteRawBlockMeta(0, 30, 1.25, false, true, false), 172),
    /**
     * Block of coal.
     *
     */
    BLOCK_OF_COAL("minecraft:coal_block", new ConcreteRawBlockMeta(0, 30, 5, false, true, false), 173),
    /**
     * Packed ice.
     *
     */
    PACKED_ICE("minecraft:packed_ice", new ConcreteRawBlockMeta(0, 2.5, 0.5, false, true, false), 174),
    /**
     * Sunflower.
     *
     */
    SUNFLOWER("minecraft:double_plant", new ConcreteRawBlockMeta(0, 0, 0, false, false, true), 175),
    /**
     * Lilac.
     *
     */
    LILAC("minecraft:double_plant", SUNFLOWER, 175, 1),
    /**
     * Double tallgrass.
     *
     */
    DOUBLE_TALLGRASS("minecraft:double_plant", SUNFLOWER, 175, 2),
    /**
     * Large fern.
     *
     */
    LARGE_FERN("minecraft:double_plant", SUNFLOWER, 175, 3),
    /**
     * Rose bush.
     *
     */
    ROSE_BUSH("minecraft:double_plant", SUNFLOWER, 175, 4),
    /**
     * Peony.
     *
     */
    PEONY("minecraft:double_plant", SUNFLOWER, 175, 5),
    /**
     * Freestanding banner.
     *
     */
    FREESTANDING_BANNER("minecraft:standing_banner", new ConcreteRawBlockMeta(0, 5, Double.NaN, false, false, true), 176),
    /**
     * Wallmounted banner.
     *
     */
    WALLMOUNTED_BANNER("minecraft:wall_banner", new ConcreteRawBlockMeta(0, 5, Double.NaN, false, false, true), 177),
    /**
     * Inverted daylight sensor.
     *
     */
    INVERTED_DAYLIGHT_SENSOR("minecraft:daylight_detector_inverted", new ConcreteRawBlockMeta(0, 1, 0.2, false, true, true), 178),
    /**
     * Red sandstone.
     *
     */
    RED_SANDSTONE("minecraft:red_sandstone", new ConcreteRawBlockMeta(0, 4, 0.8, false, true, false), 179),
    /**
     * Smooth red sandstone.
     *
     */
    SMOOTH_RED_SANDSTONE("minecraft:red_sandstone", RED_SANDSTONE, 179, 1),
    /**
     * Chiseled red sandstone.
     *
     */
    CHISELED_RED_SANDSTONE("minecraft:red_sandstone", RED_SANDSTONE, 179, 2),
    /**
     * Red sandstone stairs.
     *
     */
    RED_SANDSTONE_STAIRS("minecraft:red_sandstone_stairs", new ConcreteRawBlockMeta(0, 4, 0.8, false, true, true), 180),
    /**
     * Double red sandstone slab.
     *
     */
    DOUBLE_RED_SANDSTONE_SLAB("minecraft:stone_slab2", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 181),
    /**
     * Red sandstone slab.
     *
     */
    RED_SANDSTONE_SLAB("minecraft:double_stone_slab2", new ConcreteRawBlockMeta(0, 30, 2, false, true, true), 182),
    /**
     * Spruce fence gate.
     *
     */
    SPRUCE_FENCE_GATE("minecraft:spruce_fence_gate", new ConcreteRawBlockMeta(0, 15, 2, false, false, true), 183),
    /**
     * Birch fence gate.
     *
     */
    BIRCH_FENCE_GATE("minecraft:birch_fence_gate", new ConcreteRawBlockMeta(0, 15, 2, false, false, true), 184),
    /**
     * Jungle fence gate.
     *
     */
    JUNGLE_FENCE_GATE("minecraft:jungle_fence_gate", new ConcreteRawBlockMeta(0, 15, 2, false, false, true), 185),
    /**
     * Dark oak fence gate.
     *
     */
    DARK_OAK_FENCE_GATE("minecraft:dark_oak_fence_gate", new ConcreteRawBlockMeta(0, 15, 2, false, false, true), 186),
    /**
     * Acacia fence gate.
     *
     */
    ACACIA_FENCE_GATE("minecraft:acacia_fence_gate", new ConcreteRawBlockMeta(0, 15, 2, false, false, true), 187),
    /**
     * Spruce fence.
     *
     */
    SPRUCE_FENCE("minecraft:spruce_fence", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 188),
    /**
     * Birch fence.
     *
     */
    BIRCH_FENCE("minecraft:birch_fence", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 189),
    /**
     * Jungle fence.
     *
     */
    JUNGLE_FENCE("minecraft:jungle_fence", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 190),
    /**
     * Dark oak fence.
     *
     */
    DARK_OAK_FENCE("minecraft:dark_oak_fence", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 191),
    /**
     * Acacia fence.
     *
     */
    ACACIA_FENCE("minecraft:acacia_fence", new ConcreteRawBlockMeta(0, 15, 2, false, true, true), 192),
    /**
     * Spruce door block.
     *
     */
    SPRUCE_DOOR_BLOCK("minecraft:spruce_door", new ConcreteRawBlockMeta(0, 15, 3, false, true, true), 193),
    /**
     * Birch door block.
     *
     */
    BIRCH_DOOR_BLOCK("minecraft:birch_door", new ConcreteRawBlockMeta(0, 15, 3, false, true, true), 194),
    /**
     * Jungle door block.
     *
     */
    JUNGLE_DOOR_BLOCK("minecraft:jungle_door", new ConcreteRawBlockMeta(0, 15, 3, false, true, true), 195),
    /**
     * Acacia door block.
     *
     */
    ACACIA_DOOR_BLOCK("minecraft:acacia_door", new ConcreteRawBlockMeta(0, 15, 3, false, true, true), 196),
    /**
     * Dark oak door block.
     *
     */
    DARK_OAK_DOOR_BLOCK("minecraft:dark_oak_door", new ConcreteRawBlockMeta(0, 15, 3, false, true, true), 197);

    /**
     * The name of this material.
     *
     */
    private final String materialName;

    /**
     * The ID of this material.
     *
     */
    private final int materialId;
    /**
     * The data of this material.
     *
     */
    private final byte materialData;

    /**
     * The {@link RawBlockMeta} of this material.
     *
     */
    private final RawBlockMeta rawBlockMeta;

    /**
     * The class of the specific {@link BlockMeta} to use.
     *
     */
    private final Class<? extends BlockMeta> blockMetaClass;

    /**
     * Constructor of BlockMaterial.
     *
     * @param materialName
     *            The name of this material.
     * @param rawBlockMeta
     *            The {@link RawBlockMeta} of this material.
     * @param blockMetaClass
     *            The class of the specific {@link BlockMeta} to use.
     * @param materialId
     *            The ID of this material.
     * @param materialData
     *            The data of this material.
     */
    private BlockMaterial(final String materialName, final RawBlockMeta rawBlockMeta,
            final Class<? extends BlockMeta> blockMetaClass, final int materialId, final byte materialData) {
        this.materialName = materialName;
        this.rawBlockMeta = rawBlockMeta;
        this.blockMetaClass = blockMetaClass;
        this.materialId = materialId;
        this.materialData = materialData;
    }

    /**
     * Constructor of BlockMaterial.
     *
     * @param materialName
     *            The name of this material.
     * @param rawBlockMeta
     *            The {@link RawBlockMeta} of this material.
     * @param blockMetaClass
     *            The class of the specific {@link BlockMeta} to use.
     * @param materialId
     *            The ID of this material.
     * @param materialData
     *            The data of this material.
     */
    private BlockMaterial(final String materialName, final RawBlockMeta rawBlockMeta,
            final Class<? extends BlockMeta> blockMetaClass, final int materialId, final int materialData) {
        this(materialName, rawBlockMeta, blockMetaClass, materialId, (byte) materialData);
    }

    /**
     * Constructor of BlockMaterial.
     *
     * @param materialName
     *            The name of this material.
     * @param rawBlockMeta
     *            The {@link RawBlockMeta} of this material.
     * @param blockMetaClass
     *            The class of the specific {@link BlockMeta} to use.
     * @param materialId
     *            The ID of this material.
     */
    private BlockMaterial(final String materialName, final RawBlockMeta rawBlockMeta,
            final Class<? extends BlockMeta> blockMetaClass, final int materialId) {
        this(materialName, rawBlockMeta, blockMetaClass, materialId, 0);
    }

    /**
     * Constructor of BlockMaterial.
     *
     * @param materialName
     *            The name of this material.
     * @param rawBlockMeta
     *            The {@link RawBlockMeta} of this material.
     * @param materialId
     *            The ID of this material.
     */
    private BlockMaterial(final String materialName, final RawBlockMeta rawBlockMeta, final int materialId) {
        this(materialName, rawBlockMeta, BlockMeta.class, materialId, 0);
    }

    /**
     * Constructor of BlockMaterial.
     *
     * @param materialName
     *            The name of this material.
     * @param rawBlockMetaSource
     *            The {@link BlockMeta} to obtain the {@link RawBlockMeta} from.
     * @param materialId
     *            The ID of this material.
     * @param materialData
     *            The data of this material.
     */
    private BlockMaterial(final String materialName, final BlockMaterial rawBlockMetaSource, final int materialId,
            final byte materialData) {
        this(materialName, rawBlockMetaSource.getRawBlockMeta(), rawBlockMetaSource.getBlockMetaClass(), materialId, materialData);
    }

    /**
     * Constructor of BlockMaterial.
     *
     * @param materialName
     *            The name of this material.
     * @param rawBlockMetaSource
     *            The {@link BlockMeta} to obtain the {@link RawBlockMeta} from.
     * @param materialId
     *            The ID of this material.
     * @param materialData
     *            The data of this material.
     */
    private BlockMaterial(final String materialName, final BlockMaterial rawBlockMetaSource, final int materialId,
            final int materialData) {
        this(materialName, rawBlockMetaSource, materialId, (byte) materialData);
    }

    /**
     * Searchs for the {@link BlockMaterial} with the given ID and data.
     *
     * @deprecated Data values should not be used longer.
     * @param id
     *            The ID to search for.
     * @param data
     *            The data value to search for.
     * @return The {@link BlockMaterial} that is associated with the given ID
     *         and has the given data value.
     */
    @Deprecated
    public static BlockMaterial getById(final int id, final byte data) {
        for (final BlockMaterial mat : BlockMaterial.values()) {
            if (mat.getMaterialId() == id && (data == -1 || mat.getMaterialData() == data)) {
                return mat;
            }
        }
        return null;
    }

    /**
     * Searchs for the {@link BlockMaterial} with the given ID and data.
     *
     * <p>
     * <b> NOTE: This is just a wrapper for
     * {@link BlockMaterial#getById(int, byte)}. <code>Data</code> will be
     * casted to <code>byte</code>. </b>
     * </p>
     *
     * @deprecated Data values should not be used longer.
     * @param id
     *            The ID to search for.
     * @param data
     *            The data value to search for.
     * @return The {@link BlockMaterial} that is associated with the given ID
     *         and has the given data value.
     */
    @Deprecated
    public static BlockMaterial getById(final int id, final int data) {
        return BlockMaterial.getById(id, (byte) data);
    }

    /**
     * Searchs for the {@link BlockMaterial} with the given ID.
     *
     * <p>
     * <b> NOTE: This ignors the material data. </b>
     * </p>
     *
     * @deprecated Data values should not be used longer.
     * @param id
     *            The ID to search for.
     * @return The {@link BlockMaterial} that is associated with the given ID.
     *         If nothing was found, this returns <code>null</code>.
     */
    @Deprecated
    public static BlockMaterial getById(final int id) {
        return BlockMaterial.getById(id, -1);
    }

    /**
     * Searchs for the {@link BlockMaterial} with the given name.
     *
     * @param name
     *            The name of the {@link BlockMaterial} to search for.
     * @return The {@link BlockMaterial} that is associated with the given name.
     *         If nothing was found, this returns <code>null</code>.
     */
    public static BlockMaterial getByName(final String name) {
        for (final BlockMaterial mat : BlockMaterial.values()) {
            if (mat.getMaterialName().equalsIgnoreCase(name)) {
                return mat;
            }
        }
        return null;
    }

    /**
     *
     * @return Whether this block has a custon block meta class (the block meta
     *         class {@link BlockMaterial#blockMetaClass} is not equal to the
     *         class object of {@link BlockMeta}).
     */
    public boolean hasCustomBlockMetaClass() {
        return this.blockMetaClass != BlockMeta.class;
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
     * @deprecated Data values should not be used longer.
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
     * @deprecated Data values should not be used longer.
     * @see com.blockhaus2000.ipm.minecraft.material.Material#getMaterialData()
     */
    @Deprecated
    @Override
    public byte getMaterialData() {
        return this.materialData;
    }

    /**
     *
     * @return {@link BlockMaterial#rawBlockMeta}
     */
    public RawBlockMeta getRawBlockMeta() {
        return this.rawBlockMeta;
    }

    /**
     *
     * @return {@link BlockMaterial#blockMetaClass}
     */
    public Class<? extends BlockMeta> getBlockMetaClass() {
        return this.blockMetaClass;
    }
}
