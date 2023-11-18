package io.github.singlerr.cob.mixin.optifine;

import io.github.singlerr.cob.core.optifine.ShadersExtended;
import net.minecraft.util.BlockRenderLayer;
import net.optifine.shaders.ShadersRender;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShadersRender.class)
public abstract class MixinShadersRender {

    @Inject(method = "setupArrayPointersVbo", at = @At("TAIL"), remap = false)
    private static void setup(CallbackInfo ci) {
        GL20.glVertexAttribPointer(ShadersExtended.EXTRA_ATTRIB.getIndex(), 4, GL11.GL_FLOAT, false, 0, 0);
    }

    @Inject(method = "preRenderChunkLayer", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL20;glEnableVertexAttribArray(I)V", ordinal = 2, shift = At.Shift.AFTER), remap = false)
    private static void setupAttrib(BlockRenderLayer blockLayerIn, CallbackInfo ci) {
        GL20.glEnableVertexAttribArray(ShadersExtended.EXTRA_ATTRIB.getIndex());
    }

    @Inject(method = "postRenderChunkLayer", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL20;glDisableVertexAttribArray(I)V", ordinal = 2, shift = At.Shift.AFTER), remap = false)
    private static void disableAttrib(BlockRenderLayer blockLayerIn, CallbackInfo ci) {
        GL20.glDisableVertexAttribArray(ShadersExtended.EXTRA_ATTRIB.getIndex());
    }
}
