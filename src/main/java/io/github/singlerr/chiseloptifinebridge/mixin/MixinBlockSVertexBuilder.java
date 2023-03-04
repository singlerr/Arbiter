package io.github.singlerr.chiseloptifinebridge.mixin;

import io.github.singlerr.chiseloptifinebridge.core.ChiselOptifineBridgeManager;
import mod.chiselsandbits.chiseledblock.BlockChiseled;
import mod.flatcoloredblocks.ModUtil;
import mod.flatcoloredblocks.block.BlockFlatColored;
import mod.flatcoloredblocks.block.EnumFlatBlockType;
import mod.flatcoloredblocks.client.ClientSide;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.optifine.shaders.BlockAliases;
import net.optifine.shaders.SVertexBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SVertexBuilder.class)
public final class MixinBlockSVertexBuilder {

    /**
     * @author
     * @reason to support chiselsandbits
     */
    @Overwrite
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

        int blockAliasId = BlockAliases.getBlockAliasId(blockId, metadata);
        if (blockAliasId >= 0) {

            if(blockState.getBlock() instanceof BlockFlatColored){
                BlockFlatColored cb = (BlockFlatColored) blockState.getBlock();
                if(cb.getType() == EnumFlatBlockType.GLOWING){
                    blockId = 169;

                }else if(cb.getType() == EnumFlatBlockType.TRANSPARENT){
                    blockId = 95;
                }


            }else if(blockState.getBlock() instanceof BlockChiseled){
                blockId = ChiselOptifineBridgeManager.mapBlockId(blockId, blockState, blockAccess, blockPos);

            }
        }

        int renderType = block.getRenderType(blockState).ordinal();
        int dataLo = ((renderType & '\uffff') << 16) + (blockId & '\uffff');
        int dataHi = metadata & '\uffff';
        wrr.sVertexBuilder.pushEntity(((long) dataHi << 32) + (long) dataLo);
    }

}
