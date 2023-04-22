package io.github.singlerr.chiseloptifinebridge.mixin;

import mod.flatcoloredblocks.textures.AlphaModifiedTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.image.BufferedImage;
import java.util.function.Function;

@Mixin(AlphaModifiedTexture.class)
public class MixinAlphaModifiedTextureAtlasSprite {


    @Inject(method = "generate", remap = false, at = @At(
            value = "HEAD"
    ), cancellable = true)
    private static void generate(String name, BufferedImage bi, float alphaMultiplier, TextureMap map, CallbackInfoReturnable<TextureAtlasSprite> cir) {
        TextureAtlasSprite dummy = new DummyTextureSprite(name);
        map.setTextureEntry(dummy);
        cir.setReturnValue(dummy);
        cir.cancel();
    }

    @Inject(method = "hasCustomLoader", remap = false, at = @At(
            value = "HEAD"
    ), cancellable = true)
    public void hasCustomLoader(IResourceManager manager, ResourceLocation location, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
        cir.cancel();
    }

    @Inject(method = "load", remap = false, at = @At(
            value = "HEAD"
    ), cancellable = true)
    public void load(IResourceManager manager, ResourceLocation location, Function<ResourceLocation, TextureAtlasSprite> textureGetter, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
        cir.cancel();
    }

}
