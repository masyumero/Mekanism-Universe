package io.github.masyumero.mekuniverse.common.command;

import io.github.masyumero.mekuniverse.common.registry.MekUniverseBlocks;
import mekanism.common.command.builders.StructureBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class MekUniverseBuilders {

    private MekUniverseBuilders() {}

    public static class ContainedUniverseReactorBuilder extends StructureBuilder {

        public ContainedUniverseReactorBuilder() {
            super(13, 13, 13);
        }

        @Override
        protected void build(Level world, BlockPos start, boolean empty) {
            // 从角落往各边忽略cutoff+1个方块
            buildPartialFrame(world, start, 2);
            buildWalls(world, start);
            // 和数组类似，以0开始算
            buildInteriorLayers(world, start, 1, 7, Blocks.AIR);
            world.setBlockAndUpdate(start.offset(6, 12, 6), MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CONTROLLER.getBlock().defaultBlockState());
        }

        @Override
        protected Block getWallBlock(BlockPos pos) {
            return MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING.getBlock();
        }

        @Override
        protected Block getCasing() {
            return MekUniverseBlocks.CONTAINED_UNIVERSE_REACTOR_CASING.getBlock();
        }
    }
}
