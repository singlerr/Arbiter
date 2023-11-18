package io.github.singlerr.cob.core;

import io.github.singlerr.cob.core.cab.ConversionMap;
import io.github.singlerr.cob.core.cab.Converter;
import io.github.singlerr.cob.core.cab.converter.BlockChiseledConverter;
import io.github.singlerr.cob.core.cab.converter.BlockFlatColoredConverter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mod.chiselsandbits.chiseledblock.BlockChiseled;
import mod.chiselsandbits.chiseledblock.TileEntityBlockChiseled;
import mod.flatcoloredblocks.block.BlockFlatColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.optifine.shaders.BlockAliases;
import net.optifine.shaders.SVertexBuilder;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ChiselsAndBitsHandler {

    public static final int CHISELED_BLOCK = 550;
    public static final int GLOWSTONE = 169;

    public static final int SEA_LANTERN = 95;

    public static final int STAINED_GLASS = 95;

    public static final int REDSTONE_BLOCK = 152;

    public static final int LAPIS_BLOCK = 22;

    public static final int EMERALD_BLOCK = 133;

    public static final int ICE = 79;

    public static final ConversionMap conversionMap;

    static {
        conversionMap = new ConversionMap();

        conversionMap.put(BlockFlatColored.class, new BlockFlatColoredConverter());
        conversionMap.put(BlockChiseled.class, new BlockChiseledConverter());
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
        Optional<Converter> opt = conversionMap.findAccepts(blockState, world, pos);

        if (!opt.isPresent())
            return originalId;

        Converter conv = opt.get();

        return conv.get(originalId, blockState, world, pos);
    }


}
