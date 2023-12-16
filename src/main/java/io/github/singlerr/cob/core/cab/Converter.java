package io.github.singlerr.cob.core.cab;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;

public interface Converter {

    boolean accept(BlockState blockState, IBlockDisplayReader world, BlockPos pos);

    int get(int id, BlockState blockState, IBlockDisplayReader world, BlockPos pos);

}
