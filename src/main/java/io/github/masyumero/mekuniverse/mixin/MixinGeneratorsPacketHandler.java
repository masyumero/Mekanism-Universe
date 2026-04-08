package io.github.masyumero.mekuniverse.mixin;

import io.github.masyumero.mekuniverse.common.network.to_server.MekUniversePacketGeneratorGuiButtonPress;
import mekanism.common.network.BasePacketHandler;
import mekanism.generators.common.network.GeneratorsPacketHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GeneratorsPacketHandler.class, remap = false)
public abstract class MixinGeneratorsPacketHandler extends BasePacketHandler {

    @Inject(method = "initialize", at = @At("HEAD"))
    private void initialize(CallbackInfo ci) {
        registerClientToServer(MekUniversePacketGeneratorGuiButtonPress.class, MekUniversePacketGeneratorGuiButtonPress::decode);
    }
}
