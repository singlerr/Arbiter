package io.github.singlerr.cob;

import mod.chiselsandbits.helpers.ModUtil;
import mod.chiselsandbits.render.chiseledblock.ChiseledBlockBaked;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid = Arbiter.MOD_ID,
        name = Arbiter.MOD_NAME,
        version = Arbiter.VERSION
)
public class Arbiter {

    public static final String MOD_ID = "arbiter";
    public static final String MOD_NAME = "Arbiter";
    public static final String VERSION = "2.0.2";




    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());

    }


}
