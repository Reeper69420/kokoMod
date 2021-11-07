package me.kokoniara.kokoMod.util;

import org.lwjgl.opengl.Display;

public class updateWidowTitle {
    public static void updateTitle(String windowTitle){
        Display.setTitle(windowTitle);
    }
}
