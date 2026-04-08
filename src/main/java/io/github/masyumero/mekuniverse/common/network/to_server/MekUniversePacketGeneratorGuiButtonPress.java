package io.github.masyumero.mekuniverse.common.network.to_server;

import io.github.masyumero.mekuniverse.MekanismUniverseLang;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseContainerTypes;
import mekanism.common.network.IMekanismPacket;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.function.BiFunction;

public class MekUniversePacketGeneratorGuiButtonPress implements IMekanismPacket {
    
    private final MekUniverseClickedGeneratorsTileButton tileButton;
    private final int extra;
    private final BlockPos tilePosition;

    public MekUniversePacketGeneratorGuiButtonPress(MekUniverseClickedGeneratorsTileButton buttonClicked, BlockPos tilePosition) {
        this(buttonClicked, tilePosition, 0);
    }

    public MekUniversePacketGeneratorGuiButtonPress(MekUniverseClickedGeneratorsTileButton buttonClicked, BlockPos tilePosition, int extra) {
        this.tileButton = buttonClicked;
        this.tilePosition = tilePosition;
        this.extra = extra;
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        ServerPlayer player = context.getSender();
        if (player != null) {//If we are on the server (the only time we should be receiving this packet), let forge handle switching the Gui
            TileEntityMekanism tile = WorldUtils.getTileEntity(TileEntityMekanism.class, player.level(), tilePosition);
            if (tile != null) {
                MenuProvider provider = tileButton.getProvider(tile, extra);
                if (provider != null) {
                    //Ensure valid data
                    NetworkHooks.openScreen(player, provider, buf -> {
                        buf.writeBlockPos(tilePosition);
                        buf.writeVarInt(extra);
                    });
                }
            }
        }
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeEnum(tileButton);
        buffer.writeBlockPos(tilePosition);
        buffer.writeVarInt(extra);
    }

    public static MekUniversePacketGeneratorGuiButtonPress decode(FriendlyByteBuf buffer) {
        return new MekUniversePacketGeneratorGuiButtonPress(buffer.readEnum(MekUniverseClickedGeneratorsTileButton.class), buffer.readBlockPos(), buffer.readVarInt());
    }

    public enum MekUniverseClickedGeneratorsTileButton {
        TAB_OUTPUT((tile, extra) -> MekUniverseContainerTypes.CONTAINED_UNIVERSE_REACTOR_OUTPUT.getProvider(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR, tile)),
        TAB_FUEL((tile, extra) -> MekUniverseContainerTypes.CONTAINED_UNIVERSE_REACTOR_FUEL.getProvider(MekanismUniverseLang.CONTAINED_UNIVERSE_REACTOR, tile));

        private final BiFunction<TileEntityMekanism, Integer, MenuProvider> providerFromTile;

        MekUniverseClickedGeneratorsTileButton(BiFunction<TileEntityMekanism, Integer, MenuProvider> providerFromTile) {
            this.providerFromTile = providerFromTile;
        }

        public MenuProvider getProvider(TileEntityMekanism tile, int extra) {
            return providerFromTile.apply(tile, extra);
        }
    }
}
