package io.github.singlerr.cob.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mod.chiselsandbits.api.StateCount;
import mod.chiselsandbits.chiseledblock.BlockChiseled;
import mod.chiselsandbits.chiseledblock.TileEntityBlockChiseled;
import mod.chiselsandbits.chiseledblock.serialization.StringStates;
import mod.chiselsandbits.helpers.ExceptionNoTileEntity;
import mod.flatcoloredblocks.block.BlockFlatColored;
import mod.flatcoloredblocks.block.EnumFlatBlockType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.optifine.shaders.BlockAliases;
import net.optifine.shaders.SVertexBuilder;

import java.util.HashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ChiselsAndBitsHandler {
    public static final HashMap<String, Integer> conversions = new HashMap<>();
    private static final String PREFIX = "minecraft%3A";

    static {
        conversions.put("glowstone", 89);
        conversions.put("sea_lantern", 169);
        conversions.put("stained_glass", 95);
        conversions.put("redstone_block", 152);
        conversions.put("lapis_block", 22);
        conversions.put("emerald_block", 133);
        conversions.put("ice", 79);
    }

    /**
     * Hooked to {@link SVertexBuilder}. Replacement of {@link SVertexBuilder#pushEntity(IBlockState, BlockPos, IBlockAccess, BufferBuilder)}
     *
     * @param originalId value of {@link BlockAliases#getBlockAliasId(int, int)}
     * @param blockState parameter of {@link SVertexBuilder#pushEntity(IBlockState, BlockPos, IBlockAccess, BufferBuilder)}
     * @param world      parameter of {@link SVertexBuilder#pushEntity(IBlockState, BlockPos, IBlockAccess, BufferBuilder)}
     * @param pos        parameter of {@link SVertexBuilder#pushEntity(IBlockState, BlockPos, IBlockAccess, BufferBuilder)}
     * @return if {@link IBlockState#getBlock()} is {@link BlockChiseled} and {@link TileEntityBlockChiseled} exists, return mapped id else return originalId
     */
    public static int mapBlockId(int originalId, IBlockState blockState, IBlockAccess world, BlockPos pos) {
        //TODO(ModUtil.getTileEntitySafely(world, pos) instanceof TileEntityBlockChiseled)) if not working
        if (blockState.getBlock() instanceof BlockFlatColored) {
            BlockFlatColored cb = (BlockFlatColored) blockState.getBlock();
            if (cb.getType() == EnumFlatBlockType.GLOWING) {
                return 169;
            } else if (cb.getType() == EnumFlatBlockType.TRANSPARENT) {
                return 95;
            }
        }
        if (!(blockState.getBlock() instanceof BlockChiseled))
            return originalId;
        try {
            TileEntityBlockChiseled entity = BlockChiseled.getTileEntity(world, pos);
            String nbtData = StringStates.getNameFromStateID(entity.getBitAccess().getVoxelStats().mostCommonState);
            if (parseBlockId(parseBlockName(nbtData), originalId) == 169)
                return 169;


            for (StateCount stateCount : entity.getBitAccess().getStateCounts()) {
                nbtData = StringStates.getNameFromStateID(stateCount.stateId);
                String blockName = parseBlockName(nbtData);
                if (blockName.contains("glowing") && blockName.contains("flatcolored"))
                    return 169;
                if (conversions.containsKey(blockName)) {
                    int blockId = parseBlockId(blockName, originalId);
                    if (blockId == 169)
                        continue;
                    return blockId;
                }

            }
            return originalId;
        } catch (ExceptionNoTileEntity ignored) {
            return originalId;
        }
    }


    private static String parseBlockName(String sourceName) {
        int blockInfoStart = sourceName.indexOf(PREFIX) + PREFIX.length();
        int propertyStart = sourceName.indexOf("?");
        return sourceName.substring(blockInfoStart, propertyStart);
    }

    private static int parseBlockId(String name, int defaultId) {
        return conversions.getOrDefault(name, defaultId);
    }


}
