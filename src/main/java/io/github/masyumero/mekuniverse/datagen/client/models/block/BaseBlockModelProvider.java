package io.github.masyumero.mekuniverse.datagen.client.models.block;

import io.github.masyumero.mekuniverse.common.block.attribute.AttributeStateCUReactorPortMode;
import io.github.masyumero.mekuniverse.common.block.attribute.AttributeStateCUReactorPortMode.CUReactorPortMode;
import lombok.NonNull;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class BaseBlockModelProvider extends BlockStateProvider {

    public BaseBlockModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    public void activeBlock(BlockRegistryObject<?, ?> blockRO, ModelFile activeBlockModel, ModelFile blockModel) {
        ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
        VariantBlockStateBuilder variantBuilder = getVariantBuilder(blockRO.getBlock());

        variantBuilder.forAllStatesExcept(state -> {
            if (Attribute.isActive(state)) {
                return builder
                        .modelFile(activeBlockModel).build();
            } else {
                return builder
                        .modelFile(blockModel).build();
            }
        }, BlockStateHelper.FLUID_LOGGED);
    }

    public void portBlock(BlockRegistryObject<?, ?> blockRO, @NonNull ModelFile... portModel) {
        if (portModel.length > 3) {
            return;
        }
        ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
        VariantBlockStateBuilder variantBuilder = getVariantBuilder(blockRO.getBlock());

        variantBuilder.forAllStatesExcept(state -> {
            CUReactorPortMode mode = state.getValue(AttributeStateCUReactorPortMode.modeProperty);
            if (mode == CUReactorPortMode.INPUT) {
                return builder.modelFile(portModel[0]).build();
            } else if (mode == CUReactorPortMode.OUTPUT_ENERGY) {
                return builder.modelFile(portModel[1]).build();
            } else { // mode == CUReactorPortMode.OUTPUT_HELIUM
                return builder.modelFile(portModel[2]).build();
            }
        }, BlockStateHelper.FLUID_LOGGED);
    }
}
