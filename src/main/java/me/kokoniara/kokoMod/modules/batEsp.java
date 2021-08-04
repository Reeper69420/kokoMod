package me.kokoniara.kokoMod.modules;

import me.kokoniara.kokoMod.helpers.graphics.renderEsp;
import me.kokoniara.kokoMod.kokoMod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityBat;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class batEsp {
    @SubscribeEvent
    public void RenderLivingEvent(RenderWorldLastEvent event) {
        if(kokoMod.batEspToggle) {
            for (Entity ent : Minecraft.getMinecraft().theWorld.loadedEntityList) {
                if(ent instanceof EntityBat){
                    renderEsp.drawbox(ent.posX, ent.posY, ent.posZ, event);
                }
            }
        }

    }
}
