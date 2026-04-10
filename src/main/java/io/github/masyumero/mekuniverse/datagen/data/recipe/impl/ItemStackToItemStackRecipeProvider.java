package io.github.masyumero.mekuniverse.datagen.data.recipe.impl;

import io.github.masyumero.mekuniverse.MekanismUniverse;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseItems;
import io.github.masyumero.mekuniverse.datagen.data.recipe.ISubRecipeProvider;
import mekanism.api.datagen.recipe.builder.ItemStackToItemStackRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class ItemStackToItemStackRecipeProvider implements ISubRecipeProvider {

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        String basePath = "enriching/";
        ItemStackToItemStackRecipeBuilder.enriching(
                IngredientCreatorAccess.item().from(MekUniverseItems.SMALL_SOLAR_POWER_SATELLITE, 64),
                MekUniverseItems.SOLAR_POWER_SATELLITE_CONSTELLATION.getItemStack()
        ).build(consumer, MekanismUniverse.rl(basePath + "solar_power_satellite_constellation"));
    }
}
