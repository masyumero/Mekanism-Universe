package io.github.masyumero.mekuniverse.datagen.data.loot.table;

import io.github.masyumero.mekuniverse.common.registry.MekUniverseBlocks;

public class MekUniverseBlockLootProvider extends BaseBlockLootTables {

    @Override
    protected void generate() {
        dropSelfWithContents(MekUniverseBlocks.BLOCKS.getAllBlocks());
    }
}
