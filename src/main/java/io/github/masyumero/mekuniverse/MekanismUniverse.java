package io.github.masyumero.mekuniverse;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MekanismUniverse.MODID)
public class MekanismUniverse {

    public static final String MODID = "mekuniverse";
    private static final Logger LOGGER = LogUtils.getLogger();

    @SuppressWarnings("removal")
    public ExampleMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }
}
