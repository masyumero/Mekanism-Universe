package io.github.masyumero.mekuniverse.datagen.data.registries;

import io.github.masyumero.mekuniverse.MekanismUniverse;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public class MekUniverseDatapackRegistryProvider extends BaseDatapackRegistryProvider {

    public MekUniverseDatapackRegistryProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, MekanismUniverse.MODID);
    }

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder();
}
