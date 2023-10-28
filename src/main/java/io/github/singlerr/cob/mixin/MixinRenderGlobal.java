package io.github.singlerr.cob.mixin;

import io.github.singlerr.cob.core.CustomUniforms;
import io.github.singlerr.cob.core.FlatColoredBlocksHandler;
import mod.flatcoloredblocks.block.BlockFlatColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.optifine.shaders.Shaders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Mixin(RenderGlobal.class)
public abstract class MixinRenderGlobal {
    @Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher;render(Lnet/minecraft/tileentity/TileEntity;FI)V", ordinal = 0, shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void onRender(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci, int pass, double d2, Entity entity, double d3, double d4, double d5, List list, List list1, List list2, BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos, Iterator var22, RenderGlobal.ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation1, List list3, Iterator var25, TileEntity tileentity) {
        if (Shaders.isRenderingWorld) {
            FlatColoredBlocksHandler.handle(tileentity);
        }
    }

    @Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher;render(Lnet/minecraft/tileentity/TileEntity;FI)V", ordinal = 1, shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void onRender_2(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci, int pass, double d0, double d2, Entity entity, double d3, double d4, double d5, List list, List list1, List list2, BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos, Set var22, Iterator var23, TileEntity tileentity) {
        if (Shaders.isRenderingWorld) {
            FlatColoredBlocksHandler.handle(tileentity);
        }
    }

    @Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher;render(Lnet/minecraft/tileentity/TileEntity;FI)V", ordinal = 2, shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void onRender_3(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci, DestroyBlockProgress destroyblockprogress, BlockPos blockpos, TileEntity tileentity1, IBlockState iblockstate) {
        if (Shaders.isRenderingWorld) {
            FlatColoredBlocksHandler.handle(tileentity1);
        }
    }
}
