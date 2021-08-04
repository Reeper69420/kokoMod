package me.kokoniara.kokoMod;

import me.kokoniara.kokoMod.modules.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = kokoMod.MODID, version = kokoMod.VERSION)
public class kokoMod {
    public static final String MODID = "kokoMod";
    public static final String VERSION = "1.0";

    public static farmReady farmReadyinstance = new farmReady();
    public static toggleSprint toggleSprintinstance = new toggleSprint();
    public static autoCliker autoClikerinstance = new autoCliker();

    public static boolean batEspToggle = false;
    public static boolean toggleSprintStatus = false;
    public static boolean autoClikerStatus = false;
    public static boolean caneFarmHelperStatus = false;


    @EventHandler
    public static void Init(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new Keybinds());
        MinecraftForge.EVENT_BUS.register(new batEsp());


        Keybinds.registerKeybinds();
    }

}














