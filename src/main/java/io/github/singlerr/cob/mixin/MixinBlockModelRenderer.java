package io.github.singlerr.cob.mixin;

import io.github.singlerr.cob.core.optifine.CustomUniforms;
import mod.flatcoloredblocks.block.BlockFlatColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.optifine.shaders.Shaders;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockModelRenderer.class)
public abstract class MixinBlockModelRenderer {
    @Inject(method = "renderModel(Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/client/renderer/block/model/IBakedModel;Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/client/renderer/BufferBuilder;Z)Z", at = @At("HEAD"))
    private void resetUniforms(IBlockAccess blockAccessIn, IBakedModel modelIn, IBlockState state, BlockPos blockPosIn, BufferBuilder buffer, boolean checkSides, CallbackInfoReturnable<Boolean> cir) {
        if (!Shaders.isRenderingWorld)
            return;
        if (!((state.getBlock()) instanceof BlockFlatColored)) {
            CustomUniforms.getIsStainedGlass().setValue(0);
            return;
        }
        BlockFlatColored block = (BlockFlatColored) state.getBlock();
        int color = block.colorFromState(state);
        //r g b a (0xff 0xff 0xff 0xff)
        int r = color >> 24;
        int g = (color >> 16) & 0xff;
        int b = (color >> 8) & 0xff;
        try {
            Display.getDrawable().makeCurrent();
        } catch (LWJGLException e) {
            throw new RuntimeException(e);
        }
        CustomUniforms.getBlockColor().setValue(r, g, b, 255f);
        CustomUniforms.getIsStainedGlass().setValue(1);
    }
}
