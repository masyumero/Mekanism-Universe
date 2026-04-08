package io.github.masyumero.mekuniverse.common.tile.multiblock;

import io.github.masyumero.mekuniverse.MekanismUniverseLang;
import io.github.masyumero.mekuniverse.common.block.attribute.AttributeStateCUReactorPortMode;
import io.github.masyumero.mekuniverse.common.block.attribute.AttributeStateCUReactorPortMode.CUReactorPortMode;
import io.github.masyumero.mekuniverse.common.content.universe_reactor.ContainedUniverseReactorMultiblockData;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseBlocks;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.api.text.EnumColor;
import mekanism.common.capabilities.holder.chemical.IChemicalTankHolder;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.capabilities.holder.slot.IInventorySlotHolder;
import mekanism.common.integration.computer.annotation.ComputerMethod;
import mekanism.common.lib.multiblock.IMultiblockEjector;
import mekanism.common.tile.base.SubstanceType;
import mekanism.common.util.CableUtils;
import mekanism.common.util.ChemicalUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Set;

public class TileEntityContainedUniverseReactorPort extends TileEntityContainedUniverseReactorCasing implements IMultiblockEjector {

    private Set<Direction> outputDirections = Collections.emptySet();

    public TileEntityContainedUniverseReactorPort(BlockPos pos, BlockState state) {
        super(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_PORT, pos, state);
        delaySupplier = NO_DELAY;
    }

    @NotNull
    @Override
    public IChemicalTankHolder<Gas, GasStack, IGasTank> getInitialGasTanks(IContentsListener listener) {
        return side -> getMultiblock().getGasTanks(side);
    }

    @NotNull
    @Override
    protected IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener) {
        return side -> getMultiblock().getEnergyContainers(side);
    }

    @NotNull
    @Override
    protected IInventorySlotHolder getInitialInventory(IContentsListener listener) {
        return side -> getMultiblock().getInventorySlots(side);
    }

    @Override
    public boolean persists(SubstanceType type) {
        if (type == SubstanceType.GAS || type == SubstanceType.ENERGY) {
            return false;
        }
        return super.persists(type);
    }

    @Override
    public void setEjectSides(Set<Direction> sides) {
        outputDirections = sides;
    }

    @Override
    protected boolean onUpdateServer(ContainedUniverseReactorMultiblockData multiblock) {
        boolean needsPacket = super.onUpdateServer(multiblock);
        if (multiblock.isFormed()) {
            if (getMode() == CUReactorPortMode.OUTPUT_HELIUM) {
                ChemicalUtil.emit(this.outputDirections, multiblock.heliumOutputTank, this);
            } else if (getMode() == CUReactorPortMode.OUTPUT_ENERGY) {
                CableUtils.emit(this.outputDirections, multiblock.energyOutputContainer, this);
            }
        }
        return needsPacket;
    }

    @Override
    public InteractionResult onSneakRightClick(Player player) {
        if (!isRemote()) {
            CUReactorPortMode mode = getMode().getNext();
            setMode(mode);
            player.displayClientMessage(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_PORT_MODE_CHANGE.translateColored(EnumColor.GRAY, mode), true);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean insertGasCheck(int tank, @Nullable Direction side) {
        if (getMode() != CUReactorPortMode.INPUT) {
            //Don't allow inserting into the fuel tanks, if we are on output mode
            return false;
        }
        return super.insertGasCheck(tank, side);
    }
    
    @Override
    public boolean extractGasCheck(int tank, @Nullable Direction side) {
        CUReactorPortMode mode = getMode();
        if (mode == CUReactorPortMode.INPUT || mode == CUReactorPortMode.OUTPUT_ENERGY) {
            // don't allow extraction from tanks based on mode
            return false;
        }
        return super.extractGasCheck(tank, side);
    }

    @Override
    public int getRedstoneLevel() {
        return getMultiblock().getCurrentRedstoneLevel();
    }

    //Methods relating to IComputerTile
    @Override
    public boolean exposesMultiblockToComputer() {
        return false;
    }

    @ComputerMethod
    CUReactorPortMode getMode() {
        return getBlockState().getValue(AttributeStateCUReactorPortMode.modeProperty);
    }

    @ComputerMethod
    void setMode(CUReactorPortMode mode) {
        if (mode != getMode()) {
            level.setBlockAndUpdate(worldPosition, getBlockState().setValue(AttributeStateCUReactorPortMode.modeProperty, mode));
        }
    }

    @ComputerMethod
    void incrementMode() {
        setMode(getMode().getNext());
    }

    @ComputerMethod
    void decrementMode() {
        setMode(getMode().getPrevious());
    }
    //End methods IComputerTile
}
