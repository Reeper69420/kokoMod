package me.kokoniara.kokoMod.modules;

import me.kokoniara.kokoMod.helpers.graphics.renderEsp;
import me.kokoniara.kokoMod.helpers.util;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityBat;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class batEsp {
    public boolean toggled;

    @SubscribeEvent
    public void RenderLivingEvent(RenderWorldLastEvent event) {
            for (Entity ent : Minecraft.getMinecraft().theWorld.loadedEntityList) {
                if (ent instanceof EntityBat) {
                    renderEsp.drawbox(ent.posX, ent.posY, ent.posZ, event);
                }
            }
    }

    public void onEnable(){
        MinecraftForge.EVENT_BUS.register(this);
        util.sendClientMessage(" batesp enabled", true);
    }
    public void onDisable(){
        MinecraftForge.EVENT_BUS.unregister(this);
        util.sendClientMessage(" batesp disabled", true);
    }
}
