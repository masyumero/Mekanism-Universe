package io.github.masyumero.mekuniverse.datagen.data.recipe.impl;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.registry.ExtraBlock;
import com.jerry.mekanism_extras.common.registry.ExtraItem;
import fr.iglee42.evolvedmekanism.EvolvedMekanism;
import fr.iglee42.evolvedmekanism.registries.EMBlocks;
import fr.iglee42.evolvedmekanism.registries.EMTags;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.registry.EMExtrasItem;
import io.github.masyumero.mekuniverse.MekanismUniverse;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseBlocks;
import io.github.masyumero.mekuniverse.datagen.data.recipe.ISubRecipeProvider;
import io.github.masyumero.mekuniverse.datagen.data.recipe.builder.ExtendedShapedRecipeBuilder;
import io.github.masyumero.mekuniverse.datagen.data.recipe.pattern.Pattern;
import io.github.masyumero.mekuniverse.datagen.data.recipe.pattern.RecipePattern;
import mekanism.api.datagen.recipe.builder.NucleosynthesizingRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.registries.MekanismGases;
import mekanism.common.registries.MekanismItems;
import mekanism.common.tags.MekanismTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.*;

import java.util.function.Consumer;

public class ContainedUniverseReactorRecipeProvider implements ISubRecipeProvider {

    private static final RecipePattern CONTROLLER_PATTERN = RecipePattern.createPattern(
            RecipePattern.TripleLine.of(Pattern.CIRCUIT, Pattern.GLASS, Pattern.CIRCUIT),
            RecipePattern.TripleLine.of(MekUniverseRecipeProvider.CASING, MekUniverseRecipeProvider.TANK, MekUniverseRecipeProvider.CASING),
            RecipePattern.TripleLine.of(MekUniverseRecipeProvider.CASING, MekUniverseRecipeProvider.CASING, MekUniverseRecipeProvider.CASING)
    );

    private static final RecipePattern PORT_PATTERN = RecipePattern.createPattern(
            RecipePattern.TripleLine.of(Pattern.EMPTY, MekUniverseRecipeProvider.CASING, Pattern.EMPTY),
            RecipePattern.TripleLine.of(MekUniverseRecipeProvider.CASING, Pattern.CIRCUIT, MekUniverseRecipeProvider.CASING),
            RecipePattern.TripleLine.of(Pattern.EMPTY, MekUniverseRecipeProvider.CASING, Pattern.EMPTY)
    );

    private static final ICondition VANILLA_CONDITION =new AndCondition(
            new NotCondition(new ModLoadedCondition(EMExtras.MODID)),
            new NotCondition(new ModLoadedCondition(EvolvedMekanism.MODID)),
            new NotCondition(new ModLoadedCondition(MekanismExtras.MODID))
    );

    private static final ICondition EXTRAS_CONDITION = new AndCondition(
            new NotCondition(new ModLoadedCondition(EMExtras.MODID)),
            new ModLoadedCondition(MekanismExtras.MODID)
    );

    private static final ICondition EVOLVED_CONDITION = new AndCondition(
            new NotCondition(new ModLoadedCondition(EMExtras.MODID)),
            new ModLoadedCondition(EvolvedMekanism.MODID)
    );

    private static final ICondition EMEXTRA_CONDITION = new ModLoadedCondition(EMExtras.MODID);

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        String basePath = "cu_reactor/";
        String vanillaPath = basePath + "vanilla/";
        String extrasPath = basePath + "extras/";
        String evolvedPath = basePath + "evolved/";
        String emextrasPath = basePath + "emextras/";
        //Controller
        ExtendedShapedRecipeBuilder.shapedRecipe(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CONTROLLER)
                .pattern(CONTROLLER_PATTERN)
                .key(Pattern.CIRCUIT, MekanismTags.Items.CIRCUITS_ULTIMATE)
                .key(Pattern.GLASS, Tags.Items.GLASS_PANES)
                .key(MekUniverseRecipeProvider.CASING, MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING)
                .key(MekUniverseRecipeProvider.TANK, MekanismBlocks.ULTIMATE_CHEMICAL_TANK)
                .addCondition(VANILLA_CONDITION)
                .build(consumer, MekanismUniverse.rl(vanillaPath + "controller"));

        ExtendedShapedRecipeBuilder.shapedRecipe(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CONTROLLER)
                .pattern(CONTROLLER_PATTERN)
                .key(Pattern.CIRCUIT, ExtraItem.INFINITE_CONTROL_CIRCUIT)
                .key(Pattern.GLASS, Tags.Items.GLASS_PANES)
                .key(MekUniverseRecipeProvider.CASING, MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING)
                .key(MekUniverseRecipeProvider.TANK, ExtraBlock.INFINITE_CHEMICAL_TANK)
                .addCondition(EXTRAS_CONDITION)
                .build(consumer, MekanismUniverse.rl(extrasPath + "controller"));

        ExtendedShapedRecipeBuilder.shapedRecipe(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CONTROLLER)
                .pattern(CONTROLLER_PATTERN)
                .key(Pattern.CIRCUIT, EMTags.Items.CIRCUITS_MULTIVERSAL)
                .key(Pattern.GLASS, Tags.Items.GLASS_PANES)
                .key(MekUniverseRecipeProvider.CASING, MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING)
                .key(MekUniverseRecipeProvider.TANK, EMBlocks.MULTIVERSAL_CHEMICAL_TANK)
                .addCondition(EVOLVED_CONDITION)
                .build(consumer, MekanismUniverse.rl(evolvedPath + "controller"));

        ExtendedShapedRecipeBuilder.shapedRecipe(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CONTROLLER)
                .pattern(CONTROLLER_PATTERN)
                .key(Pattern.CIRCUIT, EMExtrasItem.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT)
                .key(Pattern.GLASS, Tags.Items.GLASS_PANES)
                .key(MekUniverseRecipeProvider.CASING, MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING)
                .key(MekUniverseRecipeProvider.TANK, Ingredient.of(EMBlocks.MULTIVERSAL_CHEMICAL_TANK, ExtraBlock.INFINITE_CHEMICAL_TANK))
                .addCondition(EMEXTRA_CONDITION)
                .build(consumer, MekanismUniverse.rl(emextrasPath + "controller"));

        //Port
        ExtendedShapedRecipeBuilder.shapedRecipe(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_PORT)
                .pattern(PORT_PATTERN)
                .key(Pattern.CIRCUIT, MekanismTags.Items.CIRCUITS_ULTIMATE)
                .key(MekUniverseRecipeProvider.CASING, MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING)
                .addCondition(VANILLA_CONDITION)
                .build(consumer, MekanismUniverse.rl(vanillaPath + "port"));

        ExtendedShapedRecipeBuilder.shapedRecipe(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_PORT)
                .pattern(PORT_PATTERN)
                .key(Pattern.CIRCUIT, ExtraItem.INFINITE_CONTROL_CIRCUIT)
                .key(MekUniverseRecipeProvider.CASING, MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING)
                .addCondition(EXTRAS_CONDITION)
                .build(consumer, MekanismUniverse.rl(extrasPath + "port"));

        ExtendedShapedRecipeBuilder.shapedRecipe(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_PORT)
                .pattern(PORT_PATTERN)
                .key(Pattern.CIRCUIT, EMTags.Items.CIRCUITS_MULTIVERSAL)
                .key(MekUniverseRecipeProvider.CASING, MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING)
                .addCondition(EVOLVED_CONDITION)
                .build(consumer, MekanismUniverse.rl(evolvedPath + "port"));

        ExtendedShapedRecipeBuilder.shapedRecipe(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_PORT)
                .pattern(PORT_PATTERN)
                .key(Pattern.CIRCUIT, EMExtrasItem.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT)
                .key(MekUniverseRecipeProvider.CASING, MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING)
                .addCondition(EMEXTRA_CONDITION)
                .build(consumer, MekanismUniverse.rl(emextrasPath + "port"));

        //Casing
        NucleosynthesizingRecipeBuilder.nucleosynthesizing(IngredientCreatorAccess.item().from(MekanismItems.TELEPORTATION_CORE, 64),
                IngredientCreatorAccess.gas().from(MekanismGases.ANTIMATTER, 10000),
                MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING.getItemStack(),
                20000)
                .build(consumer, MekanismUniverse.rl(basePath + "port"));
    }
}
