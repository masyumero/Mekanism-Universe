package io.github.masyumero.mekuniverse.datagen.client.lang;

import io.github.masyumero.mekuniverse.MekanismUniverse;
import net.minecraft.data.PackOutput;

import static io.github.masyumero.mekuniverse.datagen.client.lang.MekUniverseLangProvider.LANGS;

public class MekUniverseJapaneseLangProvider extends BaseLanguageProvider{

    public MekUniverseJapaneseLangProvider(PackOutput output) {
        super(output, MekanismUniverse.MODID, "ja_jp");
    }

    @Override
    protected void addTranslations() {
        LANGS.forEach((key, enjp) -> add(key, enjp.jp()));
    }
}
