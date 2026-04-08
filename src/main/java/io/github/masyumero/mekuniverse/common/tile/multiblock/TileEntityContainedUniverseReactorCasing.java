package io.github.masyumero.mekuniverse.common.tile.multiblock;

import io.github.masyumero.mekuniverse.MekanismUniverse;
import io.github.masyumero.mekuniverse.common.content.universe_reactor.ContainedUniverseReactorMultiblockData;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseBlocks;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseContainerTypes;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.inventory.container.sync.dynamic.SyncMapper;
import mekanism.common.lib.multiblock.MultiblockManager;
import mekanism.common.tile.prefab.TileEntityMultiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityContainedUniverseReactorCasing extends TileEntityMultiblock<ContainedUniverseReactorMultiblockData> {

    public TileEntityContainedUniverseReactorCasing(BlockPos pos, BlockState state) {
        this(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING, pos, state);
    }

    public TileEntityContainedUniverseReactorCasing(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Override
    public ContainedUniverseReactorMultiblockData createMultiblock() {
        return new ContainedUniverseReactorMultiblockData(this);
    }

    @Override
    public MultiblockManager<ContainedUniverseReactorMultiblockData> getManager() {
        return MekanismUniverse.containedUniverseReactorManager;
    }

    @Override
    public boolean canBeMaster() {
        return false;
    }

    @Override
    public void addContainerTrackers(MekanismContainer container) {
        super.addContainerTrackers(container);
        if (container.getType() == MekUniverseContainerTypes.CONTAINED_UNIVERSE_REACTOR_OUTPUT.get()) {
            addTabContainerTracker(container, ContainedUniverseReactorMultiblockData.OUTPUT_TAB);
        } else if (container.getType() == MekUniverseContainerTypes.CONTAINED_UNIVERSE_REACTOR_FUEL.get()) {
            addTabContainerTracker(container, ContainedUniverseReactorMultiblockData.FUEL_TAB);
        }
    }

    private void addTabContainerTracker(MekanismContainer container, String tab) {
        SyncMapper.INSTANCE.setup(container, ContainedUniverseReactorMultiblockData.class, this::getMultiblock, tab);
    }
}
