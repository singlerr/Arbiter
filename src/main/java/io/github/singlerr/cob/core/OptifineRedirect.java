package io.github.singlerr.cob.core;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.singlerr.cob.mixin.optifine.AccessorBufferBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IBlockDisplayReader;
import net.optifine.shaders.BlockAliases;

public final class OptifineRedirect {

    /***
     *
     * @param state
     * @param world
     * @param pos
     * @param builder
     */
    public static void pushEntity(BlockState state, IBlockDisplayReader world, BlockPos pos, IVertexBuilder builder) {
        if (builder instanceof BufferBuilder) {
            FlatColoredBlocksHandler.BlockInfo info = FlatColoredBlocksHandler.handleBlock(state, pos, world);

            if (!info.isEnabled()) {
                int blockId = ChiselsAndBitsHandler.mapBlockId(BlockAliases.getAliasBlockId(state), state, world, pos);;
                int metadata = BlockAliases.getAliasMetadata(state);
                int renderType = BlockAliases.getRenderType(state);
                int dataLo = ((renderType & '\uffff') << 16) + (blockId & '\uffff');
                int dataHi = metadata & '\uffff';
                ((AccessorBufferBuilder) builder).getSVertexBuilder().pushEntity(((long)dataHi << 32) + (long)dataLo);
                return;
            }
            Vector3i color = info.getData();
            int blockId = ChiselsAndBitsHandler.mapBlockId(BlockAliases.getAliasBlockId(state), state, world, pos);
            int x = color.getX() & 0xFFFF;
            int y = color.getY() & 0xFFFF;
            int z = color.getZ() & 0xFFFF;
            int lo = (x << 16) + (blockId & 0xFFFF);
            int hi = (z << 16) + (y & 0xFFFF);
            ((AccessorBufferBuilder) builder).getSVertexBuilder().pushEntity(((long) hi << 32) | lo);
        }
    }
}
