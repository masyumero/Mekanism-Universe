package io.github.masyumero.mekuniverse.client.gui.element;

import io.github.masyumero.mekuniverse.MekanismUniverseLang;
import io.github.masyumero.mekuniverse.common.network.to_server.MekUniversePacketGeneratorGuiButtonPress;
import io.github.masyumero.mekuniverse.common.network.to_server.MekUniversePacketGeneratorGuiButtonPress.MekUniverseClickedGeneratorsTileButton;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorController;
import mekanism.api.text.ILangEntry;
import mekanism.client.gui.IGuiWrapper;
import mekanism.client.gui.element.tab.GuiTabElementType;
import mekanism.client.gui.element.tab.TabType;
import mekanism.client.render.lib.ColorAtlas;
import mekanism.common.util.MekanismUtils;
import mekanism.generators.client.GeneratorsSpecialColors;
import mekanism.generators.common.GeneratorsLang;
import mekanism.generators.common.MekanismGenerators;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class GuiContainedUniverseReactorTab extends GuiTabElementType<TileEntityContainedUniverseReactorController, GuiContainedUniverseReactorTab.ContainedUniverseReactorTab> {

    public GuiContainedUniverseReactorTab(IGuiWrapper gui, TileEntityContainedUniverseReactorController tile, ContainedUniverseReactorTab type) {
        super(gui, tile, type);
    }

    public enum ContainedUniverseReactorTab implements TabType<TileEntityContainedUniverseReactorController> {
        OUTPUT(MekanismUtils.getResource(MekanismUtils.ResourceType.GUI, "stats.png"), MekanismUniverseLang.OUTPUT_TAB, 6, MekUniverseClickedGeneratorsTileButton.TAB_OUTPUT, GeneratorsSpecialColors.TAB_MULTIBLOCK_HEAT),
        FUEL(MekanismGenerators.rl(MekanismUtils.ResourceType.GUI.getPrefix() + "fuel.png"), GeneratorsLang.FUEL_TAB, 34, MekUniverseClickedGeneratorsTileButton.TAB_FUEL, GeneratorsSpecialColors.TAB_MULTIBLOCK_FUEL);

        private final MekUniverseClickedGeneratorsTileButton button;
        private final ColorAtlas.ColorRegistryObject colorRO;
        private final ILangEntry description;
        private final ResourceLocation path;
        private final int yPos;

        ContainedUniverseReactorTab(ResourceLocation path, ILangEntry description, int y, MekUniverseClickedGeneratorsTileButton button, ColorAtlas.ColorRegistryObject colorRO) {
            this.path = path;
            this.description = description;
            this.yPos = y;
            this.button = button;
            this.colorRO = colorRO;
        }

        @Override
        public ResourceLocation getResource() {
            return path;
        }

        @Override
        public void onClick(TileEntityContainedUniverseReactorController tile) {
            MekanismGenerators.packetHandler().sendToServer(new MekUniversePacketGeneratorGuiButtonPress(button, tile.getBlockPos()));
        }

        @Override
        public Component getDescription() {
            return description.translate();
        }

        @Override
        public int getYPos() {
            return yPos;
        }

        @Override
        public ColorAtlas.ColorRegistryObject getTabColor() {
            return colorRO;
        }
    }
}
