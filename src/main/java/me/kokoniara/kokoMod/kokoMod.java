package me.kokoniara.kokoMod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.opengl.Display;

@Mod(modid = kokoMod.MODID, version = kokoMod.VERSION)
public class kokoMod {
    public static final String MODID = "kokoMod";
    public static final String VERSION = "1.0";

    public static objects objectsi = new objects();

    private static void updateTitle(){
        Display.setTitle("Kokoclient V69.420");
    }


    @EventHandler
    public static void Init(FMLPreInitializationEvent event) {
        updateTitle();
        MinecraftForge.EVENT_BUS.register(objectsi.keyBindinstance);
    }
}