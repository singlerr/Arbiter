package io.github.singlerr.chiseloptifinebridge.mixin;

import net.optifine.shaders.SVertexBuilder;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SVertexBuilder.class)
public class MixinBlockSVertexBuilder {

    /**
     * @author
     * @reason
     */
    /*
    public static void pushEntity(IBlockState blockState, BlockPos blockPos, IBlockAccess blockAccess, BufferBuilder wrr) {
        Block block = blockState.getBlock();
        int blockId;
        int metadata;
        if (blockState instanceof BlockStateBase) {
            BlockStateBase bsb = (BlockStateBase) blockState;
            blockId = bsb.getBlockId();
            metadata = bsb.getMetadata();
        } else {
            blockId = Block.getIdFromBlock(block);
            metadata = block.getMetaFromState(blockState);
        }
        int renderType = block.getRenderType(blockState).ordinal();
        int blockAliasId = BlockAliases.getBlockAliasId(blockId, metadata);
        if (blockAliasId >= 0) {

            if (blockState.getBlock() instanceof BlockFlatColored) {
                BlockFlatColored cb = (BlockFlatColored) blockState.getBlock();
                if (cb.getType() == EnumFlatBlockType.GLOWING) {
                    blockId = 169;
                } else if (cb.getType() == EnumFlatBlockType.TRANSPARENT) {
                    blockId = 95;
                }
            } else if (blockState.getBlock() instanceof BlockChiseled) {
                blockId = ChiselOptifineBridgeManager.mapBlockId(blockId, blockState, blockAccess, blockPos);
            }
        }
        int dataLo = ((renderType & '\uffff') << 16) + (blockId & '\uffff');
        int dataHi = metadata & '\uffff';
        wrr.sVertexBuilder.pushEntity(((long) dataHi << 32) + (long) dataLo);
    }

     */


}
