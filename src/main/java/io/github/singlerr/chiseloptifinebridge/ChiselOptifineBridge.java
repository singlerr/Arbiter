package io.github.singlerr.chiseloptifinebridge;

import mod.chiselsandbits.chiseledblock.BlockChiseled;
import mod.chiselsandbits.helpers.ModUtil;
import mod.chiselsandbits.render.helpers.ModelUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.chunk.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.optifine.shaders.SVertexBuilder;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersRender;

@Mod(
        modid = ChiselOptifineBridge.MOD_ID,
        name = ChiselOptifineBridge.MOD_NAME,
        version = ChiselOptifineBridge.VERSION,
        dependencies = "required-after:chiselsandbits;required-after:flatcoloredblocks"
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





}
