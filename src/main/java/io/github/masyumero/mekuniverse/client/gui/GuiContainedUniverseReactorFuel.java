package io.github.masyumero.mekuniverse.client.gui;

import io.github.masyumero.mekuniverse.client.gui.element.GuiContainedUniverseReactorTab;
import io.github.masyumero.mekuniverse.client.gui.element.GuiContainedUniverseReactorTab.ContainedUniverseReactorTab;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorController;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiGasGauge;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class GuiContainedUniverseReactorFuel extends GuiContainedUniverseReactorInfo {

    public GuiContainedUniverseReactorFuel(EmptyTileContainer<TileEntityContainedUniverseReactorController> container, Inventory inv, Component title) {
        super(container, inv, title);
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiGasGauge(() -> tile.getMultiblock().heliumTank, () -> tile.getMultiblock().getGasTanks(null), GaugeType.SMALL, this, 25, 64));
        addRenderableWidget(new GuiGasGauge(() -> tile.getMultiblock().hydrogenTank, () -> tile.getMultiblock().getGasTanks(null), GaugeType.SMALL, this, 133, 64));
        addRenderableWidget(new GuiProgress(() -> tile.getMultiblock().isActive(), ProgressType.SMALL_RIGHT, this, 47, 76));
        addRenderableWidget(new GuiProgress(() -> tile.getMultiblock().isActive(), ProgressType.SMALL_LEFT, this, 101, 76));
        addRenderableWidget(new GuiContainedUniverseReactorTab(this, tile, ContainedUniverseReactorTab.OUTPUT));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
    }
}
