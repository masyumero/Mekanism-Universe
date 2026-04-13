package io.github.masyumero.mekuniverse.common.config;

import mekanism.common.config.MekanismConfigHelper;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;

public class MekUniverseLoadConfig {

    public static final MekUniverseConfig config = new MekUniverseConfig();

    @SuppressWarnings("removal")
    public static void registerConfigs(ModLoadingContext context) {
        ModContainer container = context.getActiveContainer();
        MekanismConfigHelper.registerConfig(container, config);
    }
}
