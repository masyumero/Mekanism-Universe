package io.github.masyumero.mekuniverse.client.gui;

import io.github.masyumero.mekuniverse.client.gui.element.GuiContainedUniverseReactorTab;
import io.github.masyumero.mekuniverse.client.gui.element.GuiContainedUniverseReactorTab.ContainedUniverseReactorTab;
import io.github.masyumero.mekuniverse.common.content.universe_reactor.ContainedUniverseReactorMultiblockData;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorController;
import mekanism.client.gui.GuiMekanismTile;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiEnergyGauge;
import mekanism.client.gui.element.gauge.GuiGasGauge;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.common.MekanismLang;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.util.text.EnergyDisplay;
import mekanism.generators.common.GeneratorsLang;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GuiContainedUniverseReactorController extends GuiMekanismTile<TileEntityContainedUniverseReactorController, MekanismTileContainer<TileEntityContainedUniverseReactorController>> {

    public GuiContainedUniverseReactorController(MekanismTileContainer<TileEntityContainedUniverseReactorController> container, Inventory inv, Component title) {
        super(container, inv, title);
        dynamicSlots = true;
        titleLabelY = 5;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        if (tile.getMultiblock().isFormed()) {
            addRenderableWidget(new GuiEnergyTab(this, () -> {
                ContainedUniverseReactorMultiblockData multiblock = tile.getMultiblock();
                return List.of(MekanismLang.STORING.translate(EnergyDisplay.of(multiblock.energyOutputContainer)),
                        GeneratorsLang.PRODUCING_AMOUNT.translate(EnergyDisplay.of(multiblock.getPassiveGeneration(true))));
            }));

            addRenderableWidget(new GuiEnergyGauge(tile.getMultiblock().energyContainer, GaugeType.STANDARD, this, 97, 16));
            addRenderableWidget(new GuiGasGauge(() -> tile.getMultiblock().antimatterTank, () -> tile.getMultiblock().getGasTanks(null), GaugeType.STANDARD, this, 61, 16));

            addRenderableWidget(new GuiContainedUniverseReactorTab(this, tile, ContainedUniverseReactorTab.OUTPUT));
            addRenderableWidget(new GuiContainedUniverseReactorTab(this, tile, ContainedUniverseReactorTab.FUEL));
        }
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        drawString(guiGraphics, MekanismLang.MULTIBLOCK_FORMED.translate(), 8, 16, titleTextColor());
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}
