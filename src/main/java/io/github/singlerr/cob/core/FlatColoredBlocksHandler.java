package io.github.singlerr.cob.core;

import mod.chiselsandbits.chiseledblock.BlockChiseled;
import mod.chiselsandbits.chiseledblock.TileEntityBlockChiseled;
import mod.chiselsandbits.helpers.ExceptionNoTileEntity;
import mod.chiselsandbits.helpers.ModUtil;
import mod.flatcoloredblocks.block.BlockFlatColored;
import mod.flatcoloredblocks.block.ConversionHSV2RGB;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IBlockDisplayReader;

public final class FlatColoredBlocksHandler {
    public static final int CODE_TRANSPARENT_BLOCK = 400;

    private static final BlockInfo BLANK = new BlockInfo(false, new Vector3i(0, 0, 0));


    public static BlockInfo handleBlock(BlockState state, BlockPos pos, IBlockDisplayReader world) {
        BlockFlatColored block = null;
        if (!((state.getBlock()) instanceof BlockFlatColored)) {
            if (!(state.getBlock() instanceof BlockChiseled)) {
                return BLANK;
            }

            try {
                TileEntityBlockChiseled tileEntity = BlockChiseled.getTileEntity(world, pos);
                BlockState blockState = ModUtil.getStateById(tileEntity.getBitAccess().getVoxelStats().mostCommonState);
                if (!(blockState.getBlock() instanceof BlockFlatColored))
                    return BLANK;

                block = (BlockFlatColored) blockState.getBlock();

            } catch (ExceptionNoTileEntity e) {
                return BLANK;
            }
        }

        if (block == null)
            block = (BlockFlatColored) state.getBlock();

        int hsv = block.hsvFromState(state);
        int rgb = ConversionHSV2RGB.toRGB(hsv);
        //r g b a (0xff 0xff 0xff 0xff)
        int r = rgb >> 16 & 255;
        int g = rgb >> 8 & 255;
        int b = rgb & 255;

        return new BlockInfo(true, new Vector3i(r, g, b));
    }

    public static class BlockInfo {
        private boolean enabled;

        private Vector3i data;

        public BlockInfo(boolean enabled, Vector3i data) {
            this.enabled = enabled;
            this.data = data;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public Vector3i getData() {
            return data;
        }

        public void setData(Vector3i data) {
            this.data = data;
        }
    }

}
