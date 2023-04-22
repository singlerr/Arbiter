package io.github.singlerr.chiseloptifinebridge.mixin;

import io.github.singlerr.chiseloptifinebridge.ChiselOptifineBridge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.SPacketJoinGame;
import net.minecraft.util.text.TextComponentString;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {

    @Shadow
    private Minecraft client;

    @Inject(method = "handleJoinGame", at = @At(value = "TAIL"))
    private void onJoinGame(SPacketJoinGame packetIn, CallbackInfo ci) {
        client.player.sendStatusMessage(new TextComponentString("§a" + ChiselOptifineBridge.MOD_NAME + " v" + ChiselOptifineBridge.VERSION + " §f모드 작동 중(by 학점돌이)"), true);
    }
}
