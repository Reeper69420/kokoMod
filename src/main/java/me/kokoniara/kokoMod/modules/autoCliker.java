package me.kokoniara.kokoMod.modules;

import me.kokoniara.kokoMod.helpers.util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Mouse;

import java.util.concurrent.ThreadLocalRandom;

import static me.kokoniara.kokoMod.kokoMod.*;

public class autoCliker {
    public static int minCpsAutoclicker = 20;
    public static int maxCpsAutoclicker = 22;

    private long lastClick;
    private long hold;

    private double speed;
    private double holdLength;
    private double min;
    private double max;

    private Minecraft client = Minecraft.getMinecraft();


    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent e) {
        if(autoClikerStatus){
            if (Mouse.isButtonDown(1)) {
                if (System.currentTimeMillis() - lastClick > speed * 1000) {
                    lastClick = System.currentTimeMillis();
                    if (hold < lastClick) {
                        hold = lastClick;
                    }
                    int key = client.gameSettings.keyBindUseItem.getKeyCode();
                    KeyBinding.setKeyBindState(key, true);
                    KeyBinding.onTick(key);
                    this.updateVals();
                } else if (System.currentTimeMillis() - hold > holdLength * 1000) {
                    KeyBinding.setKeyBindState(client.gameSettings.keyBindUseItem.getKeyCode(), false);
                    this.updateVals();
                }
            }
        }
    }

    private void updateVals() {
        min = minCpsAutoclicker;
        max = maxCpsAutoclicker;

        if (min >= max) {
            max = min + 1;
        }

        speed = 1.0 / ThreadLocalRandom.current().nextDouble(min - 0.2, max);
        holdLength = speed / ThreadLocalRandom.current().nextDouble(min, max);
    }

    public void onEnable() {
        updateVals();
        MinecraftForge.EVENT_BUS.register(this);
        util.sendClientMessage(" rmb autoclicker enabled", true);
    }
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        util.sendClientMessage(" rmb autoclicker disabled", true);
    }
}
