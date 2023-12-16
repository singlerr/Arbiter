package io.github.singlerr.cob.core.cab.converter;

import io.github.singlerr.cob.core.ChiselsAndBitsHandler;
import io.github.singlerr.cob.core.cab.Converter;
import mod.chiselsandbits.chiseledblock.BlockChiseled;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;

public final class BlockChiseledConverter implements Converter {
    @Override
    public boolean accept(BlockState blockState, IBlockDisplayReader world, BlockPos pos) {
        return blockState.getBlock() instanceof BlockChiseled;
    }

    @Override
    public int get(int id, BlockState blockState, IBlockDisplayReader world, BlockPos pos) {
        /*
        try {
            TileEntityBlockChiseled entity = BlockChiseled.getTileEntity(world, pos);
            IBlockState commonState = ModUtil.getStateById(entity.getBitAccess().getVoxelStats().mostCommonState);

            if (commonState.getBlock() instanceof BlockSeaLantern || (commonState.getBlock() instanceof BlockGlowstone))
                return ChiselsAndBitsHandler.GLOWSTONE;


            for (StateCount stateCount : entity.getBitAccess().getStateCounts()) {
                IBlockState state = ModUtil.getStateById(stateCount.stateId);

                if (state.getBlock() instanceof BlockFlatColored) {
                    BlockFlatColored b = (BlockFlatColored) state.getBlock();
                    if (b.getType() == EnumFlatBlockType.GLOWING)
                        return ChiselsAndBitsHandler.GLOWSTONE;
                    if (b.getType() == EnumFlatBlockType.TRANSPARENT)
                        return FlatColoredBlocksHandler.CODE_TRANSPARENT_BLOCK;
                }

                if (state.getBlock() instanceof BlockSeaLantern || state.getBlock() instanceof BlockGlowstone)
                    continue;

                if (blockState.getBlock() instanceof BlockStainedGlass)
                    return ChiselsAndBitsHandler.STAINED_GLASS;

                if (blockState.getBlock().equals(Blocks.REDSTONE_BLOCK))
                    return ChiselsAndBitsHandler.REDSTONE_BLOCK;

                if (blockState.getBlock().equals(Blocks.EMERALD_BLOCK))
                    return ChiselsAndBitsHandler.EMERALD_BLOCK;

                if (blockState.getBlock() instanceof BlockIce)
                    return ChiselsAndBitsHandler.ICE;

                if (blockState.getBlock().equals(Blocks.LAPIS_BLOCK))
                    return ChiselsAndBitsHandler.LAPIS_BLOCK;


            }
        } catch (ExceptionNoTileEntity ex) {
            return id;
        }*/
        return ChiselsAndBitsHandler.CHISELED_BLOCK;
    }
}
