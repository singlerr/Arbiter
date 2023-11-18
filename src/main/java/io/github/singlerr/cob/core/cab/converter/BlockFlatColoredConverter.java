package io.github.singlerr.cob.core.cab.converter;

import io.github.singlerr.cob.core.ChiselsAndBitsHandler;
import io.github.singlerr.cob.core.FlatColoredBlocksHandler;
import io.github.singlerr.cob.core.cab.Converter;
import mod.flatcoloredblocks.block.BlockFlatColored;
import mod.flatcoloredblocks.block.EnumFlatBlockType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public final class BlockFlatColoredConverter implements Converter {

    @Override
    public boolean accept(IBlockState blockState, IBlockAccess world, BlockPos pos) {
        return blockState.getBlock() instanceof BlockFlatColored;
    }

    @Override
    public int get(int id, IBlockState blockState, IBlockAccess world, BlockPos pos) {

        BlockFlatColored val = (BlockFlatColored) blockState.getBlock();
        if (val.getType() == EnumFlatBlockType.GLOWING) {
            return ChiselsAndBitsHandler.GLOWSTONE;
        } else if (val.getType() == EnumFlatBlockType.TRANSPARENT) {
            return FlatColoredBlocksHandler.CODE_TRANSPARENT_BLOCK;
        }
        return id;
    }
}
