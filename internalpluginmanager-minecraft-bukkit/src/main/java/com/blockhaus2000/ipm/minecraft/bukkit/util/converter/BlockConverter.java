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
package com.blockhaus2000.ipm.minecraft.bukkit.util.converter;

import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.NetherWartsState;
import org.bukkit.block.Beacon;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.BrewingStand;
import org.bukkit.block.Chest;
import org.bukkit.block.CommandBlock;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Dropper;
import org.bukkit.block.Furnace;
import org.bukkit.block.Hopper;
import org.bukkit.block.Jukebox;
import org.bukkit.block.NoteBlock;
import org.bukkit.block.Sign;
import org.bukkit.block.Skull;
import org.bukkit.inventory.Inventory;
import org.bukkit.material.Bed;
import org.bukkit.material.Button;
import org.bukkit.material.Cake;
import org.bukkit.material.Cauldron;
import org.bukkit.material.CocoaPlant;
import org.bukkit.material.CocoaPlant.CocoaPlantSize;
import org.bukkit.material.DetectorRail;
import org.bukkit.material.Diode;
import org.bukkit.material.Directional;
import org.bukkit.material.Lever;
import org.bukkit.material.MaterialData;
import org.bukkit.material.NetherWarts;
import org.bukkit.material.Openable;
import org.bukkit.material.PistonBaseMaterial;
import org.bukkit.material.PoweredRail;
import org.bukkit.material.TrapDoor;
import org.bukkit.material.Tripwire;
import org.bukkit.material.TripwireHook;

import com.blockhaus2000.ipm.minecraft.Direction;
import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.minecraft.Note;
import com.blockhaus2000.ipm.minecraft.SkullType;
import com.blockhaus2000.ipm.minecraft.block.Block;
import com.blockhaus2000.ipm.minecraft.block.BlockMaterial;
import com.blockhaus2000.ipm.minecraft.block.concrete.ConcreteBlock;
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
import com.blockhaus2000.ipm.minecraft.block.meta.RedstoneComparatorBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.RedstoneRepeaterBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.RedstoneWireBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.SignBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.StemBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.TrappedChestBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.TripwireBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.TripwireHookBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteActivatorRailBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteAnvilBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteBeaconBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteBedBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteBrewingStandBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteButtonBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteCakeBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteCarrotAndPotatoBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteCauldronBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteChestBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteCocoaBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteCommandBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteDetectorRailBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteDispenserAndDropperBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteDoorBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteEndPortalFrameBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteFarmlandBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteFlowerPotBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteFurnaceBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteHopperBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteJukeboxBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteLeverBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteMobHeadBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteMonsterSpawnerBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteNetherWartBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteNoteBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcretePistonBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcretePoweredRailBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcretePressurePlateBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteRedstoneComparatorBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteRedstoneRepeaterBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteRedstoneWireBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteSignBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteStemBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteTrappedChestBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteTripwireBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.impl.ConcreteTripwireHookBlockMeta;
import com.blockhaus2000.ipm.minecraft.bukkit.inventory.BukkitInventory;
import com.blockhaus2000.ipm.minecraft.bukkit.util.InventoryUtil;
import com.blockhaus2000.ipm.minecraft.entity.EntityType;
import com.blockhaus2000.ipm.minecraft.record.RecordMaterial;
import com.blockhaus2000.ipm.minecraft.state.AnvilDamageState;
import com.blockhaus2000.ipm.minecraft.state.CauldronFillState;
import com.blockhaus2000.ipm.minecraft.state.RedstoneComparatorState;
import com.blockhaus2000.ipm.minecraft.state.grow.CarrotAndPotatoGrowState;
import com.blockhaus2000.ipm.minecraft.state.grow.CocaoBeanGrowState;
import com.blockhaus2000.ipm.minecraft.state.grow.NetherWartGrowState;
import com.blockhaus2000.ipm.minecraft.state.grow.StemGrowState;
import com.blockhaus2000.ipm.minecraft.util.WorldLocation;

/**
 * A utility class to convert Bukkit {@link org.bukkit.block.Block}s into IPM
 * {@link Block}s and the other way.
 *
 */
public final class BlockConverter {
    /**
     * Constructor of BlockConverter.
     *
     */
    private BlockConverter() {
        // Nothing to do.
    }

    /**
     * Converts the given Bukkit {@link org.bukkit.block.Block} into an IPM
     * {@link Block}.
     *
     * @param bukkitBlock
     *            The Bukkit {@link org.bukkit.block.Block} to convert.
     * @return The converted IPM {@link Block}.
     */
    @SuppressWarnings("deprecation")
    public static Block convertToIpmBlock(final org.bukkit.block.Block bukkitBlock) {
        assert bukkitBlock != null : "BukkitBlock cannot be null!";

        final BlockMaterial blockMaterial = BlockMaterial.getById(bukkitBlock.getTypeId(), bukkitBlock.getData());

        final BlockState bukkitBlockState = bukkitBlock.getState();

        final Direction direction = DirectionConverter
                .convertToIpmDirection(bukkitBlockState.getData() instanceof Directional ? ((Directional) bukkitBlockState
                        .getData()).getFacing() : BlockFace.SELF);

        BlockMeta blockMeta = new ConcreteBlockMeta(blockMaterial.getRawBlockMeta(), bukkitBlock.isBlockPowered(),
                bukkitBlock.isBlockIndirectlyPowered(), direction);
        if (blockMaterial.hasCustomBlockMetaClass()) {
            switch (blockMaterial) {
                case DISPENSER:
                    final Dispenser bukkitDispenserBlock = (Dispenser) bukkitBlockState;

                    blockMeta = new ConcreteDispenserAndDropperBlockMeta(blockMeta,
                            BukkitInventory.Factory.create(bukkitDispenserBlock.getInventory()));

                    break;
                case DROPPER:
                    final Dropper bukkitDropperBlock = (Dropper) bukkitBlockState;

                    blockMeta = new ConcreteDispenserAndDropperBlockMeta(blockMeta,
                            BukkitInventory.Factory.create(bukkitDropperBlock.getInventory()));

                    break;
                case NOTE_BLOCK:
                    final NoteBlock bukkitNoteBlock = (NoteBlock) bukkitBlockState;

                    blockMeta = new ConcreteNoteBlockMeta(blockMeta, Note.getById(bukkitNoteBlock.getNote().getId()));

                    break;
                case BED:
                    final Bed bukkitBedMaterial = (Bed) bukkitBlockState.getData();

                    int data = bukkitBedMaterial.getData();
                    switch (bukkitBedMaterial.getFacing()) {
                        case EAST:
                            data--;
                            // FALL-THROUGH
                        case NORTH:
                            data--;
                            // FALL-THROUGH
                        case WEST:
                            data--;
                            // FALL-THROUGH
                        case SOUTH:
                            break;
                        default:
                            BlockConverter.illegalSwitchCase();
                            return null;
                    }

                    final boolean isBedHead = bukkitBedMaterial.isHeadOfBed();
                    if (isBedHead) {
                        data = data - 8;
                    }
                    final boolean isPlayerInside = data == 4;

                    blockMeta = new ConcreteBedBlockMeta(blockMeta, isPlayerInside, isBedHead);
                    break;
                case POWERED_RAIL:
                    final PoweredRail bukkitPoweredRailMaterial = (PoweredRail) bukkitBlockState.getData();

                    blockMeta = new ConcretePoweredRailBlockMeta(blockMeta, bukkitPoweredRailMaterial.isPowered());

                    break;
                case DETECTOR_RAIL:
                    final DetectorRail bukkitDetectorRailMaterial = (DetectorRail) bukkitBlockState.getData();

                    blockMeta = new ConcreteDetectorRailBlockMeta(blockMeta, bukkitDetectorRailMaterial.isPressed());

                    break;
                case STICKY_PISTON:
                case PISTON:
                    final PistonBaseMaterial bukkitPistonBaseMaterial = (PistonBaseMaterial) bukkitBlockState.getData();

                    blockMeta = new ConcretePistonBlockMeta(blockMeta, bukkitPistonBaseMaterial.isPowered());

                    break;
                case MONSTER_SPAWNER:
                    final CreatureSpawner bukkitCreatureSpawnerBlock = (CreatureSpawner) bukkitBlockState;

                    blockMeta = new ConcreteMonsterSpawnerBlockMeta(blockMeta, EntityType.getByName(bukkitCreatureSpawnerBlock
                            .getSpawnedType().getName()));

                    break;
                case CHEST:
                    final Chest bukkitChestBlock = (Chest) bukkitBlockState;

                    final Inventory bukkitChestBlockInventory = bukkitChestBlock.getInventory();
                    blockMeta = new ConcreteChestBlockMeta(blockMeta, BukkitInventory.Factory.create(bukkitChestBlockInventory),
                            bukkitChestBlockInventory.getViewers().size() != 0);

                    break;
                case REDSTONE_WIRE:
                    blockMeta = new ConcreteRedstoneWireBlockMeta(blockMeta, bukkitBlockState.getData().getData());

                    break;
                case FURNACE:
                case BURNING_FURNACE:
                    final Furnace bukkitFurnaceBlock = (Furnace) bukkitBlockState;

                    blockMeta = new ConcreteFurnaceBlockMeta(blockMeta, BukkitInventory.Factory.create(bukkitFurnaceBlock
                            .getInventory()), bukkitFurnaceBlock.getBurnTime() != 0);

                    break;
                case STANDING_SIGN_BLOCK:
                case WALL_MOUNTED_SIGN_BLOCK:
                    final Sign bukkitSignBlock = (Sign) bukkitBlockState;

                    blockMeta = new ConcreteSignBlockMeta(blockMeta, bukkitSignBlock.getLine(0), bukkitSignBlock.getLine(1),
                            bukkitSignBlock.getLine(2), bukkitSignBlock.getLine(3));

                    break;
                case OAK_DOOR_BLOCK:
                case IRON_DOOR_BLOCK:
                    final Openable bukkitOpenableMaterial = (Openable) bukkitBlockState.getData();

                    blockMeta = new ConcreteDoorBlockMeta(blockMeta, bukkitOpenableMaterial.isOpen());

                    break;
                case WOODEN_TRAPDOOR:
                case IRON_TRAPDOOR:
                    final TrapDoor bukkitTrapDoorMaterial = (TrapDoor) bukkitBlockState.getData();

                    blockMeta = new ConcreteDoorBlockMeta(blockMeta, bukkitTrapDoorMaterial.isOpen());

                    break;
                case LEVER:
                    final Lever bukkitLevelMaterial = (Lever) bukkitBlockState.getData();

                    blockMeta = new ConcreteLeverBlockMeta(blockMeta, bukkitLevelMaterial.isPowered());

                    break;
                case STONE_PRESSURE_PLATE:
                case WOODEN_PRESSURE_PLATE:
                case WEIGHTED_PRESSURE_PLATE_LIGHT:
                case WEIGHTED_PRESSURE_PLATE_HEAVY:
                    final boolean isPressed = bukkitBlockState.getData().getData() == 1;

                    blockMeta = new ConcretePressurePlateBlockMeta(blockMeta, isPressed);

                    break;
                case STONE_BUTTON:
                case WOODEN_BUTTON:
                    final Button bukkitButtonMaterial = (Button) bukkitBlockState.getData();

                    blockMeta = new ConcreteButtonBlockMeta(blockMeta, bukkitButtonMaterial.isPowered());

                    break;
                case JUKEBOX:
                    final Jukebox bukkitJukeboxBlock = (Jukebox) bukkitBlockState;

                    blockMeta = new ConcreteJukeboxBlockMeta(blockMeta, bukkitJukeboxBlock.isPlaying(),
                            RecordMaterial.getById(bukkitJukeboxBlock.getPlaying().getId()));

                    break;
                case CAKE_BLOCK:
                    final Cake bukkitCakeMaterial = (Cake) bukkitBlockState.getData();

                    blockMeta = new ConcreteCakeBlockMeta(blockMeta, bukkitCakeMaterial.getSlicesRemaining());

                    break;
                case REDSTONE_REPEATER_BLOCK_OFF:
                case REDSTONE_REPEATER_BLOCK_ON:
                    final Diode bukkitDiodeMaterial = (Diode) bukkitBlockState.getData();

                    blockMeta = new ConcreteRedstoneRepeaterBlockMeta(blockMeta, bukkitBlock.isBlockPowered(),
                            bukkitDiodeMaterial.getDelay());

                    break;
                case PUMPKIN_STEM:
                case MELON_STEM:
                    blockMeta = new ConcreteStemBlockMeta(blockMeta, StemGrowState.getById(bukkitBlockState.getData().getData()));

                    break;
                case NETHER_WART:
                    final NetherWarts bukkitNetherWartsMaterial = (NetherWarts) bukkitBlockState.getData();

                    final NetherWartGrowState netherWartGrowState;
                    switch (bukkitNetherWartsMaterial.getState()) {
                        case SEEDED:
                            netherWartGrowState = NetherWartGrowState.SEED;
                            break;
                        case STAGE_ONE:
                            netherWartGrowState = NetherWartGrowState.STATE_ONE;
                            break;
                        case STAGE_TWO:
                            netherWartGrowState = NetherWartGrowState.STATE_TWO;
                            break;
                        case RIPE:
                            netherWartGrowState = NetherWartGrowState.RIPE;
                            break;
                        default:
                            BlockConverter.illegalSwitchCase();
                            return null;
                    }

                    blockMeta = new ConcreteNetherWartBlockMeta(blockMeta, netherWartGrowState);

                    break;
                case BREWING_STAND:
                    final BrewingStand bukkitBrewingStandBlock = (BrewingStand) bukkitBlockState;

                    blockMeta = new ConcreteBrewingStandBlockMeta(blockMeta,
                            BukkitInventory.Factory.create(bukkitBrewingStandBlock.getInventory()),
                            bukkitBrewingStandBlock.getBrewingTime(), bukkitBrewingStandBlock.getBrewingTime() != 0);

                    break;
                case CAULDRON:
                    final Cauldron bukkitCauldronMaterial = (Cauldron) bukkitBlockState.getData();

                    blockMeta = new ConcreteCauldronBlockMeta(blockMeta, CauldronFillState.getById(bukkitCauldronMaterial
                            .getData()));

                    break;
                case END_PORTAL_FRAME:
                    final boolean containsEnderPerl = bukkitBlockState.getData().getData() - 4 > 0;

                    blockMeta = new ConcreteEndPortalFrameBlockMeta(blockMeta, containsEnderPerl);

                    break;
                case COCOA:
                    final CocoaPlant bukkitCocoaPlantMaterial = (CocoaPlant) bukkitBlockState.getData();

                    final CocaoBeanGrowState cocaoBeanGrowState;
                    switch (bukkitCocoaPlantMaterial.getSize()) {
                        case SMALL:
                            cocaoBeanGrowState = CocaoBeanGrowState.SEED;
                            break;
                        case MEDIUM:
                            cocaoBeanGrowState = CocaoBeanGrowState.MEDIUM;
                            break;
                        case LARGE:
                            cocaoBeanGrowState = CocaoBeanGrowState.RIPE;
                            break;
                        default:
                            BlockConverter.illegalSwitchCase();
                            return null;
                    }

                    blockMeta = new ConcreteCocoaBlockMeta(blockMeta, cocaoBeanGrowState);

                    break;
                case TRIPWIRE_HOOK:
                    final TripwireHook bukkitTripwireHookMaterial = (TripwireHook) bukkitBlockState.getData();

                    blockMeta = new ConcreteTripwireHookBlockMeta(blockMeta, bukkitTripwireHookMaterial.isConnected(),
                            bukkitTripwireHookMaterial.isActivated());

                    break;
                case TRIPWIRE:
                    final Tripwire bukkitTripwireMaterial = (Tripwire) bukkitBlockState.getData();

                    blockMeta = new ConcreteTripwireBlockMeta(blockMeta, bukkitTripwireMaterial.isActivated());

                    break;
                case COMMAND_BLOCK:
                    final CommandBlock bukkitCommandBlock = (CommandBlock) bukkitBlockState;

                    blockMeta = new ConcreteCommandBlockMeta(blockMeta, bukkitCommandBlock.getCommand());

                    break;
                case BEACON:
                    final Beacon bukkitBeaconBlock = (Beacon) bukkitBlockState;

                    blockMeta = new ConcreteBeaconBlockMeta(blockMeta, BukkitInventory.Factory.create(bukkitBeaconBlock
                            .getInventory()));

                    break;
                case FLOWER_POT:
                    final BlockMaterial itemType;
                    switch (bukkitBlockState.getData().getData()) {
                        case 1:
                            itemType = BlockMaterial.POPPY;
                            break;
                        case 2:
                            itemType = BlockMaterial.DANDELION;
                            break;
                        case 3:
                            itemType = BlockMaterial.OAK_SAPLING;
                            break;
                        case 4:
                            itemType = BlockMaterial.SPRUCE_SAPLING;
                            break;
                        case 5:
                            itemType = BlockMaterial.BIRCH_SAPLING;
                            break;
                        case 6:
                            itemType = BlockMaterial.JUNGLE_SAPLING;
                            break;
                        case 7:
                            itemType = BlockMaterial.RED_MUSHROOM;
                            break;
                        case 8:
                            itemType = BlockMaterial.BROWN_MUSHROOM;
                            break;
                        case 9:
                            itemType = BlockMaterial.CACTUS;
                            break;
                        case 10:
                            itemType = BlockMaterial.DEAD_SHRUB;
                            break;
                        case 11:
                            itemType = BlockMaterial.FERN;
                            break;
                        case 12:
                            itemType = BlockMaterial.ACACIA_SAPLING;
                            break;
                        case 13:
                            itemType = BlockMaterial.DARK_OAK_SAPLING;
                            break;
                        default:
                            BlockConverter.illegalSwitchCase();
                            return null;
                    }

                    blockMeta = new ConcreteFlowerPotBlockMeta(blockMeta, itemType);

                    break;
                case CARROTS:
                case POTATOES:
                    blockMeta = new ConcreteCarrotAndPotatoBlockMeta(blockMeta,
                            CarrotAndPotatoGrowState.getByStateId(bukkitBlockState.getData().getData()));

                    break;
                case MOB_HEAD:
                    final Skull bukkitSkullBlock = (Skull) bukkitBlockState;

                    final SkullType skullType;
                    switch (bukkitSkullBlock.getSkullType()) {
                        case CREEPER:
                            skullType = SkullType.CREEPER;
                            break;
                        case PLAYER:
                            skullType = SkullType.PLAYER;
                            break;
                        case SKELETON:
                            skullType = SkullType.SKELETON;
                            break;
                        case WITHER:
                            skullType = SkullType.WITHER_SKELETON;
                            break;
                        case ZOMBIE:
                            skullType = SkullType.ZOMBIE;
                            break;
                        default:
                            BlockConverter.illegalSwitchCase();
                            return null;
                    }

                    blockMeta = new ConcreteMobHeadBlockMeta(blockMeta, skullType, bukkitSkullBlock.getOwner());

                    break;
                case ANVIL:
                    final byte bukkitAnvilData = bukkitBlockState.getData().getData();

                    final AnvilDamageState damageState;
                    if ((bukkitAnvilData & 0x04) == 0x04) {
                        damageState = AnvilDamageState.DAMAGED;
                    } else if ((bukkitAnvilData & 0x08) == 0x08) {
                        damageState = AnvilDamageState.VERY_DAMAGED;
                    } else {
                        damageState = AnvilDamageState.NOT_DAMAGED;
                    }

                    blockMeta = new ConcreteAnvilBlockMeta(blockMeta, damageState);

                    break;
                case TRAPPED_CHEST:
                    final Chest bukkitTrappedChestBlock = (Chest) bukkitBlockState;

                    final Inventory bukkitTrappedChestInventory = bukkitTrappedChestBlock.getBlockInventory();
                    final int bukkitTrappedChestViewersInventory = bukkitTrappedChestInventory.getViewers().size();

                    blockMeta = new ConcreteTrappedChestBlockMeta(blockMeta, bukkitTrappedChestViewersInventory > 0,
                            BukkitInventory.Factory.create(bukkitTrappedChestInventory), bukkitTrappedChestViewersInventory);

                    break;
                case REDSTONE_COMPARATOR_INACTIVE:
                case REDSTONE_COMPARATOR_ACTIVE:
                    final RedstoneComparatorState comparatorState;
                    if ((bukkitBlockState.getData().getData() & 0x08) == 0x08) {
                        comparatorState = RedstoneComparatorState.SUBTRACT;
                    } else {
                        comparatorState = RedstoneComparatorState.COMPARE;
                    }

                    blockMeta = new ConcreteRedstoneComparatorBlockMeta(blockMeta, comparatorState);

                    break;
                case HOPPER:
                    final Hopper bukkitHopperBlock = (Hopper) bukkitBlockState;

                    final Direction outputFace;
                    switch (bukkitHopperBlock.getData().getData()) {
                        case 0:
                            outputFace = Direction.DOWN;
                            break;
                        case 2:
                            outputFace = Direction.NORTH;
                            break;
                        case 3:
                            outputFace = Direction.SOUTH;
                            break;
                        case 4:
                            outputFace = Direction.WEST;
                            break;
                        case 5:
                            outputFace = Direction.EAST;
                            break;
                        default:
                            outputFace = null;
                            break;
                    }

                    blockMeta = new ConcreteHopperBlockMeta(blockMeta, BukkitInventory.Factory.create(bukkitHopperBlock
                            .getInventory()), outputFace);

                    break;
                case ACTIVATOR_RAIL:
                    final boolean isActive = (bukkitBlockState.getData().getData() & 0x08) == 0x08;

                    blockMeta = new ConcreteActivatorRailBlockMeta(blockMeta, isActive);

                    break;
                case FARMLAND:
                    final boolean isWet = bukkitBlockState.getData().getData() != 0;

                    blockMeta = new ConcreteFarmlandBlockMeta(blockMeta, isWet);

                    break;
                default:
                    BlockConverter.illegalSwitchCase();
                    return null;
            }
        }

        return new ConcreteBlock(blockMeta, direction, blockMaterial);
    }

    /**
     * Converts the given IPM {@link Block} into a Bukkit
     * {@link org.bukkit.block.Block}.
     *
     * <p>
     * <b> CAUTION: This modifies the block within the "real" Minecraft world
     * immediatly! </b>
     * </p>
     *
     * @param ipmBlock
     *            The IPM {@link Block} to convert.
     * @param loc
     *            The {@link WorldLocation} of the block to convert.
     * @return The converted Bukkit {@link org.bukkit.block.Block}.
     */
    @SuppressWarnings("deprecation")
    public static org.bukkit.block.Block convertToBukkitBlock(final Block ipmBlock, final WorldLocation loc) {
        assert ipmBlock != null : "IpmBlock cannot be null!";
        assert loc != null : "Loc cannot be null!";

        final org.bukkit.block.Block block = WorldConverter.convertToBukkitWorld(loc.getWorld()).getBlockAt(
                LocationConverter.convertToBukkitLocation(loc));

        block.setTypeIdAndData(ipmBlock.getMaterial().getMaterialId(), ipmBlock.getMaterial().getMaterialData(), true);

        if (ipmBlock.getFaceDirection() != null && block instanceof Directional) {
            ((Directional) block).setFacingDirection(DirectionConverter.convertToBukkitDirection(ipmBlock.getFaceDirection()));
        }

        final BlockMaterial ipmBlockMaterial = ipmBlock.getMaterial();
        if (ipmBlockMaterial.hasCustomBlockMetaClass()) {
            final BlockMeta ipmBlockMeta = ipmBlock.getMeta();
            final BlockState blockState = block.getState();

            switch (ipmBlockMaterial) {
                case DISPENSER:
                case DROPPER:
                    final DispenserAndDropperBlockMeta ipmAndDropperBlockMeta = (DispenserAndDropperBlockMeta) ipmBlockMeta;

                    if (ipmBlockMaterial == BlockMaterial.DISPENSER) {
                        InventoryUtil.copyContent(ipmAndDropperBlockMeta.getInventory(), ((Dispenser) blockState).getInventory());
                    } else {
                        InventoryUtil.copyContent(ipmAndDropperBlockMeta.getInventory(), ((Dropper) blockState).getInventory());
                    }

                    break;
                case NOTE_BLOCK:
                    final NoteBlockMeta ipmNoteBlockMeta = (NoteBlockMeta) ipmBlockMeta;

                    ((NoteBlock) blockState).setNote(new org.bukkit.Note(ipmNoteBlockMeta.getNote().getId()));

                    break;
                case BED:
                    final BedBlockMeta ipmBedBlockMeta = (BedBlockMeta) ipmBlockMeta;

                    ((Bed) blockState).setHeadOfBed(ipmBedBlockMeta.isHead());

                    break;
                case POWERED_RAIL:
                    final PoweredRailBlockMeta ipmPoweredRailBlockMeta = (PoweredRailBlockMeta) ipmBlockMeta;

                    ((PoweredRail) blockState.getData()).setPowered(ipmPoweredRailBlockMeta.isPowered());

                    break;
                case DETECTOR_RAIL:
                    final DetectorRailBlockMeta ipmDetectorRailBlockMeta = (DetectorRailBlockMeta) ipmBlockMeta;

                    ((DetectorRail) blockState.getData()).setPressed(ipmDetectorRailBlockMeta.isActive());

                    break;
                case STICKY_PISTON:
                case PISTON:
                    final PistonBlockMeta ipmPistonBlockMeta = (PistonBlockMeta) ipmBlockMeta;

                    ((PistonBaseMaterial) blockState.getData()).setPowered(ipmPistonBlockMeta.isExtended());

                    break;
                case MONSTER_SPAWNER:
                    final MonsterSpawnerBlockMeta ipmMonsterSpawnerBlockMeta = (MonsterSpawnerBlockMeta) ipmBlockMeta;

                    ((CreatureSpawner) blockState).setSpawnedType(org.bukkit.entity.EntityType
                            .fromName(ipmMonsterSpawnerBlockMeta.getEntity().getEntityName()));

                    break;
                case CHEST:
                    final ChestBlockMeta ipmChestBlockMeta = (ChestBlockMeta) ipmBlockMeta;

                    InventoryUtil.copyContent(ipmChestBlockMeta.getInventory(), ((Chest) blockState).getBlockInventory());

                    break;
                case REDSTONE_WIRE:
                    final RedstoneWireBlockMeta ipmRedstoneWireBlockMeta = (RedstoneWireBlockMeta) ipmBlockMeta;

                    blockState.getData().setData((byte) ipmRedstoneWireBlockMeta.getRedstoneStrength());

                    break;
                case FURNACE:
                case BURNING_FURNACE:
                    final FurnaceBlockMeta ipmFurnaceBlockMeta = (FurnaceBlockMeta) ipmBlockMeta;

                    InventoryUtil.copyContent(ipmFurnaceBlockMeta.getInventory(), ((Furnace) blockState).getInventory());

                    break;
                case STANDING_SIGN_BLOCK:
                case WALL_MOUNTED_SIGN_BLOCK:
                    final SignBlockMeta ipmSignBlockMeta = (SignBlockMeta) ipmBlockMeta;

                    final Sign signBlock = (Sign) blockState;
                    signBlock.setLine(0, ipmSignBlockMeta.getFirstLine());
                    signBlock.setLine(1, ipmSignBlockMeta.getSecondLine());
                    signBlock.setLine(2, ipmSignBlockMeta.getThirdLine());
                    signBlock.setLine(3, ipmSignBlockMeta.getFourthLine());

                    break;
                case OAK_DOOR_BLOCK:
                case IRON_DOOR_BLOCK:
                    final DoorBlockMeta ipmDoorBlockMeta = (DoorBlockMeta) ipmBlockMeta;

                    ((Openable) blockState.getData()).setOpen(ipmDoorBlockMeta.isOpen());

                    break;
                case WOODEN_TRAPDOOR:
                case IRON_TRAPDOOR:
                    final DoorBlockMeta ipmTrapdoorBlockMeta = (DoorBlockMeta) ipmBlockMeta;

                    ((TrapDoor) blockState.getData()).setOpen(ipmTrapdoorBlockMeta.isOpen());

                    break;
                case LEVER:
                    final LeverBlockMeta ipmLeverBlockMeta = (LeverBlockMeta) ipmBlockMeta;

                    ((Lever) blockState.getData()).setPowered(ipmLeverBlockMeta.isActive());

                    break;
                case STONE_PRESSURE_PLATE:
                case WOODEN_PRESSURE_PLATE:
                case WEIGHTED_PRESSURE_PLATE_LIGHT:
                case WEIGHTED_PRESSURE_PLATE_HEAVY:
                    final PressurePlateBlockMeta ipmPressurePlateBlockMeta = (PressurePlateBlockMeta) ipmBlockMeta;

                    blockState.getData().setData((byte) (ipmPressurePlateBlockMeta.isPressed() ? 1 : 0));

                    break;
                case STONE_BUTTON:
                case WOODEN_BUTTON:
                    final ButtonBlockMeta ipmButtonBlockMeta = (ButtonBlockMeta) ipmBlockMeta;

                    ((Button) blockState.getData()).setPowered(ipmButtonBlockMeta.isPressed());

                    break;
                case JUKEBOX:
                    final JukeboxBlockMeta ipmJukeboxBlockMeta = (JukeboxBlockMeta) ipmBlockMeta;

                    ((Jukebox) blockState).setPlaying(Material.getMaterial(ipmJukeboxBlockMeta.getRecord().getMaterialId()));

                    break;
                case CAKE_BLOCK:
                    final CakeBlockMeta ipmCakeBlockMeta = (CakeBlockMeta) ipmBlockMeta;

                    ((Cake) blockState.getData()).setSlicesRemaining(ipmCakeBlockMeta.getSize());

                    break;
                case REDSTONE_REPEATER_BLOCK_OFF:
                case REDSTONE_REPEATER_BLOCK_ON:
                    final RedstoneRepeaterBlockMeta ipmRedstoneRepeaterBlockMeta = (RedstoneRepeaterBlockMeta) ipmBlockMeta;

                    ((Diode) blockState.getData()).setDelay(ipmRedstoneRepeaterBlockMeta.getState());

                    break;
                case PUMPKIN_STEM:
                case MELON_STEM:
                    final StemBlockMeta ipmStemBlockMeta = (StemBlockMeta) ipmBlockMeta;

                    blockState.getData().setData((byte) ipmStemBlockMeta.getGrowState().getStateId());

                    break;
                case NETHER_WART:
                    final NetherWartBlockMeta ipmNetherWartBlockMeta = (NetherWartBlockMeta) ipmBlockMeta;

                    final NetherWartsState netherWartGrowState;
                    switch (ipmNetherWartBlockMeta.getGrowState()) {
                        case SEED:
                            netherWartGrowState = NetherWartsState.SEEDED;
                            break;
                        case STATE_ONE:
                            netherWartGrowState = NetherWartsState.STAGE_ONE;
                            break;
                        case STATE_TWO:
                            netherWartGrowState = NetherWartsState.STAGE_TWO;
                            break;
                        case RIPE:
                            netherWartGrowState = NetherWartsState.RIPE;
                            break;
                        default:
                            BlockConverter.illegalSwitchCase();
                            return null;
                    }

                    ((NetherWarts) blockState.getData()).setState(netherWartGrowState);

                    break;
                case BREWING_STAND:
                    final BrewingStandBlockMeta ipmBrewingStandBlockMeta = (BrewingStandBlockMeta) ipmBlockMeta;

                    final BrewingStand brewingStandBlock = (BrewingStand) blockState;
                    brewingStandBlock.setBrewingTime(ipmBrewingStandBlockMeta.getRemainingBrewingTime());
                    InventoryUtil.copyContent(ipmBrewingStandBlockMeta.getInventory(), brewingStandBlock.getInventory());

                    break;
                case CAULDRON:
                    final CauldronBlockMeta ipmCauldronBlockMeta = (CauldronBlockMeta) ipmBlockMeta;

                    blockState.getData().setData((byte) ipmCauldronBlockMeta.getFillState().getId());

                    break;
                case END_PORTAL_FRAME:
                    final EndPortalFrameBlockMeta ipmEndPortalFrameBlockMeta = (EndPortalFrameBlockMeta) ipmBlockMeta;

                    if (ipmEndPortalFrameBlockMeta.containsEnderPerl()) {
                        final MaterialData endPortalFrameMaterialData = blockState.getData();
                        endPortalFrameMaterialData.setData((byte) (endPortalFrameMaterialData.getData() | 0x04));
                    }

                    break;
                case COCOA:
                    final CocoaBlockMeta ipmCocoaBlockMeta = (CocoaBlockMeta) ipmBlockMeta;

                    final CocoaPlantSize cocoaPlantSize;
                    switch (ipmCocoaBlockMeta.getGrowState()) {
                        case SEED:
                            cocoaPlantSize = CocoaPlantSize.SMALL;
                            break;
                        case MEDIUM:
                            cocoaPlantSize = CocoaPlantSize.MEDIUM;
                            break;
                        case RIPE:
                            cocoaPlantSize = CocoaPlantSize.LARGE;
                            break;
                        default:
                            BlockConverter.illegalSwitchCase();
                            return null;
                    }

                    ((CocoaPlant) blockState.getData()).setSize(cocoaPlantSize);

                    break;
                case TRIPWIRE_HOOK:
                    final TripwireHookBlockMeta ipmTripwireHookBlockMeta = (TripwireHookBlockMeta) ipmBlockMeta;

                    final TripwireHook tripwireHookMaterial = (TripwireHook) blockState.getData();
                    tripwireHookMaterial.setConnected(ipmTripwireHookBlockMeta.isTensioned());
                    tripwireHookMaterial.setActivated(ipmTripwireHookBlockMeta.isActive());

                    break;
                case TRIPWIRE:
                    final TripwireBlockMeta ipmTripwireBlockMeta = (TripwireBlockMeta) ipmBlockMeta;

                    ((Tripwire) blockState.getData()).setActivated(ipmTripwireBlockMeta.isActive());

                    break;
                case COMMAND_BLOCK:
                    final CommandBlockMeta ipmCommandBlockMeta = (CommandBlockMeta) ipmBlockMeta;

                    ((CommandBlock) blockState).setCommand(ipmCommandBlockMeta.getCommand());

                    break;
                case BEACON:
                    final BeaconBlockMeta ipmBeaconBlockMeta = (BeaconBlockMeta) ipmBlockMeta;

                    InventoryUtil.copyContent(ipmBeaconBlockMeta.getInventory(), ((Beacon) blockState).getInventory());

                    break;
                case FLOWER_POT:
                    final FlowerPotBlockMeta ipmFlowerPotBlockMeta = (FlowerPotBlockMeta) ipmBlockMeta;

                    final int flowerPotContentData;
                    switch (ipmFlowerPotBlockMeta.getItemType()) {
                        case POPPY:
                            flowerPotContentData = 1;
                            break;
                        case DANDELION:
                            flowerPotContentData = 2;
                            break;
                        case OAK_SAPLING:
                            flowerPotContentData = 3;
                            break;
                        case SPRUCE_SAPLING:
                            flowerPotContentData = 4;
                            break;
                        case BIRCH_SAPLING:
                            flowerPotContentData = 5;
                            break;
                        case JUNGLE_SAPLING:
                            flowerPotContentData = 6;
                            break;
                        case RED_MUSHROOM:
                            flowerPotContentData = 7;
                            break;
                        case BROWN_MUSHROOM:
                            flowerPotContentData = 8;
                            break;
                        case CACTUS:
                            flowerPotContentData = 9;
                            break;
                        case DEAD_SHRUB:
                            flowerPotContentData = 10;
                            break;
                        case FERN:
                            flowerPotContentData = 11;
                            break;
                        case ACACIA_SAPLING:
                            flowerPotContentData = 12;
                            break;
                        case DARK_OAK_SAPLING:
                            flowerPotContentData = 13;
                            break;
                        default:
                            BlockConverter.illegalSwitchCase();
                            return null;
                    }

                    blockState.getData().setData((byte) flowerPotContentData);

                    break;
                case CARROTS:
                case POTATOES:
                    final CarrotAndPotatoBlockMeta ipmCarrotAndPotatoBlockMeta = (CarrotAndPotatoBlockMeta) ipmBlockMeta;

                    blockState.getData().setData((byte) ipmCarrotAndPotatoBlockMeta.getGrowState().getStateId());

                    break;
                case MOB_HEAD:
                    final MobHeadBlockMeta ipmMobHeadBlockMeta = (MobHeadBlockMeta) ipmBlockMeta;

                    final org.bukkit.SkullType skullType;
                    switch (ipmMobHeadBlockMeta.getSkullType()) {
                        case CREEPER:
                            skullType = org.bukkit.SkullType.CREEPER;
                            break;
                        case PLAYER:
                            skullType = org.bukkit.SkullType.PLAYER;
                            break;
                        case SKELETON:
                            skullType = org.bukkit.SkullType.SKELETON;
                            break;
                        case WITHER_SKELETON:
                            skullType = org.bukkit.SkullType.WITHER;
                            break;
                        case ZOMBIE:
                            skullType = org.bukkit.SkullType.ZOMBIE;
                            break;
                        default:
                            BlockConverter.illegalSwitchCase();
                            return null;
                    }

                    final Skull skullBlock = (Skull) blockState;
                    skullBlock.setSkullType(skullType);
                    skullBlock.setOwner(ipmMobHeadBlockMeta.getPlayerName());

                    break;
                case ANVIL:
                    final AnvilBlockMeta ipmAnvilBlockMeta = (AnvilBlockMeta) ipmBlockMeta;

                    byte anvilDamageData = 0;
                    switch (ipmAnvilBlockMeta.getDamageState()) {
                        case VERY_DAMAGED:
                            anvilDamageData = (byte) (anvilDamageData + 4);
                            // FALL-THROUGH
                        case DAMAGED:
                            anvilDamageData = (byte) (anvilDamageData + 4);
                            // FALL-THROUGH
                        case NOT_DAMAGED:
                            break;
                        default:
                            BlockConverter.illegalSwitchCase();
                            return null;
                    }

                    final MaterialData anvilMaterialData = blockState.getData();
                    anvilMaterialData.setData((byte) (anvilMaterialData.getData() | anvilDamageData));

                    break;
                case TRAPPED_CHEST:
                    final TrappedChestBlockMeta ipmTrappedChestBlockMeta = (TrappedChestBlockMeta) ipmBlockMeta;

                    InventoryUtil.copyContent(ipmTrappedChestBlockMeta.getInventory(), ((Chest) blockState).getBlockInventory());

                    break;
                case REDSTONE_COMPARATOR_INACTIVE:
                case REDSTONE_COMPARATOR_ACTIVE:
                    final RedstoneComparatorBlockMeta ipmRedstoneComparatorBlockMeta = (RedstoneComparatorBlockMeta) ipmBlockMeta;

                    if (ipmRedstoneComparatorBlockMeta.getComparatorState() == RedstoneComparatorState.SUBTRACT) {
                        final MaterialData redstoneComparatorMaterialData = blockState.getData();
                        redstoneComparatorMaterialData.setData((byte) (redstoneComparatorMaterialData.getData() | 0x08));
                    }

                    break;
                case HOPPER:
                    final HopperBlockMeta ipmHopperBlockMeta = (HopperBlockMeta) ipmBlockMeta;

                    final byte outputFaceData;
                    if (ipmHopperBlockMeta.getOutputFace() != null) {
                        switch (ipmHopperBlockMeta.getOutputFace()) {
                            case DOWN:
                                outputFaceData = 0;
                                break;
                            case NORTH:
                                outputFaceData = 2;
                                break;
                            case SOUTH:
                                outputFaceData = 3;
                                break;
                            case WEST:
                                outputFaceData = 4;
                                break;
                            case EAST:
                                outputFaceData = 5;
                                break;
                            default:
                                BlockConverter.illegalSwitchCase();
                                return null;
                        }
                    } else {
                        outputFaceData = 1;
                    }

                    final MaterialData hopperMaterialData = blockState.getData();
                    hopperMaterialData.setData((byte) (hopperMaterialData.getData() | outputFaceData));

                    break;
                case ACTIVATOR_RAIL:
                    final ActivatorRailBlockMeta ipmActivatorRailBlockMeta = (ActivatorRailBlockMeta) ipmBlockMeta;

                    if (ipmActivatorRailBlockMeta.isActive()) {
                        final MaterialData activatorRailMaterialData = blockState.getData();
                        activatorRailMaterialData.setData((byte) (activatorRailMaterialData.getData() | 0x08));
                    }

                    break;
                case FARMLAND:
                    final FarmlandBlockMeta ipmFarmlandBlockMeta = (FarmlandBlockMeta) ipmBlockMeta;

                    if (ipmFarmlandBlockMeta.isWet()) {
                        // Generate a random number for the wetness, cause it is
                        // not known how wet the farmland whould be.
                        blockState.getData().setData((byte) (Math.random() * 7 + 1));
                    }

                    break;
                default:
                    BlockConverter.illegalSwitchCase();
                    return null;
            }
        }

        return block;
    }

    /**
     * Prints a message that an error occurred and the user has to check for an
     * update and/or inform a developer.
     *
     */
    private static void illegalSwitchCase() {
        InternalPluginManager
                .getServer()
                .getLogger()
                .log(Level.WARNING,
                        "An error occurred whilest converting a Bukkit block into an InternalPluginManager block or "
                                + "otherwise. Please check for an implementation update and/or inform a developer.");
    }
}
