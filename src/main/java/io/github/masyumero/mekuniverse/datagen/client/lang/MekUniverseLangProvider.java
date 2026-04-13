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
        addENJP(MekUniverseItems.GEM_ENERGY, "Energy Gem", "エネルギーのジェム");
        addENJP(MekUniverseItems.CLUSTER_ENERGY, "Energy Cluster", "エネルギーのクラスター");
        addENJP(MekUniverseItems.MASS_ENERGY, "Energy Mass", "エネルギーの塊");
        addENJP(MekUniverseItems.CONDENSE_ENERGY, "Energy Condense", "濃縮されたエネルギー");
        addENJP(MekUniverseItems.CORE_ENERGY, "Energy Core", "エネルギーのコア");
        addENJP(MekUniverseItems.DENSE_CORE_ENERGY, "Energy Dense Core", "エネルギーの高密コア");
        addENJP(MekUniverseItems.COMPACT_CORE_ENERGY, "Energy Compact Core", "エネルギーのコンパクトコア");
        addENJP(MekUniverseItems.SINGULARITY_ENERGY, "Energy Singularity", "エネルギーのシンギュラリティ");
    }

    private void addBlock() {
        addENJP(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING, "宇宙封入炉ケーシング");
        addENJP(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CONTROLLER, "宇宙封入炉制御装置");
        addENJP(MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_PORT, "宇宙封入炉ポート");
    }

    private void addMisc() {
        add(MekanismUniverseLang.MAIN_TAB, "Mekanism:Universe");
        addENJP(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR, "Contained Universe Reactor", "宇宙封入炉");

        addENJP(MekanismUniverseLang.OUTPUT_TAB, "Output", "出力");
        addENJP(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_STATS, "Field Active: %1$s", "封入状態: %1$s");
        addENJP(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_TIER, "Tier: %1$s", "恒星段階: %1$s");
        addENJP(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_SATELLITE, "Satellite: %1$s", "衛星群: %1$s個");
        addENJP(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_PASSIVE_RATE, "Passive Generation: %1$s/t", "発電量: %1$s/t");
        addENJP(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_HELIUM_PRODUCTION, "Helium Production: %1$s mB/t", "ヘリウムの生成量: %1$s mB/t");
        addENJP(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_REQUEST_HYDROGEN, "Request Hydrogen: %1$s mb/t", "水素要求量: %1$s mb/t");
        addENJP(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_REQUEST_HELIUM, "Request Helium: %1$s mb/t", "ヘリウム要求量: %1$s mb/t");
        addENJP(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_PORT_MODE_CHANGE, "Port mode changed to: %1$s", "バルブモードを%1$sに変更しました");
        addENJP(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_PORT_MODE_INPUT, "input only", "入力のみ");
        addENJP(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_PORT_MODE_OUTPUT_HELIUM, "output helium", "ヘリウム出力");
        addENJP(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_PORT_MODE_OUTPUT_ENERGY, "output energy", "電力出力");
        addENJP(MekanismUniverseLang.DESCRIPTION_CONTAINED_UNIVERSE_REACTOR_CONTROLLER, "The controlling block for the entire Contained Universe Reactor structure.", "宇宙封入炉全体を制御するためのコントローラ。");
        addENJP(MekanismUniverseLang.DESCRIPTION_CONTAINED_UNIVERSE_REACTOR_CASING, "Reinforced framing that can be used in the Contained Universe Reactor multiblock.", "宇宙封入炉のマルチブロックを構成する強化された筐体です。");
        addENJP(MekanismUniverseLang.DESCRIPTION_CONTAINED_UNIVERSE_REACTOR_PORT, "A block of reinforced framing that is capable of managing both the gas and energy transfer of the Contained Universe Reactor.", "宇宙封入炉筐体の一つで、電力の出力燃料などの搬入を担います。");

        addENJP(MekanismUniverseLang.FELL_VOID, "Fell into the void", "虚空に落ちた");
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
