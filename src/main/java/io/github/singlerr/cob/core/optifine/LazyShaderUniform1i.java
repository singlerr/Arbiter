package io.github.singlerr.cob.core.optifine;

import net.optifine.shaders.uniform.ShaderUniform1i;

public class LazyShaderUniform1i {

    private final ShaderUniform1i uniform;

    private int value;

    public LazyShaderUniform1i(ShaderUniform1i uniform) {
        this.uniform = uniform;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ShaderUniform1i getUniform() {
        return uniform;
    }

    public void update() {
        uniform.setValue(value);
    }
}
