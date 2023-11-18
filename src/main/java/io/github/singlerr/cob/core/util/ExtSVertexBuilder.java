package io.github.singlerr.cob.core.util;

import io.github.singlerr.cob.core.FlatColoredBlocksHandler;
import net.minecraft.block.state.IBlockState;

public interface ExtSVertexBuilder {

    void pushExtra(FlatColoredBlocksHandler.BlockInfo data);

    void pushExtra(IBlockState state);

    FlatColoredBlocksHandler.BlockInfo[] getExtraData();

    int getEntityDataIndex();

    int getVertexSize();
}
