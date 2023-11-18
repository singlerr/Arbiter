package io.github.singlerr.cob.mixin.optifine;

import io.github.singlerr.cob.core.FlatColoredBlocksHandler;
import io.github.singlerr.cob.core.util.ExtSVertexBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.optifine.shaders.SVertexBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SVertexBuilder.class)
public abstract class MixinSVertexBuilder implements ExtSVertexBuilder {
    @Shadow
    int entityDataIndex;
    @Shadow
    int vertexSize;
    @Unique
    private FlatColoredBlocksHandler.BlockInfo[] extraData;

    @Inject(method = "pushEntity(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/client/renderer/BufferBuilder;)V", at = @At(value = "INVOKE", target = "Lnet/optifine/shaders/SVertexBuilder;pushEntity(J)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILEXCEPTION, remap = false, cancellable = true)
    private static void inject(IBlockState blockState, BlockPos blockPos, IBlockAccess blockAccess, BufferBuilder wrr, CallbackInfo ci, Block block, int blockAliasId, int blockId, int metadata, int renderType, int dataLo, int dataHi) {
        FlatColoredBlocksHandler.BlockInfo info = FlatColoredBlocksHandler.handleBlock(blockState, blockPos, blockAccess);

        if (!info.isEnabled()) {
            return;
        }
        Vec3i color = info.getData();

        int x = color.getX() & 0xFFFF;
        int y = color.getY() & 0xFFFF;
        int z = color.getZ() & 0xFFFF;
        int lo = (x << 16) + (blockId & 0xFFFF);
        int hi = (z << 16) + (y & 0xFFFF);
        ((AccessorBufferBuilder) wrr).getSVertexBuilder().pushEntity(((long) hi << 32) | lo);
        ci.cancel();
    }

    @Override
    public void pushExtra(FlatColoredBlocksHandler.BlockInfo data) {
        extraData[entityDataIndex] = data;
    }

    @Override
    public FlatColoredBlocksHandler.BlockInfo[] getExtraData() {
        return extraData;
    }

    @Override
    public int getEntityDataIndex() {
        return entityDataIndex;
    }

    @Override
    public int getVertexSize() {
        return vertexSize;
    }

    @Override
    public void pushExtra(IBlockState state) {

    }


}
