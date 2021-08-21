package me.kokoniara.kokoMod.renderEngine.modules;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;


public class renderchartColumn {
    public static void renderWeirdQuad(RenderWorldLastEvent event){

        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);

        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;

        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)event.partialTicks;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)event.partialTicks;
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)event.partialTicks;


        Vec3 pos = new Vec3(d0, d1, d2);

        //Vec3 pos = event.player.getPositionEyes(event.partialTicks); Doesnt work because y-part +(double)this.getEyeHeight()

        //GL11.glTranslated(-pos.xCoord, -pos.yCoord, -pos.zCoord);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);		// draw the line on top of the geometry

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4f(1, 0, 0, 1);
        GL11.glVertex2f(-10f ,10f );
        GL11.glColor4f(0, 1, 0, 1);
        GL11.glVertex2f(10f ,10f );
        GL11.glColor4f(0, 0, 1, 1);
        GL11.glVertex2f(10f ,-10f );
        GL11.glColor4f(1, 1, 1, 1);
        GL11.glVertex2f(-10f ,-10f );
        GL11.glEnd();


        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
}
