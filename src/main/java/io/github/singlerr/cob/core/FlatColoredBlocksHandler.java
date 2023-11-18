package io.github.singlerr.cob.core;

import io.github.singlerr.cob.core.optifine.CustomUniforms;
import io.github.singlerr.cob.core.util.Vector4s;
import mod.chiselsandbits.chiseledblock.BlockChiseled;
import mod.chiselsandbits.chiseledblock.TileEntityBlockChiseled;
import mod.chiselsandbits.helpers.ExceptionNoTileEntity;
import mod.chiselsandbits.helpers.ModUtil;
import mod.flatcoloredblocks.block.BlockFlatColored;
import mod.flatcoloredblocks.block.BlockFlatColoredTranslucent;
import mod.flatcoloredblocks.block.ConversionHSV2RGB;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;

public final class FlatColoredBlocksHandler {
    public static final int CODE_TRANSPARENT_BLOCK = 400;

    private static final BlockInfo BLANK = new BlockInfo(false, new Vec3i(0, 0, 0));

    public static void update() {
        CustomUniforms.getIsStainedGlass().update();
        CustomUniforms.getBlockColor().update();
    }

    public static void handle(IBlockState state) {
        if (!((state.getBlock()) instanceof BlockFlatColored)) {
            CustomUniforms.getIsStainedGlass().setValue(0);
            CustomUniforms.getIsStainedGlass().getUniform().setValue(0);
            return;
        }
        BlockFlatColored block = (BlockFlatColored) state.getBlock();
        int color = block.colorFromState(state);
        //r g b a (0xff 0xff 0xff 0xff)
        int b = (color) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int r = (color >> 16) & 0xFF;
        int a = (color >> 24) & 0xFF;
        CustomUniforms.getBlockColor().setValue(r, g, b, 255f);
        CustomUniforms.getIsStainedGlass().setValue(1);
        CustomUniforms.getBlockColor().getUniform().setValue(r, g, b, 255f);
        CustomUniforms.getIsStainedGlass().getUniform().setValue(1);
    }

    public static Vector4s getColor(IBlockState state) {
        if (!((state.getBlock()) instanceof BlockFlatColored)) {
            return new Vector4s((short) 0, (short) 0, (short) 0, (short) 0);
        }
        BlockFlatColored block = (BlockFlatColored) state.getBlock();
        int color = block.colorFromState(state);
        //r g b a (0xff 0xff 0xff 0xff)
        int b = (color) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int r = (color >> 16) & 0xFF;
        int a = (color >> 24) & 0xFF;
        return new Vector4s((short) r, (short) g, (short) b, (short) a);
    }

    public static BlockInfo handleBlock(IBlockState state, BlockPos pos, IBlockAccess world) {
        BlockFlatColored block = null;
        if (!((state.getBlock()) instanceof BlockFlatColored)) {
            if (!(state.getBlock() instanceof BlockChiseled)) {
                return BLANK;
            }

            try {
                TileEntityBlockChiseled tileEntity = BlockChiseled.getTileEntity(world, pos);
                IBlockState blockState = ModUtil.getStateById(tileEntity.getBitAccess().getVoxelStats().mostCommonState);
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

        return new BlockInfo(true, new Vec3i(r, g, b));
    }

    public static void handle(TileEntity tileEntity) {
        IBlockState state = tileEntity.getWorld().getBlockState(tileEntity.getPos());

        if (!((state.getBlock()) instanceof BlockFlatColored)) {
            CustomUniforms.getIsStainedGlass().setValue(0);
            CustomUniforms.getIsStainedGlass().getUniform().setValue(0);
            return;
        }
        BlockFlatColored block = (BlockFlatColored) state.getBlock();
        int color = block.colorFromState(state);
        //r g b a (0xff 0xff 0xff 0xff)
        int b = (color) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int r = (color >> 16) & 0xFF;
        int a = (color >> 24) & 0xFF;
        CustomUniforms.getBlockColor().setValue(r, g, b, a);
        CustomUniforms.getIsStainedGlass().setValue(1);
        CustomUniforms.getBlockColor().getUniform().setValue(r, g, b, 255f);
        CustomUniforms.getIsStainedGlass().getUniform().setValue(1);
    }

    public static class BlockInfo {
        private boolean enabled;

        private Vec3i data;

        public BlockInfo(boolean enabled, Vec3i data) {
            this.enabled = enabled;
            this.data = data;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public Vec3i getData() {
            return data;
        }

        public void setData(Vec3i data) {
            this.data = data;
        }
    }

}
