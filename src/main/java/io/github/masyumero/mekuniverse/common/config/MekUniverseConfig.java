package io.github.masyumero.mekuniverse.common.config;

import lombok.Getter;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedBooleanValue;
import mekanism.common.config.value.CachedFloatingLongValue;
import mekanism.common.config.value.CachedIntValue;
import mekanism.common.config.value.CachedLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class MekUniverseConfig extends BaseMekanismConfig {

    @Getter
    private final ForgeConfigSpec configSpec;
    public final CachedFloatingLongValue baseUseEnergy;
    public final CachedLongValue baseHeliumGeneration;
    public final CachedLongValue useAntimatterAmount;
    public final CachedLongValue heliumOutputTankCapacity;
    public final CachedIntValue tierLimit;
    public final CachedBooleanValue voidFall;

    MekUniverseConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Mekanism Universe Config. This config is synced from server to client.");
        builder.push("Contained Universe Reactor");
        baseUseEnergy = CachedFloatingLongValue.define(this, builder, "The energy consumed by the machine per tick (Fe).", "baseUseEnergy", FloatingLong.create(1_073_741_824L));
        baseHeliumGeneration = CachedLongValue.wrap(this, builder.comment("Base amount of Helium produced. Actual production is this number * Tier (mB)").defineInRange("baseHeliumGeneration", 4_000L, 1L, 354_745_078_340_568_384L));
        useAntimatterAmount = CachedLongValue.wrap(this, builder.comment("The amount of antimatter used to start the machine (mB).").define("useAntimatterAmount", 100_000L));
        heliumOutputTankCapacity = CachedLongValue.wrap(this, builder.comment("Storage capacity of the machine's helium output tank (mB).").define("heliumOutputTankCapacity", Long.MAX_VALUE));
        tierLimit = CachedIntValue.wrap(this, builder.comment("Not recommended!!! Changes to tier limits. Setting them too high may cause issues such as overflow. Not recommended!!!").define("tierLimit", 26));
        voidFall = CachedBooleanValue.wrap(this, builder.comment("Behavior when entering the machine.").define("voidFall", true));
        this.configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "universe";
    }

    @Override
    public ModConfig.Type getConfigType() {
        return ModConfig.Type.COMMON;
    }
}
