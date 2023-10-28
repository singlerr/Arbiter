package io.github.singlerr.cob.mixin;

import io.github.singlerr.cob.core.CustomUniforms;
import mod.flatcoloredblocks.block.BlockFlatColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.optifine.shaders.Shaders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileEntityRendererDispatcher.class)
public abstract class MixinTileEntityRendererDispatcher {

    @Inject(method = "render(Lnet/minecraft/tileentity/TileEntity;FI)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher;render(Lnet/minecraft/tileentity/TileEntity;DDDFIF)V", shift = At.Shift.BEFORE))
    private void onRender(TileEntity tileentityIn, float partialTicks, int destroyStage, CallbackInfo ci) {
        if (!Shaders.isRenderingWorld)
            return;
        IBlockState state = tileentityIn.getWorld().getBlockState(tileentityIn.getPos());

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
        CustomUniforms.getBlockColor().getUniform().setValue(r, g, b, 255f);
        CustomUniforms.getIsStainedGlass().getUniform().setValue(1);
    }
}
