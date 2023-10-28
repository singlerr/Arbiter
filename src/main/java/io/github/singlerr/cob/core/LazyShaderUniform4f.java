package io.github.singlerr.cob.core;

import net.optifine.shaders.uniform.ShaderUniform4f;

public class LazyShaderUniform4f {
    private final ShaderUniform4f uniform;

    private float x;

    private float y;

    private float z;

    private float w;

    public LazyShaderUniform4f(ShaderUniform4f uniform) {
        this.uniform = uniform;
    }

    public void setValue(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public ShaderUniform4f getUniform() {
        return uniform;
    }

    public void update() {
        this.uniform.setValue(x, y, z, w);
    }
}
