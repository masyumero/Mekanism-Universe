package io.github.masyumero.mekuniverse.datagen.data.recipe.impl;

import com.fxd927.mekanismmoremachine.common.registries.MMItems;
import io.github.masyumero.mekuniverse.MekanismUniverse;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseItems;
import io.github.masyumero.mekuniverse.datagen.data.recipe.BaseRecipeProvider;
import io.github.masyumero.mekuniverse.datagen.data.recipe.ISubRecipeProvider;
import io.github.masyumero.mekuniverse.datagen.data.recipe.builder.ExtendedShapedRecipeBuilder;
import io.github.masyumero.mekuniverse.datagen.data.recipe.pattern.Pattern;
import io.github.masyumero.mekuniverse.datagen.data.recipe.pattern.RecipePattern;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.registries.MekanismItems;
import mekanism.generators.common.registries.GeneratorsItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.function.Consumer;

@NothingNullByDefault
public class MekUniverseRecipeProvider extends BaseRecipeProvider {

    public static final char TANK = 'T';
    public static final char CASING = 'F';

    private final RecipePattern energyPattern = RecipePattern.createPattern(
            RecipePattern.TripleLine.of(Pattern.CONSTANT, Pattern.CONSTANT, Pattern.CONSTANT),
            RecipePattern.TripleLine.of(Pattern.CONSTANT, Pattern.CONSTANT, Pattern.CONSTANT),
            RecipePattern.TripleLine.of(Pattern.CONSTANT, Pattern.CONSTANT, Pattern.CONSTANT)
    );

    public MekUniverseRecipeProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void addRecipes(Consumer<FinishedRecipe> consumer) {
        addMisc(consumer);
    }

    @Override
    protected List<ISubRecipeProvider> getSubRecipeProviders() {
        return List.of(
                new ItemStackToItemStackRecipeProvider(),
                new ContainedUniverseReactorRecipeProvider(),
                new ItemStackToEnergyRecipeProvider()
        );
    }

    private void addMisc(Consumer<FinishedRecipe> consumer) {
        //Energy
        compressEnergy(MekUniverseItems.GEM_ENERGY, MMItems.CRYSTAL_ENERGY, consumer);
        compressEnergy(MekUniverseItems.CLUSTER_ENERGY, MekUniverseItems.GEM_ENERGY, consumer);
        compressEnergy(MekUniverseItems.MASS_ENERGY, MekUniverseItems.CLUSTER_ENERGY, consumer);
        compressEnergy(MekUniverseItems.CONDENSE_ENERGY, MekUniverseItems.MASS_ENERGY, consumer);
        compressEnergy(MekUniverseItems.CORE_ENERGY, MekUniverseItems.CONDENSE_ENERGY, consumer);
        compressEnergy(MekUniverseItems.DENSE_CORE_ENERGY, MekUniverseItems.CORE_ENERGY, consumer);
        compressEnergy(MekUniverseItems.COMPACT_CORE_ENERGY, MekUniverseItems.DENSE_CORE_ENERGY, consumer);
        compressEnergy(MekUniverseItems.SINGULARITY_ENERGY, MekUniverseItems.COMPACT_CORE_ENERGY, consumer);

        //Satellite
        ExtendedShapedRecipeBuilder.shapedRecipe(MekUniverseItems.SMALL_SOLAR_POWER_SATELLITE)
                .pattern(RecipePattern.createPattern(
                        RecipePattern.TripleLine.of(Pattern.CONSTANT, Pattern.OTHER, Pattern.CONSTANT),
                        RecipePattern.TripleLine.of(Pattern.CONSTANT, Pattern.ENERGY, Pattern.CONSTANT),
                        RecipePattern.TripleLine.of(Pattern.CONSTANT, Pattern.EMPTY, Pattern.CONSTANT)
                ))
                .key(Pattern.CONSTANT, GeneratorsItems.SOLAR_PANEL)
                .key(Pattern.OTHER, MekanismItems.ROBIT)
                .key(Pattern.ENERGY, MekanismItems.MODULE_ENERGY)
                .build(consumer, MekanismUniverse.rl("crafting/" + MekUniverseItems.SMALL_SOLAR_POWER_SATELLITE.getName()));
    }

    private void compressEnergy(ItemRegistryObject<?> toItem, ItemLike fromItem, Consumer<FinishedRecipe> consumer) {
        ExtendedShapedRecipeBuilder.shapedRecipe(toItem)
                .pattern(energyPattern)
                .key(Pattern.CONSTANT, fromItem)
                .build(consumer, MekanismUniverse.rl("crafting/" + toItem.getName()));
    }
}
