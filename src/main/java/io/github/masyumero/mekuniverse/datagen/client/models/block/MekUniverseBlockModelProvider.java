package io.github.masyumero.mekuniverse.datagen.client.models.block;

import io.github.masyumero.mekuniverse.MekanismUniverse;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseBlocks;
import mekanism.common.Mekanism;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MekUniverseBlockModelProvider extends BaseBlockModelProvider{

    public MekUniverseBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MekanismUniverse.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ResourceLocation casingTexture = MekanismUniverse.rl("block/cu_reactor_casing");
        String basePath = "block/cu_reactor/";
                
        //Casing
        ModelFile casingModel = models().cubeAll(basePath + "casing", casingTexture);

        simpleBlock(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING.getBlock(), casingModel);

        //controller
        ModelFile controllerModel = models().getBuilder(basePath + "controller")
                .parent(new ModelFile.UncheckedModelFile(Mekanism.rl("block/machine")))
                .texture("sides", casingTexture)
                .texture("front", casingTexture)
                .texture("up", MekanismUniverse.rl("block/cu_reactor_controller"));

        ModelFile controllerActiveModel = models().getBuilder(basePath + "controller_on")
                .parent(controllerModel)
                .texture("up", MekanismUniverse.rl("block/cu_reactor_controller_on"));

        activeBlock(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CONTROLLER, controllerActiveModel, controllerModel);

        //Port
        ModelFile portInputModel = models().cubeAll(basePath + "port_input",
                MekanismUniverse.rl("block/cu_reactor_port_input"));
        ModelFile portOutputEnergyModel = models().cubeAll(basePath + "port_output_energy",
                MekanismUniverse.rl("block/cu_reactor_port_output_energy"));
        ModelFile portOutputHeliumModel = models().cubeAll(basePath + "port_output_helium",
                MekanismUniverse.rl("block/cu_reactor_port_output_helium"));

        portBlock(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_PORT, portInputModel, portOutputEnergyModel, portOutputHeliumModel);
    }
}
