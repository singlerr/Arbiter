package io.github.singlerr.cob.core;

import mod.flatcoloredblocks.block.BlockFlatColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

public final class FlatColoredBlocksHandler {

    public static void update(){
        CustomUniforms.getIsStainedGlass().update();
        CustomUniforms.getBlockColor().update();
    }
    public static void handle(IBlockState state){
        if (!((state.getBlock()) instanceof BlockFlatColored)) {
            CustomUniforms.getIsStainedGlass().setValue(0);
            CustomUniforms.getIsStainedGlass().getUniform().setValue(0);
            return;
        }
        BlockFlatColored block = (BlockFlatColored) state.getBlock();
        int color = block.colorFromState(state);
        //r g b a (0xff 0xff 0xff 0xff)
        int b = (color)&0xFF;
        int g = (color>>8)&0xFF;
        int r = (color>>16)&0xFF;
        int a = (color>>24)&0xFF;
        CustomUniforms.getBlockColor().setValue(r,g,b,255f);
        CustomUniforms.getIsStainedGlass().setValue(1);
        CustomUniforms.getBlockColor().getUniform().setValue(r, g, b, 255f);
        CustomUniforms.getIsStainedGlass().getUniform().setValue(1);
    }
    public static void handle(TileEntity tileEntity) {
        IBlockState state = tileEntity.getWorld().getBlockState(tileEntity.getPos());

        if (!((state.getBlock()) instanceof BlockFlatColored)) {
            CustomUniforms.getIsStainedGlass().setValue(0);
            CustomUniforms.getIsStainedGlass().getUniform().setValue(0);
            return;
        }
        BlockFlatColored block = (BlockFlatColored) state.getBlock();
        int color = block.colorFromState(state);
        //r g b a (0xff 0xff 0xff 0xff)
        int b = (color)&0xFF;
        int g = (color>>8)&0xFF;
        int r = (color>>16)&0xFF;
        int a = (color>>24)&0xFF;
        CustomUniforms.getBlockColor().setValue(r,g,b,a);
        CustomUniforms.getIsStainedGlass().setValue(1);
        CustomUniforms.getBlockColor().getUniform().setValue(r, g, b, 255f);
        CustomUniforms.getIsStainedGlass().getUniform().setValue(1);
    }

}
