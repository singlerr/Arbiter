package io.github.singlerr.cob.core.cab;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface Converter {

    boolean accept(IBlockState blockState, IBlockAccess world, BlockPos pos);

    int get(int id, IBlockState blockState, IBlockAccess world, BlockPos pos);

}
