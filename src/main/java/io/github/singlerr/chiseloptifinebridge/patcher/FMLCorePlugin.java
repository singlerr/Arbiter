package io.github.singlerr.chiseloptifinebridge.patcher;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import javax.annotation.Nullable;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion(value = "1.12.2")
@IFMLLoadingPlugin.SortingIndex(Integer.MAX_VALUE) // after deobfuscation
public class FMLCorePlugin implements IFMLLoadingPlugin {

    public FMLCorePlugin(){

    }
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{PatchApplier.class.getName()};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }


    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
