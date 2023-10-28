package io.github.singlerr.cob.mixin.optifine;

import io.github.singlerr.cob.core.FlatColoredBlocksHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.optifine.shaders.SVertexBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SVertexBuilder.class)
public class MixinSVertexBuilder {
    /*
    @Inject(method = "pushEntity(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/client/renderer/BufferBuilder;)V", at = @At("HEAD"), remap = false)
    private static void onRender(IBlockState blockState, BlockPos blockPos, IBlockAccess blockAccess, BufferBuilder wrr, CallbackInfo ci){
        FlatColoredBlocksHandler.handle(blockState);
    }
     */
}
