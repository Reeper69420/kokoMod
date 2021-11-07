package me.kokoniara.kokoMod.module.render;

import me.kokoniara.kokoMod.renderEngine.modules.renderEsp;
import me.kokoniara.kokoMod.module.Category;
import me.kokoniara.kokoMod.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityBat;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BatEsp extends Module {

    public BatEsp(){
        super("BatEsp", "shows location of bats", Category.RENDER, true, "enabled batEsp", "disabled batEsp");
    }

    @SubscribeEvent
    public void RenderLivingEvent(RenderWorldLastEvent event) {
        for (Entity ent : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            if (ent instanceof EntityBat) {
                renderEsp.drawbox(ent.posX, ent.posY, ent.posZ, event);
            }
        }
    }
}
