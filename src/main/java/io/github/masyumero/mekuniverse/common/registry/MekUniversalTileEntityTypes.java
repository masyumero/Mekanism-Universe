package io.github.masyumero.mekuniverse.common.registry;

import io.github.masyumero.mekuniverse.MekanismUniverse;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorCasing;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorController;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorPort;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraftforge.eventbus.api.IEventBus;

public interface MekUniversalTileEntityTypes {

    TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismUniverse.MODID);

    TileEntityTypeRegistryObject<TileEntityContainedUniverseReactorController> CONTAINED_UNIVERSE_REACTOR_CONTROLLER = TILE_ENTITY_TYPES.register(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CONTROLLER, TileEntityContainedUniverseReactorController::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient );
    TileEntityTypeRegistryObject<TileEntityContainedUniverseReactorCasing> CONTAINED_UNIVERSE_REACTOR_CASING = TILE_ENTITY_TYPES.register(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING, TileEntityContainedUniverseReactorCasing::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient );
    TileEntityTypeRegistryObject<TileEntityContainedUniverseReactorPort> CONTAINED_UNIVERSE_REACTOR_PORT = TILE_ENTITY_TYPES.register(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_PORT, TileEntityContainedUniverseReactorPort::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient );

    static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
    }
}
