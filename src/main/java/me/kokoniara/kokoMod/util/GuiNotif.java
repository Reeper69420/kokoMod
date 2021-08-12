package me.kokoniara.kokoMod.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class GuiNotif extends Gui {

    public void GuiNotif(Minecraft mc, String text) {
        ScaledResolution scaled = new ScaledResolution(mc);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();

        drawCenteredString(mc.fontRendererObj, text, width / 2, (height / 2) - 4, Integer.parseInt("47a8ed", 16));
    }
}