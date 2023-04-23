package io.github.singlerr.chiseloptifinebridge;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class ClientEventHandler {
    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        String text = "§a" + ChiselOptifineBridge.MOD_NAME + " v" + ChiselOptifineBridge.VERSION + " §fby singlerr";
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(text, event.getResolution().getScaledWidth() - Minecraft.getMinecraft().fontRenderer.getStringWidth(text), 2, Color.WHITE.getRGB());
    }
}
