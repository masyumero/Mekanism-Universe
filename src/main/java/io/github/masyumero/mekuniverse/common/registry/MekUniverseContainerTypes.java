package io.github.masyumero.mekuniverse.common.registry;

import io.github.masyumero.mekuniverse.MekanismUniverse;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorCasing;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorController;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public interface MekUniverseContainerTypes {

    ContainerTypeDeferredRegister CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekanismUniverse.MODID);

    ContainerTypeRegistryObject<EmptyTileContainer<TileEntityContainedUniverseReactorCasing>> CONTAINED_UNIVERSE_REACTOR = CONTAINER_TYPES.registerEmpty("contained_universe_reactor", TileEntityContainedUniverseReactorCasing.class);
    ContainerTypeRegistryObject<MekanismTileContainer<TileEntityContainedUniverseReactorController>> CONTAINED_UNIVERSE_REACTOR_CONTROLLER = CONTAINER_TYPES.register(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CONTROLLER, TileEntityContainedUniverseReactorController.class);
    ContainerTypeRegistryObject<EmptyTileContainer<TileEntityContainedUniverseReactorController>> CONTAINED_UNIVERSE_REACTOR_OUTPUT = CONTAINER_TYPES.registerEmpty("contained_universe_reactor_output", TileEntityContainedUniverseReactorController.class);
    ContainerTypeRegistryObject<EmptyTileContainer<TileEntityContainedUniverseReactorController>> CONTAINED_UNIVERSE_REACTOR_FUEL = CONTAINER_TYPES.registerEmpty("contained_universe_reactor_fuel", TileEntityContainedUniverseReactorController.class);

    static void register(IEventBus eventBus) {
        CONTAINER_TYPES.register(eventBus);
    }
}
