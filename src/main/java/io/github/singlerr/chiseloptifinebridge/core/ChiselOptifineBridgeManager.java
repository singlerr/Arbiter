package io.github.singlerr.chiseloptifinebridge.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mod.chiselsandbits.chiseledblock.BlockChiseled;
import mod.chiselsandbits.chiseledblock.TileEntityBlockChiseled;
import mod.chiselsandbits.chiseledblock.serialization.StringStates;
import mod.chiselsandbits.helpers.ExceptionNoTileEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.optifine.shaders.BlockAliases;
import net.optifine.shaders.SVertexBuilder;

import java.util.HashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ChiselOptifineBridgeManager {
    private static final HashMap<String, Integer> conversions = new HashMap<>();
    private static final String PREFIX = "minecraft%3A";

    static {
        conversions.put("glowstone", 89);
        conversions.put("sea_lantern", 169);
        conversions.put("stained_glass", 95);
        conversions.put("stained_hardened_clay", 95);
        conversions.put("concrete", 95);
        conversions.put("quartz_block", 95);
        conversions.put("ice",79);
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
        if (!(blockState.getBlock() instanceof BlockChiseled))
            return originalId;
        try {
            TileEntityBlockChiseled entity = BlockChiseled.getTileEntity(world, pos);
            String nbtData = StringStates.getNameFromStateID(entity.getBitAccess().getVoxelStats().mostCommonState);
            return parseBlockId(parseBlockName(nbtData), originalId);
        } catch (ExceptionNoTileEntity ignored) {
            return originalId;
        }
    }

    public static int mapEntityId(int originalId, Entity entity) {
        if (entity.getClass().getName().equals("jp.ngt.rtm.entity.train.EntityTrainElectricCar")) {
            if (Minecraft.getMinecraft().player != null)
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString(entity.getClass().getName()));
        }
        return originalId;
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
