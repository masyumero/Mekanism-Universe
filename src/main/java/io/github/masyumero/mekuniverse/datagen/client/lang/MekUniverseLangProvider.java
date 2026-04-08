package io.github.masyumero.mekuniverse.datagen.client.lang;

import io.github.masyumero.mekuniverse.MekanismUniverse;
import io.github.masyumero.mekuniverse.MekanismUniverseLang;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseBlocks;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseItems;
import io.github.masyumero.mekuniverse.common.util.TextUtils;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mekanism.api.providers.IBaseProvider;
import mekanism.api.text.IHasTranslationKey;
import net.minecraft.data.PackOutput;

import java.util.Map;

public class MekUniverseLangProvider extends BaseLanguageProvider {

    public static final Map<IHasTranslationKey, ENJP> LANGS = new Object2ObjectOpenHashMap<>();

    public MekUniverseLangProvider(PackOutput output) {
        super(output, MekanismUniverse.MODID);
    }

    private static void addENJP(IHasTranslationKey key, ENJP enjp) {
        if (LANGS.containsKey(key)) throw new IllegalArgumentException("Duplicate key: " + key);
        LANGS.put(key, enjp);
    }

    private void addENJP(IHasTranslationKey key, String jp) {
        if (key instanceof IBaseProvider provider) {
            addENJP(key, new ENJP(TextUtils.toEnglishName(provider.getName()), jp));
        }
    }

    static void addENJP(IHasTranslationKey key, String en, String jp) {
        addENJP(key, new ENJP(en, jp));
    }

    @Override
    protected void addTranslations() {
        addMisc();
        addItem();
        addBlock();

        LANGS.forEach((key, enjp) -> add(key, enjp.en));
    }

    private void addItem() {
        addENJP(MekUniverseItems.SMALL_SOLAR_POWER_SATELLITE, "小型太陽光発電衛星");
        addENJP(MekUniverseItems.SOLAR_POWER_SATELLITE_CONSTELLATION, "太陽光発電衛星群");
        addENJP(MekUniverseItems.ENERGY_GEM, "エナジージェム");
        addENJP(MekUniverseItems.ENERGY_CLUSTER, "エナジークラスター");
        addENJP(MekUniverseItems.ENERGY_MASS, "エナジーマス");
        addENJP(MekUniverseItems.ENERGY_CONDENSE, "エナジーコンデンス");
        addENJP(MekUniverseItems.ENERGY_CORE, "エナジーコア");
        addENJP(MekUniverseItems.ENERGY_DENSE_CORE, "エナジー高密コア");
        addENJP(MekUniverseItems.ENERGY_COMPACT_CORE, "エナジーコンパクトコア");
        addENJP(MekUniverseItems.ENERGY_SINGULARITY, "エナジーシンギュラリティ");
    }

    private void addBlock() {
        addENJP(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING, "宇宙封入炉ケーシング");
        addENJP(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CONTROLLER, "宇宙封入炉制御装置");
        addENJP(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_PORT, "宇宙封入炉ポート");
    }

    private void addMisc() {
        add(MekanismUniverseLang.MAIN_TAB, "Mekanism:Universe");
        addENJP(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR, "Contained Universe Reactor", "宇宙封入炉");

        //Fusion Reactor
        addENJP(MekanismUniverseLang.DESCRIPTION_CONTAINED_UNIVERSE_REACTOR_CONTROLLER, "The controlling block for the entire Contained Universe Reactor structure.", "宇宙封入炉全体を制御するためのコントローラ。");
        addENJP(MekanismUniverseLang.DESCRIPTION_CONTAINED_UNIVERSE_REACTOR_CASING, "Reinforced framing that can be used in the Contained Universe Reactor multiblock.", "宇宙封入炉のマルチブロックを構成する強化された筐体です。");
        addENJP(MekanismUniverseLang.DESCRIPTION_CONTAINED_UNIVERSE_REACTOR_PORT, "A block of reinforced framing that is capable of managing both the gas and energy transfer of the Contained Universe Reactor.", "宇宙封入炉筐体の一つで、電力の出力燃料などの搬入を担います。");
    }

    public record ENJP(String en, String jp) {

        @Override
        public boolean equals(Object o) {
            if (o instanceof ENJP enjp) {
                return enjp.en.equals(en) && enjp.jp.equals(jp);
            }
            return false;
        }
    }
}
