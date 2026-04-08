package io.github.masyumero.mekuniverse.client;

import io.github.masyumero.mekuniverse.MekanismUniverse;
import io.github.masyumero.mekuniverse.client.gui.GuiContainedUniverseReactorController;
import io.github.masyumero.mekuniverse.client.gui.GuiContainedUniverseReactorFuel;
import io.github.masyumero.mekuniverse.client.gui.GuiContainedUniverseReactorOutput;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseContainerTypes;
import mekanism.client.ClientRegistrationUtil;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = MekanismUniverse.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistration {

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerContainers(RegisterEvent event) {
        event.register(Registries.MENU, helper -> {
            ClientRegistrationUtil.registerScreen(MekUniverseContainerTypes.CONTAINED_UNIVERSE_REACTOR_CONTROLLER, GuiContainedUniverseReactorController::new);
            ClientRegistrationUtil.registerScreen(MekUniverseContainerTypes.CONTAINED_UNIVERSE_REACTOR_OUTPUT, GuiContainedUniverseReactorOutput::new);
            ClientRegistrationUtil.registerScreen(MekUniverseContainerTypes.CONTAINED_UNIVERSE_REACTOR_FUEL, GuiContainedUniverseReactorFuel::new);
        });
    }
}
