package io.github.singlerr.cob.core;

public final class CustomUniforms {

    private static LazyShaderUniform4f blockColor;

    private static LazyShaderUniform1i isStainedGlass;

    public static LazyShaderUniform1i getIsStainedGlass() {
        return isStainedGlass;
    }

    public static void setIsStainedGlass(LazyShaderUniform1i isStainedGlass) {
        CustomUniforms.isStainedGlass = isStainedGlass;
    }

    public static LazyShaderUniform4f getBlockColor() {
        return blockColor;
    }

    public static void setBlockColor(LazyShaderUniform4f blockColor) {
        CustomUniforms.blockColor = blockColor;
    }
}
