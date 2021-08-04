package me.kokoniara.kokoMod.modules;

import me.kokoniara.kokoMod.helpers.graphics.GuiNotif;
import me.kokoniara.kokoMod.kokoMod;
import me.kokoniara.kokoMod.helpers.util;
import net.minecraft.client.Minecraft;
//import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Mouse;




public class farmReady {
    private Minecraft client = Minecraft.getMinecraft();
    GuiNotif notifier = new GuiNotif();
    private long temptime;

//    private long mouseToggleTimer;
//    private boolean preventMouseSpam;

    //private boolean rmbToggled = false;
    private boolean headlockCondition = false;
//    int rmbKeyCode = client.gameSettings.keyBindAttack.getKeyCode();

    private int playerYaw;
    private int playerPitch;

    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent e) {
        if(kokoMod.caneFarmHelperStatus){

//            //toggles the rmb, there is a time so it doesnt spam
//            if (Mouse.isButtonDown(0)) {
//                if (System.currentTimeMillis() - mouseToggleTimer > 500) {
//                    rmbToggled = !rmbToggled;
//                    if (rmbToggled) {
//                        preventMouseSpam = true;
//                    } else if (!rmbToggled) {
//                        preventMouseSpam = false;
//                    }
//                    mouseToggleTimer = System.currentTimeMillis();
//                }
//            }


            //shows a introducton first time using the module with a 7sec timer
            if(System.currentTimeMillis() - temptime < 7 * 1000){
                notifier.GuiNotif(client, "farm helper will lock your head postion on the right angle");
            }


            updatePitchAndYaw();

            //checks if the head is in a good place to lock
            headlockCondition = checkHeadCondition(playerPitch, playerYaw);

            //checks if lock is applicable and lock the head every tick
            if(headlockCondition){
                Mouse.getDX();
                Mouse.getDY();
                client.mouseHelper.deltaX = client.mouseHelper.deltaY = 0;
            }

//            //if rmbtoggle is toggled it permanently clicks the attack button
//            if(rmbToggled){
////                System.out.println("rmb is toggled idk");
////                KeyBinding.setKeyBindState(rmbKeyCode, true);
////                KeyBinding.onTick(rmbKeyCode);
//                if(preventMouseSpam){
//                    KeyBinding.setKeyBindState(client.gameSettings.keyBindAttack.getKeyCode(), true);
//                    KeyBinding.onTick(client.gameSettings.keyBindAttack.getKeyCode());
//                    preventMouseSpam = false;
//                    System.out.println("rmb is toggled idk");
//                }
//
//            }

        }
    }

    private void updatePitchAndYaw(){
        //gets the player yaw and pitch
        playerYaw = Math.round(client.thePlayer.rotationYaw);
        playerPitch = Math.round(client.thePlayer.rotationPitch);
    }

    private boolean checkHeadCondition(int playerPitch, int playerYaw){
        boolean temp = playerYaw % 45 == 0 && playerPitch == 0;
        return temp;
    }

    public void onEnable() {
        temptime = System.currentTimeMillis();
        //mouseToggleTimer = System.currentTimeMillis();
        MinecraftForge.EVENT_BUS.register(this);
        util.sendClientMessage("farm helper enabled", true);
    }
    public void onDisable() {
        headlockCondition = false;
        playerYaw = playerPitch = 0;
        util.sendClientMessage("farm helper disabled", true);
        //KeyBinding.setKeyBindState(rmbKeyCode, false);
        MinecraftForge.EVENT_BUS.unregister(this);

    }
    @SubscribeEvent
    public void onUnloadWorld(WorldEvent.Unload event) {
        MinecraftForge.EVENT_BUS.unregister(this);
        util.sendClientMessage("farm helper forcefully unloaded", true);
    }
}
