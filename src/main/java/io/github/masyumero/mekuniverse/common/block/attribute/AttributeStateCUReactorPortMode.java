package io.github.masyumero.mekuniverse.common.block.attribute;

import io.github.masyumero.mekuniverse.MekanismUniverseLang;
import mekanism.api.IIncrementalEnum;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.math.MathUtils;
import mekanism.api.text.EnumColor;
import mekanism.api.text.IHasTextComponent;
import mekanism.api.text.ILangEntry;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeState;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AttributeStateCUReactorPortMode implements AttributeState {

    public static final EnumProperty<CUReactorPortMode> modeProperty = EnumProperty.create("mode", CUReactorPortMode.class);

    @Override
    public BlockState copyStateData(BlockState oldState, BlockState newState) {
        if (Attribute.has(newState, AttributeStateCUReactorPortMode.class)) {
            newState = newState.setValue(modeProperty, oldState.getValue(modeProperty));
        }
        return newState;
    }

    @Override
    public BlockState getDefaultState(@NotNull BlockState state) {
        return state.setValue(modeProperty, CUReactorPortMode.INPUT);
    }

    @Override
    public void fillBlockStateContainer(Block block, List<Property<?>> properties) {
        properties.add(modeProperty);
    }

    @NothingNullByDefault
    public enum CUReactorPortMode implements StringRepresentable, IHasTextComponent, IIncrementalEnum<CUReactorPortMode> {
        INPUT("input", MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_PORT_MODE_INPUT, EnumColor.BRIGHT_GREEN),
        OUTPUT_ENERGY("output_energy", MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_PORT_MODE_OUTPUT_ENERGY, EnumColor.RED),
        OUTPUT_HELIUM("output_helium", MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_PORT_MODE_OUTPUT_HELIUM, EnumColor.DARK_RED);

        private static final CUReactorPortMode[] MODES = values();

        private final String name;
        private final ILangEntry langEntry;
        private final EnumColor color;

        CUReactorPortMode(String name, ILangEntry langEntry, EnumColor color) {
            this.name = name;
            this.langEntry = langEntry;
            this.color = color;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        @Override
        public Component getTextComponent() {
            return langEntry.translateColored(color);
        }

        public static CUReactorPortMode byIndexStatic(int index) {
            return MathUtils.getByIndexMod(MODES, index);
        }

        @Override
        public CUReactorPortMode byIndex(int index) {
            return byIndexStatic(index);
        }
    }
}
