package io.github.singlerr.cob.mixin.optifine;

import io.github.singlerr.cob.core.FlatColoredBlocksHandler;
import io.github.singlerr.cob.core.optifine.CustomUniforms;
import io.github.singlerr.cob.core.optifine.LazyShaderUniform1i;
import io.github.singlerr.cob.core.optifine.LazyShaderUniform4f;
import io.github.singlerr.cob.core.optifine.ShadersExtended;
import net.minecraft.tileentity.TileEntity;
import net.optifine.shaders.Program;
import net.optifine.shaders.SMCLog;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.MacroState;
import net.optifine.shaders.config.ShaderLine;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.uniform.ShaderUniforms;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.BufferedReader;
import java.util.List;

@Mixin(Shaders.class)
public abstract class MixinShaders {

    @Shadow
    private static ShaderUniforms shaderUniforms;

    @Inject(method = "init", at = @At("HEAD"), remap = false)
    private static void initUniforms(CallbackInfo ci) {
        CustomUniforms.setBlockColor(new LazyShaderUniform4f(shaderUniforms.make4f("chisel_blockColor")));
        CustomUniforms.setIsStainedGlass(new LazyShaderUniform1i(shaderUniforms.make1i("chisel_isStainedGlass")));
        SMCLog.info("Loaded 2 custom uniforms.");
    }

    @Inject(method = "setBlockEntityId", at = @At("HEAD"), remap = false)
    private static void onRender(TileEntity tileEntity, CallbackInfo ci) {
        if (tileEntity == null)
            return;
        FlatColoredBlocksHandler.handle(tileEntity);
    }

    @Inject(method = "useProgram", at = @At(value = "INVOKE", target = "Lnet/optifine/shaders/Shaders;checkGLError(Ljava/lang/String;)I", shift = At.Shift.BEFORE), remap = false)
    private static void setUniformValue(Program program, CallbackInfo ci) {
        //CustomUniforms.getBlockColor().getUniform().setValue(0, 255, 0, 255f);
        //CustomUniforms.getIsStainedGlass().getUniform().setValue(1);
        //FlatColoredBlocksHandler.update();

    }

    @Inject(method = "createVertShader", at = @At(value = "INVOKE", target = "Lnet/optifine/shaders/config/ShaderLine;isAttribute(Ljava/lang/String;)Z", shift = At.Shift.BEFORE, ordinal = 0), remap = false, locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private static void setup(Program program, String filename, CallbackInfoReturnable<Integer> cir, int vertShader, StringBuilder vertexCode, BufferedReader reader, ShaderOption[] activeOptions, List listFiles, MacroState macroState, String line, ShaderLine sl) {
        if (sl.isAttribute(ShadersExtended.EXTRA_ATTRIB.getName())) {
            ShadersExtended.EXTRA_ATTRIB.setEnabled(true);
        }
    }

    //  @Inject(method = "setBlockEntityId", at = @At(value = "INVOKE", target = "Lnet/optifine/shaders/uniform/ShaderUniform1i;setValue(I)V", shift = At.Shift.AFTER), remap = false)
    //  private static void onRender(TileEntity tileEntity, CallbackInfo ci) {
    //Simple trick that makes mixin not emitting class not found errors while mixing
    //      FlatColoredBlocksHandler.handle(tileEntity);
    //  }

}
