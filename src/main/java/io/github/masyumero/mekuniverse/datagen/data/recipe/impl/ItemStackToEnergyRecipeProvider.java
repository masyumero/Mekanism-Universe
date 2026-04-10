package io.github.masyumero.mekuniverse.datagen.data.recipe.impl;

import io.github.masyumero.mekuniverse.MekanismUniverse;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseItems;
import io.github.masyumero.mekuniverse.datagen.data.recipe.ISubRecipeProvider;
import mekanism.api.datagen.recipe.builder.ItemStackToEnergyRecipeBuilder;
import mekanism.api.math.FloatingLong;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class ItemStackToEnergyRecipeProvider implements ISubRecipeProvider {

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        crystalToEnergy(MekUniverseItems.GEM_ENERGY, 2, consumer);
        crystalToEnergy(MekUniverseItems.CLUSTER_ENERGY, 3, consumer);
        crystalToEnergy(MekUniverseItems.MASS_ENERGY, 4, consumer);
        crystalToEnergy(MekUniverseItems.CONDENSE_ENERGY, 5, consumer);
        crystalToEnergy(MekUniverseItems.CORE_ENERGY, 6, consumer);
        crystalToEnergy(MekUniverseItems.DENSE_CORE_ENERGY, 7, consumer);
        crystalToEnergy(MekUniverseItems.COMPACT_CORE_ENERGY, 8, consumer);
        crystalToEnergy(MekUniverseItems.SINGULARITY_ENERGY, 9, consumer);

    }

    private void crystalToEnergy(ItemRegistryObject<?> itemRO, long energyExponent, Consumer<FinishedRecipe> consumer) {
        String basePath = "energy_conversion/";
        long baseEnergy = 600_000L;
        ItemStackToEnergyRecipeBuilder.energyConversion(IngredientCreatorAccess.item().from(itemRO), FloatingLong.create(baseEnergy * Math.pow(9, energyExponent))).build(consumer, MekanismUniverse.rl(basePath + itemRO.getName()));
    }
}
