package io.github.masyumero.mekuniverse.client.gui;

import io.github.masyumero.mekuniverse.common.content.universe_reactor.ContainedUniverseReactorMultiblockData;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorController;
import java.util.List;
import mekanism.client.gui.GuiMekanismTile;
import mekanism.client.gui.element.button.MekanismImageButton;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.common.Mekanism;
import mekanism.common.MekanismLang;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.network.to_server.PacketGuiButtonPress;
import mekanism.common.util.text.EnergyDisplay;
import mekanism.generators.common.GeneratorsLang;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public abstract class GuiContainedUniverseReactorInfo extends GuiMekanismTile<TileEntityContainedUniverseReactorController, EmptyTileContainer<TileEntityContainedUniverseReactorController>> {

    public GuiContainedUniverseReactorInfo(EmptyTileContainer<TileEntityContainedUniverseReactorController> container, Inventory inv, Component title) {
        super(container, inv, title);
        titleLabelY = 5;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new MekanismImageButton(this, 6, 6, 14, getButtonLocation("back"),
                () -> Mekanism.packetHandler().sendToServer(new PacketGuiButtonPress(PacketGuiButtonPress.ClickedTileButton.BACK_BUTTON, tile)), getOnHover(MekanismLang.BACK)));
        addRenderableWidget(new GuiEnergyTab(this, () -> {
            ContainedUniverseReactorMultiblockData multiblock = tile.getMultiblock();
            return List.of(MekanismLang.STORING.translate(EnergyDisplay.of(multiblock.energyOutputContainer)),
                    GeneratorsLang.PRODUCING_AMOUNT.translate(EnergyDisplay.of(multiblock.getPassiveGeneration(true))));
        }));
    }
}