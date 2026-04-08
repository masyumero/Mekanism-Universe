package io.github.masyumero.mekuniverse.datagen.client.lang;

import mekanism.api.gear.ModuleData;
import mekanism.api.providers.IBlockProvider;
import mekanism.api.providers.IModuleDataProvider;
import mekanism.api.text.IHasTranslationKey;
import mekanism.common.Mekanism;
import mekanism.common.advancements.MekanismAdvancement;
import mekanism.common.base.IModModule;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeGui;
import mekanism.common.registration.impl.FluidRegistryObject;
import mekanism.common.util.RegistryUtils;
import net.minecraft.Util;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

public abstract class BaseLanguageProvider extends LanguageProvider {

    protected final String modName;
    protected final String basicModName;
    private final String modid;

    protected BaseLanguageProvider(PackOutput output, String modid) {
        this(output, modid, Mekanism.MOD_NAME, "en_us");
    }

    protected BaseLanguageProvider(PackOutput output, String modid, String locale) {
        this(output, modid, Mekanism.MOD_NAME, locale);
    }

    protected BaseLanguageProvider(PackOutput output, String modid, IModModule module) {
        this(output, modid, Mekanism.MOD_NAME + ": " + module.getName(), "en_us");
    }

    private BaseLanguageProvider(PackOutput output, String modid, String modName, String locale) {
        super(output, modid, locale);
        this.modid = modid;
        this.modName = modName;
        this.basicModName = modName.replaceAll(":", "");
    }

    @NotNull
    @Override
    public String getName() {
        return super.getName() + ": " + modid;
    }

    protected void addPackData(IHasTranslationKey name, IHasTranslationKey packDescription) {
        add(name, modName);
        add(packDescription, "Resources used for " + modName);
    }

    protected void add(IHasTranslationKey key, String value) {
        if (key instanceof IBlockProvider blockProvider) {
            Block block = blockProvider.getBlock();
            if (Attribute.matches(block, AttributeGui.class, attribute -> !attribute.hasCustomName())) {
                add(Util.makeDescriptionId("container", RegistryUtils.getName(block)), value);
            }
        }
        add(key.getTranslationKey(), value);
    }

    protected void add(IBlockProvider blockProvider, String value, String containerName) {
        Block block = blockProvider.getBlock();
        if (Attribute.matches(block, AttributeGui.class, attribute -> !attribute.hasCustomName())) {
            add(Util.makeDescriptionId("container", RegistryUtils.getName(block)), containerName);
            add(blockProvider.getTranslationKey(), value);
        } else {
            throw new IllegalArgumentException("Block " + blockProvider.getRegistryName() + " does not have a container name set.");
        }
    }

    protected void add(IModuleDataProvider<?> moduleDataProvider, String name, String description) {
        ModuleData<?> moduleData = moduleDataProvider.getModuleData();
        add(moduleData.getTranslationKey(), name);
        add(moduleData.getDescriptionTranslationKey(), description);
    }

    protected void addFluid(FluidRegistryObject<?, ?, ?, ?, ?> fluidRO, String name) {
        add(fluidRO.getBlock(), name);
        add(fluidRO.getBucket(), name + " Bucket");
    }

    protected void add(MekanismAdvancement advancement, String title, String description) {
        add(advancement.title(), title);
        add(advancement.description(), description);
    }

    @Override
    public void add(@NotNull String key, @NotNull String value) {
        if (value.contains("%s")) {
            throw new IllegalArgumentException("Values containing substitutions should use explicit numbered indices: " + key + " - " + value);
        }
        super.add(key, value);
    }
}
