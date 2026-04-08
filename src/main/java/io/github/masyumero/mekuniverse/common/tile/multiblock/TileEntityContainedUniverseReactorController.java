package io.github.masyumero.mekuniverse.common.tile.multiblock;

import io.github.masyumero.mekuniverse.common.content.universe_reactor.ContainedUniverseReactorMultiblockData;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseBlocks;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.integration.energy.EnergyCompatUtils;
import mekanism.common.tile.base.SubstanceType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class TileEntityContainedUniverseReactorController extends TileEntityContainedUniverseReactorCasing {

    public TileEntityContainedUniverseReactorController(BlockPos pos, BlockState state) {
        super(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CONTROLLER, pos, state);
        //Never allow the gas handler, fluid handler, or energy cap to be enabled here even though internally we can handle both of them
        addDisabledCapabilities(Capabilities.GAS_HANDLER);
        addDisabledCapabilities(EnergyCompatUtils.getEnabledEnergyCapabilities());
        addSemiDisabledCapability(ForgeCapabilities.ITEM_HANDLER, () -> !getMultiblock().isFormed());
        delaySupplier = NO_DELAY;
    }

    @Override
    protected boolean onUpdateServer(ContainedUniverseReactorMultiblockData multiblock) {
        boolean needsPacket = super.onUpdateServer(multiblock);
        setActive(multiblock.isFormed() && multiblock.isFieldActive() && multiblock.isActive());
        return needsPacket;
    }

    @Override
    protected boolean canPlaySound() {
        ContainedUniverseReactorMultiblockData multiblock = getMultiblock();
        return multiblock.isFormed() && multiblock.isFieldActive();
    }

    @Override
    public boolean canBeMaster() {
        return true;
    }

    @Override
    public boolean handles(SubstanceType type) {
        if (type == SubstanceType.GAS) {
            return false;
        }
        return super.handles(type);
    }
}
