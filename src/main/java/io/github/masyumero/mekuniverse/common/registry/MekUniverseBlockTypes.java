package io.github.masyumero.mekuniverse.common.registry;

import io.github.masyumero.mekuniverse.MekanismUniverseLang;
import io.github.masyumero.mekuniverse.common.block.attribute.AttributeStateCUReactorPortMode;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorCasing;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorController;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorPort;
import mekanism.common.block.attribute.Attributes;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.BlockTypeTile.BlockTileBuilder;
import mekanism.generators.common.registries.GeneratorsSounds;

public interface MekUniverseBlockTypes {

    BlockTypeTile<TileEntityContainedUniverseReactorController> CONTAINED_UNIVERSE_REACTOR_CONTROLLER = BlockTileBuilder
            .createBlock(() -> MekUniversalTileEntityTypes.CONTAINED_UNIVERSE_REACTOR_CONTROLLER, MekanismUniverseLang.DESCRIPTION_CONTAINED_UNIVERSE_REACTOR_CONTROLLER)
            .withGui(() -> MekUniverseContainerTypes.CONTAINED_UNIVERSE_REACTOR_CONTROLLER, MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR)
            .withSound(GeneratorsSounds.FUSION_REACTOR)
            .with(Attributes.ACTIVE, Attributes.INVENTORY)
            .externalMultiblock()
            .build();
    BlockTypeTile<TileEntityContainedUniverseReactorPort> CONTAINED_UNIVERSE_REACTOR_PORT = BlockTileBuilder
            .createBlock(() -> MekUniversalTileEntityTypes.CONTAINED_UNIVERSE_REACTOR_PORT, MekanismUniverseLang.DESCRIPTION_CONTAINED_UNIVERSE_REACTOR_PORT)
            .with(new AttributeStateCUReactorPortMode())
            .externalMultiblock()
            .withComputerSupport("containedUniverseReactorPort")
            .build();
    BlockTypeTile<TileEntityContainedUniverseReactorCasing> CONTAINED_UNIVERSE_REACTOR_CASING = BlockTileBuilder
            .createBlock(() -> MekUniversalTileEntityTypes.CONTAINED_UNIVERSE_REACTOR_CASING, MekanismUniverseLang.DESCRIPTION_CONTAINED_UNIVERSE_REACTOR_CASING)
            .externalMultiblock()
            .build();
}
