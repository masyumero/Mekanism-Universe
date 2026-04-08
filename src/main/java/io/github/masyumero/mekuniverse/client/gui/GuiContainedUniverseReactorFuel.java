package io.github.masyumero.mekuniverse.client.gui;

import io.github.masyumero.mekuniverse.MekanismUniverseLang;
import io.github.masyumero.mekuniverse.client.gui.element.GuiContainedUniverseReactorTab;
import io.github.masyumero.mekuniverse.client.gui.element.GuiContainedUniverseReactorTab.ContainedUniverseReactorTab;
import io.github.masyumero.mekuniverse.common.content.universe_reactor.ContainedUniverseReactorMultiblockData;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorController;
import mekanism.client.gui.element.GuiInnerScreen;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiGasGauge;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.util.text.TextUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GuiContainedUniverseReactorFuel extends GuiContainedUniverseReactorInfo {

    public GuiContainedUniverseReactorFuel(EmptyTileContainer<TileEntityContainedUniverseReactorController> container, Inventory inv, Component title) {
        super(container, inv, title);
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiGasGauge(() -> tile.getMultiblock().heliumTank, () -> tile.getMultiblock().getGasTanks(null), GaugeType.WIDE, this, 20, 25));
        addRenderableWidget(new GuiGasGauge(() -> tile.getMultiblock().hydrogenTank, () -> tile.getMultiblock().getGasTanks(null), GaugeType.WIDE, this, 90, 25));
        addRenderableWidget(new GuiInnerScreen(this, 20, 90, 136, 23, () -> {
            ContainedUniverseReactorMultiblockData multiblock = tile.getMultiblock();
            return List.of(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_REQUEST_HYDROGEN.translate(TextUtils.format(multiblock.getRequestHydrogenAmount())),
                    MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_REQUEST_HELIUM.translate(TextUtils.format(multiblock.getRequestHeliumAmount())));
        }));
        addRenderableWidget(new GuiContainedUniverseReactorTab(this, tile, ContainedUniverseReactorTab.OUTPUT));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
    }
}
