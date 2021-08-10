package me.kokoniara.kokoMod.modules;

import me.kokoniara.kokoMod.helpers.util;
import me.kokoniara.kokoMod.kokoMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class toggleSprint {
    public static boolean toggled;
    private Minecraft client = Minecraft.getMinecraft();
    private long lastClick;
    private double speed = 300;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e){
        if(toggled) {
            if(System.currentTimeMillis() - lastClick > speed){
                lastClick = System.currentTimeMillis();
                if(!client.thePlayer.isSprinting()){
                    KeyBinding.setKeyBindState(client.gameSettings.keyBindSprint.getKeyCode(), true);
                }
            }
        }
    }


    public void onEnable(){
        lastClick = System.currentTimeMillis();
        MinecraftForge.EVENT_BUS.register(this);
        util.sendClientMessage(" sprint enabled", true);
    }
    public void onDisable(){
        lastClick = 0;
        MinecraftForge.EVENT_BUS.unregister(this);
        util.sendClientMessage(" sprint disabled", true);
        KeyBinding.setKeyBindState(client.gameSettings.keyBindSprint.getKeyCode(), false);
    }
}
