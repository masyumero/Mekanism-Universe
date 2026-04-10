package io.github.masyumero.mekuniverse.datagen.data.loot;

import io.github.masyumero.mekuniverse.datagen.data.loot.table.MekUniverseBlockLootProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;

public class MekUniverseLootProvider extends BaseLootProvider {

    public MekUniverseLootProvider(PackOutput output) {
        super(output, List.of(
                new SubProviderEntry(MekUniverseBlockLootProvider::new, LootContextParamSets.BLOCK)
        ));
    }
}
