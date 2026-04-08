package io.github.masyumero.mekuniverse.common.registry;

import io.github.masyumero.mekuniverse.MekanismUniverse;
import io.github.masyumero.mekuniverse.MekanismUniverseLang;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import mekanism.common.registration.impl.CreativeTabRegistryObject;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class MekUniverseTab {

    public static final CreativeTabDeferredRegister TAB = new CreativeTabDeferredRegister(MekanismUniverse.MODID, MekUniverseTab::addToExistingTabs);
    public static final CreativeTabRegistryObject MEKANISM_UNIVERSE_TAB = TAB.registerMain(MekanismUniverseLang.MAIN_TAB, MekUniverseItems.ENERGY_SINGULARITY, builder ->
            builder.displayItems((displayParameters, output) -> {
                CreativeTabDeferredRegister.addToDisplay(MekUniverseItems.ITEMS, output);
            })
    );

    public static void register(IEventBus eventBus) {
        TAB.register(eventBus);
    }

    private static void addToExistingTabs(BuildCreativeModeTabContentsEvent event) {
    }
}
