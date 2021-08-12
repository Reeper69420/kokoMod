package me.kokoniara.kokoMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.opengl.Display;

@Mod(modid = main.MODID, version = main.VERSION)
public class main {
    public static final String MODID = "kokoMod";
    public static final String VERSION = "1.0";
    
    private static void updateTitle(){
        Display.setTitle("Kokoclient V69.420");
    }
    
    @EventHandler
    public static void Init(FMLPreInitializationEvent event) {
        updateTitle();
        kokoMod.instance = new kokoMod();
        kokoMod.instance.init();
    }
}