package io.github.singlerr.chiseloptifinebridge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid = ChiselOptifineBridge.MOD_ID,
        name = ChiselOptifineBridge.MOD_NAME,
        version = ChiselOptifineBridge.VERSION,
        dependencies = "required-after:chiselsandbits;required-after:flatcoloredblocks"
)
public class ChiselOptifineBridge {

    public static final String MOD_ID = "chiseloptifinebridge";
    public static final String MOD_NAME = "ChiselOptifineBridge";
    public static final String VERSION = "1.8";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static ChiselOptifineBridge INSTANCE;


    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {

        //   MinecraftForge.EVENT_BUS.register(this);
    }
/*
    @SubscribeEvent
    public void onPlace(BlockEvent.EntityPlaceEvent event){
        IBlockState state = event.getPlacedBlock();
        if(state.getBlock() instanceof BlockFlatColored){
            BlockFlatColored block = (BlockFlatColored) state.getBlock();
        }
        if(state.getBlock() instanceof BlockStainedGlass){
            TextureAtlasSprite sprite = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state).getQuads(state, EnumFacing.EAST, 0).get(0).getSprite();
            System.out.println(sprite);
        }
    }

 */


}
