package io.github.masyumero.mekuniverse.datagen.client.models.item;

import io.github.masyumero.mekuniverse.MekanismUniverse;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseBlocks;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MekUniverseItemModelProvider extends BaseItemModelProvider{

    public MekUniverseItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MekanismUniverse.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        MekUniverseItems.ITEMS.getAllItems().forEach(item -> basicItem(item.asItem()));
        basicBlock(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING, MekanismUniverse.rl("block/cu_reactor/casing"));
        basicBlock(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_PORT, MekanismUniverse.rl("block/cu_reactor/port_input"));
        basicBlock(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CONTROLLER, MekanismUniverse.rl("block/cu_reactor/controller"));
    }
}
