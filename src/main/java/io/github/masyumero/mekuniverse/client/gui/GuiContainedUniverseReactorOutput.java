package io.github.masyumero.mekuniverse.client.gui;

import io.github.masyumero.mekuniverse.MekanismUniverseLang;
import io.github.masyumero.mekuniverse.client.gui.element.GuiContainedUniverseReactorTab;
import io.github.masyumero.mekuniverse.client.gui.element.GuiContainedUniverseReactorTab.ContainedUniverseReactorTab;
import io.github.masyumero.mekuniverse.common.content.universe_reactor.ContainedUniverseReactorMultiblockData;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorController;
import mekanism.client.gui.element.GuiInnerScreen;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiEnergyGauge;
import mekanism.client.gui.element.gauge.GuiGasGauge;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.util.text.EnergyDisplay;
import mekanism.common.util.text.TextUtils;
import mekanism.generators.common.GeneratorsLang;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GuiContainedUniverseReactorOutput extends GuiContainedUniverseReactorInfo {

    public GuiContainedUniverseReactorOutput(EmptyTileContainer<TileEntityContainedUniverseReactorController> container, Inventory inv, Component title) {
        super(container, inv, title);
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiEnergyGauge(tile.getMultiblock().energyOutputContainer, GaugeType.WIDE, this, 20, 25));
        addRenderableWidget(new GuiGasGauge(() -> tile.getMultiblock().heliumOutputTank, () -> tile.getMultiblock().getGasTanks(null), GaugeType.WIDE, this, 90, 25));
        addRenderableWidget(new GuiInnerScreen(this, 20, 90, 136, 46, () -> {
            ContainedUniverseReactorMultiblockData multiblock = tile.getMultiblock();
            return List.of(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_TIER.translate(multiblock.getTier()),
                    MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR_SATELLITE.translate(multiblock.getSatelliteCount()),
                    GeneratorsLang.REACTOR_PASSIVE_RATE.translate(EnergyDisplay.of(multiblock.getPassiveGeneration(true))),
                    GeneratorsLang.REACTOR_STEAM_PRODUCTION.translate(TextUtils.format(multiblock.getHeliumPerTick(true))));
        }));
        addRenderableWidget(new GuiContainedUniverseReactorTab(this, tile, ContainedUniverseReactorTab.FUEL));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}
