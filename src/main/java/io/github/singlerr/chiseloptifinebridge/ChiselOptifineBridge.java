package io.github.singlerr.chiseloptifinebridge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = ChiselOptifineBridge.MOD_ID,
        name = ChiselOptifineBridge.MOD_NAME,
        version = ChiselOptifineBridge.VERSION,
        dependencies = "required-after:chiselsandbits"
)
public class ChiselOptifineBridge {

    public static final String MOD_ID = "chiseloptifinebridge";
    public static final String MOD_NAME = "ChiselOptifineBridge";
    public static final String VERSION = "1.0";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static ChiselOptifineBridge INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }


}
