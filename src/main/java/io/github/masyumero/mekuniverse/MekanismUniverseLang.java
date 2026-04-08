package io.github.masyumero.mekuniverse;

import com.mojang.blaze3d.MethodsReturnNonnullByDefault;
import lombok.Getter;
import mekanism.api.text.ILangEntry;
import net.minecraft.Util;

@Getter
@MethodsReturnNonnullByDefault
public enum MekanismUniverseLang implements ILangEntry {
    //Tab
    MAIN_TAB("tab", "main"),

    CONTAINED_UNIVERSE_REACTOR("cu_reactor", "contained_universe_reactor"),
    DESCRIPTION_CONTAINED_UNIVERSE_REACTOR_CONTROLLER("description", "contained_universe_reactor_controller"),
    DESCRIPTION_CONTAINED_UNIVERSE_REACTOR_CASING("description", "contained_universe_reactor_casing"),
    DESCRIPTION_CONTAINED_UNIVERSE_REACTOR_PORT("description", "contained_universe_reactor_port"),

    CONTAINED_UNIVERSE_REACTOR_PORT_MODE_INPUT("cu_reactor", "port_mode_input"),
    CONTAINED_UNIVERSE_REACTOR_PORT_MODE_OUTPUT_ENERGY("cu_reactor", "port_mode_output_energy"),
    CONTAINED_UNIVERSE_REACTOR_PORT_MODE_OUTPUT_HELIUM("cu_reactor", "port_mode_output_helium"),
    CONTAINED_UNIVERSE_REACTOR_PORT__MODE_CHANGE("cu_reactor", "port_mode_change"),
    OUTPUT_TAB("cu_reactor", "output"),
    FALL_VOID("cu_reactor", "void");

    private final String translationKey;

    MekanismUniverseLang(String type, String path) {
        this(Util.makeDescriptionId(type, MekanismUniverse.rl(path)));
    }

    MekanismUniverseLang(String key) {
        this.translationKey = key;
    }
}
