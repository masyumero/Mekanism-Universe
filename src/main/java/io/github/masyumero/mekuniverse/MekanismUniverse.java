package io.github.masyumero.mekuniverse;

import com.mojang.logging.LogUtils;
import io.github.masyumero.mekuniverse.common.command.MekUniverseBuilders;
import io.github.masyumero.mekuniverse.common.content.universe_reactor.ContainedUniverseReactorCache;
import io.github.masyumero.mekuniverse.common.content.universe_reactor.ContainedUniverseReactorMultiblockData;
import io.github.masyumero.mekuniverse.common.content.universe_reactor.ContainedUniverseReactorValidator;
import io.github.masyumero.mekuniverse.common.registry.*;
import mekanism.common.command.CommandMek;
import mekanism.common.command.builders.BuildCommand;
import mekanism.common.lib.multiblock.MultiblockCache;
import mekanism.common.lib.multiblock.MultiblockManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MekanismUniverse.MODID)
public class MekanismUniverse {

    public static final String MODID = "mekuniverse";
    private static final Logger LOGGER = LogUtils.getLogger();

    @SuppressWarnings("removal")
    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
    }

    @SuppressWarnings("removal")
    public MekanismUniverse() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MekUniverseItems.register(modEventBus);
        MekUniverseBlocks.register(modEventBus);
        MekUniversalTileEntityTypes.register(modEventBus);
        MekUniverseContainerTypes.register(modEventBus);

        MekUniverseTab.register(modEventBus);

        MinecraftForge.EVENT_BUS.addListener(this::registerCommands);
    }

    public static final MultiblockManager<ContainedUniverseReactorMultiblockData> containedUniverseReactorManager = new MultiblockManager<>("ContainedUniverseReactor", ContainedUniverseReactorCache::new, ContainedUniverseReactorValidator::new);

    private void registerCommands(RegisterCommandsEvent event) {
        BuildCommand.register("cu_reactor", MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR, new MekUniverseBuilders.ContainedUniverseReactorBuilder());
        event.getDispatcher().register(CommandMek.register());
    }
}
