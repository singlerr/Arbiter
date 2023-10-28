package io.github.singlerr.cob;

import net.minecraft.block.BlockStainedGlass;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid = ChiselOptifineBridge.MOD_ID,
        name = ChiselOptifineBridge.MOD_NAME,
        version = ChiselOptifineBridge.VERSION,
        dependencies = "required:flatcoloredblocks;require:chiselsandbits"
)
public class ChiselOptifineBridge {

    public static final String MOD_ID = "arbiter";
    public static final String MOD_NAME = "Arbiter";
    public static final String VERSION = "2.0.0";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static ChiselOptifineBridge INSTANCE;


    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());

    }


}
