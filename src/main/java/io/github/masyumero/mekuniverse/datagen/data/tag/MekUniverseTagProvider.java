package io.github.masyumero.mekuniverse.datagen.data.tag;

import io.github.masyumero.mekuniverse.MekanismUniverse;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseBlocks;
import mekanism.api.providers.IBlockProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MekUniverseTagProvider extends BaseTagProvider {

    public MekUniverseTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MekanismUniverse.MODID, existingFileHelper);
    }

    @Override
    protected List<IBlockProvider> getAllBlocks() {
        return MekUniverseBlocks.BLOCKS.getAllBlocks();
    }

    @Override
    protected void registerTags(HolderLookup.Provider registries) {
        getAllBlocks().forEach(block -> addToTag(Tags.Blocks.ENDERMAN_PLACE_ON_BLACKLIST, block));
        getAllBlocks().forEach(block -> addToHarvestTag(BlockTags.MINEABLE_WITH_PICKAXE, block));
    }
}
