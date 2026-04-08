package io.github.masyumero.mekuniverse.common.content.universe_reactor;

import io.github.masyumero.mekuniverse.common.registry.MekUniverseBlockTypes;
import io.github.masyumero.mekuniverse.common.tile.multiblock.TileEntityContainedUniverseReactorController;
import mekanism.common.MekanismLang;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.lib.math.voxel.VoxelCuboid;
import mekanism.common.lib.multiblock.CuboidStructureValidator;
import mekanism.common.lib.multiblock.FormationProtocol;
import mekanism.common.lib.multiblock.Structure;
import mekanism.common.lib.multiblock.StructureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;

public class ContainedUniverseReactorValidator extends CuboidStructureValidator<ContainedUniverseReactorMultiblockData> {

    private static final VoxelCuboid BOUNDS = new VoxelCuboid(13, 13, 13);
    private static final byte[][] ALLOWED_GRID = {
            { 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0 },
            { 0, 0, 1, 1, 2, 2, 2, 2, 2, 1, 1, 0, 0 },
            { 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0 },
            { 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0 },
            { 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
            { 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
            { 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
            { 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
            { 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
            { 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0 },
            { 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0 },
            { 0, 0, 1, 1, 2, 2, 2, 2, 2, 1, 1, 0, 0 },
            { 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0 },
    };

    @Override
    protected FormationProtocol.StructureRequirement getStructureRequirement(BlockPos pos) {
        VoxelCuboid.WallRelative relative = cuboid.getWallRelative(pos);
        if (relative.isWall()) {
            Structure.Axis axis = Structure.Axis.get(cuboid.getSide(pos));
            Structure.Axis h = axis.horizontal(), v = axis.vertical();
            pos = pos.subtract(cuboid.getMinPos());
            return FormationProtocol.StructureRequirement.REQUIREMENTS[ALLOWED_GRID[h.getCoord(pos)][v.getCoord(pos)]];
        }
        return super.getStructureRequirement(pos);
    }

    @Override
    protected FormationProtocol.FormationResult validateFrame(FormationProtocol<ContainedUniverseReactorMultiblockData> ctx, BlockPos pos, BlockState state, FormationProtocol.CasingType type, boolean needsFrame) {
        boolean isControllerPos = pos.getY() == cuboid.getMaxPos().getY() && pos.getX() == cuboid.getMinPos().getX() + 6 && pos.getZ() == cuboid.getMinPos().getZ() + 6;
        boolean controller = structure.getTile(pos) instanceof TileEntityContainedUniverseReactorController;
        if (isControllerPos && !controller) {
            return FormationProtocol.FormationResult.fail(MekanismLang.MULTIBLOCK_INVALID_NO_CONTROLLER);
        } else if (!isControllerPos && controller) {
            return FormationProtocol.FormationResult.fail(MekanismLang.MULTIBLOCK_INVALID_CONTROLLER_CONFLICT, true);
        }
        return super.validateFrame(ctx, pos, state, type, needsFrame);
    }

    @Override
    protected FormationProtocol.CasingType getCasingType(BlockState state) {
        Block block = state.getBlock();
        if (BlockType.is(block, MekUniverseBlockTypes.CONTAINED_UNIVERSE_REACTOR_CASING)) {
            return FormationProtocol.CasingType.FRAME;
        } else if (BlockType.is(block, MekUniverseBlockTypes.CONTAINED_UNIVERSE_REACTOR_PORT)) {
            return FormationProtocol.CasingType.VALVE;
        } else if (BlockType.is(block, MekUniverseBlockTypes.CONTAINED_UNIVERSE_REACTOR_CONTROLLER)) {
            return FormationProtocol.CasingType.OTHER;
        }
        return FormationProtocol.CasingType.INVALID;
    }

    @Override
    public boolean precheck() {
        cuboid = StructureHelper.fetchCuboid(structure, BOUNDS, BOUNDS, EnumSet.allOf(VoxelCuboid.CuboidSide.class), 120);
        return cuboid != null;
    }

    @Override
    protected boolean isFrameCompatible(BlockEntity tile) {
        return manager.isCompatible(tile);
    }
}
