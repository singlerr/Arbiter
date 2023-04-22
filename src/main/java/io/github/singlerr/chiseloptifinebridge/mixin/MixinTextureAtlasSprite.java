package io.github.singlerr.chiseloptifinebridge.mixin;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Mixin(TextureAtlasSprite.class)
public class MixinTextureAtlasSprite {

    @Redirect(method = "loadSpriteFrames", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/TextureUtil;readBufferedImage(Ljava/io/InputStream;)Ljava/awt/image/BufferedImage;"))
    private BufferedImage getAlphaModifiedBufferedImage(InputStream bufferedImage, IResource resource) throws IOException {
        if (!resource.getResourcePackName().startsWith("flatcoloredblocks"))
            return TextureUtil.readBufferedImage(bufferedImage);


        BufferedImage preImage = TextureUtil.readBufferedImage(bufferedImage);
        BufferedImage image = new BufferedImage(preImage.getWidth(), preImage.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        final int xx = preImage.getWidth();
        final int yy = preImage.getHeight();

        for (int x = 0; x < xx; ++x) {
            for (int y = 0; y < yy; ++y) {
                final int color = preImage.getRGB(x, y);
                int a = (int) ((color >> 24 & 0xff) * 1f);
                image.setRGB(x, y, color & 0xffffff | a << 24);
            }
        }
        return image;
    }


}
