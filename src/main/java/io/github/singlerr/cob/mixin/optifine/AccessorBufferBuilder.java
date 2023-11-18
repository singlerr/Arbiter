package io.github.singlerr.cob.mixin.optifine;

import net.minecraft.client.renderer.BufferBuilder;
import net.optifine.shaders.SVertexBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.nio.IntBuffer;

@Mixin(BufferBuilder.class)
public interface AccessorBufferBuilder {

    @Accessor("sVertexBuilder")
    SVertexBuilder getSVertexBuilder();

    @Accessor("rawIntBuffer")
    IntBuffer getRawIntBuffer();
}
