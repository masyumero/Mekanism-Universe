package io.github.masyumero.mekuniverse.common.registry;

import io.github.masyumero.mekuniverse.MekanismUniverse;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorCasing;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorController;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorPort;
import mekanism.common.block.interfaces.IHasDescription;
import mekanism.common.block.prefab.BlockBasicMultiblock;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.function.Supplier;

public interface MekUniverseBlocks {

    BlockDeferredRegister BLOCKS = new BlockDeferredRegister(MekanismUniverse.MODID);

    BlockRegistryObject<BlockBasicMultiblock<TileEntityContainedUniverseReactorController>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityContainedUniverseReactorController>>> CONTAINED_UNIVERSE_REACTOR_CONTROLLER = registerTooltipBlock("contained_universe_reactor_controller", () -> new BlockBasicMultiblock<>(MekUniverseBlockTypes.CONTAINED_UNIVERSE_REACTOR_CONTROLLER, properties -> properties.mapColor(DyeColor.GRAY)));
    BlockRegistryObject<BlockBasicMultiblock<TileEntityContainedUniverseReactorCasing>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityContainedUniverseReactorCasing>>> CONTAINED_UNIVERSE_REACTOR_CASING = registerTooltipBlock("contained_universe_reactor_casing", () -> new BlockBasicMultiblock<>(MekUniverseBlockTypes.CONTAINED_UNIVERSE_REACTOR_CASING, properties -> properties.mapColor(DyeColor.GRAY)));
    BlockRegistryObject<BlockBasicMultiblock<TileEntityContainedUniverseReactorPort>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityContainedUniverseReactorPort>>> CONTAINED_UNIVERSE_REACTOR_PORT = registerTooltipBlock("contained_universe_reactor_port", () -> new BlockBasicMultiblock<>(MekUniverseBlockTypes.CONTAINED_UNIVERSE_REACTOR_PORT, properties -> properties.mapColor(DyeColor.GRAY)));

    private static <BLOCK extends Block & IHasDescription> BlockRegistryObject<BLOCK, ItemBlockTooltip<BLOCK>> registerTooltipBlock(String name, Supplier<BLOCK> blockCreator) {
        return BLOCKS.registerDefaultProperties(name, blockCreator, ItemBlockTooltip::new);
    }

    static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
