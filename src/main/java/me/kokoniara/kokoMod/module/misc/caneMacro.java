package me.kokoniara.kokoMod.module.misc;

import me.kokoniara.kokoMod.module.Category;
import me.kokoniara.kokoMod.module.Module;
import me.kokoniara.kokoMod.renderEngine.modules.drawCenterString;
import me.kokoniara.kokoMod.util.KeyboardLocker;
import me.kokoniara.kokoMod.util.sendChatMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import scala.concurrent.TaskRunner;

public class caneMacro extends Module {
    private drawCenterString notifier = new drawCenterString();
    KeyboardLocker keyboardLocker = KeyboardLocker.getKeyboardLocker();
    private boolean ismacroingReady;
    private macroStages macroWalkStage = macroStages.DEFAULT;
    EntityPlayerSP player;

    private long playerSpeedCheckTimer;

    private int playerYaw;
    private int playerPitch;
    private double playerSpeed;
    private long distanceWalked;
    private static KeyBinding attackKey;
    //private EntityPlayerSP player = mc.thePlayer;

    public caneMacro(){
        super("cane macro", "macros cane!", Category.MISC, true," cane macro enabed"," cane macro disabled");
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.RenderTickEvent event) {
        if( mc == null || mc.theWorld == null || mc.thePlayer == null ) return;

        player = mc.thePlayer;

        playerSpeed = player.getDistance(player.lastTickPosX, player.lastTickPosY, player.lastTickPosZ);
        distanceWalked = distanceWalked + (long)playerSpeed;

        if(!ismacroingReady){
            notifier.GuiNotif(mc, "macro will start when you lock your head postion on the right angle");
            //updatePitchAndYaw();
            playerYaw = Math.round(mc.thePlayer.rotationYaw);
            playerPitch = Math.round(mc.thePlayer.rotationPitch);
            boolean temp = playerYaw % 45 == 0 && playerPitch == 0;
            ismacroingReady = temp;
            if(temp) macroWalkStage = macroStages.HOLD;

            //headlockCondition = checkHeadCondition(playerPitch, playerYaw);
        }else{
            Mouse.getDX();
            Mouse.getDY();
            mc.mouseHelper.deltaX = mc.mouseHelper.deltaY = 0;
            keyboardLocker.lockKeyboard();

            if(macroWalkStage == macroStages.HOLD) macroWalkStage = macroStages.RIGHT;
            notifier.GuiNotif(mc, "macroing ur life away!");
            if(System.currentTimeMillis() - this.playerSpeedCheckTimer > 500){
                this.playerSpeedCheckTimer = System.currentTimeMillis();
                System.out.println("every 500 ms ???");
                if(playerSpeed <= 0.1F){
                    if(macroWalkStage == macroStages.RIGHT){
                        macroWalkStage = macroStages.BOTTOM;
                    }else if(macroWalkStage == macroStages.BOTTOM){
                        macroWalkStage = macroStages.RIGHT;
                    }
                }
            }


            KeyBinding.setKeyBindState(mc.gameSettings.keyBindAttack.getKeyCode(), true);

            if(macroWalkStage == macroStages.RIGHT){
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindBack.getKeyCode(), false);
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindRight.getKeyCode(), true);
            }
            if(macroWalkStage == macroStages.BOTTOM){
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindRight.getKeyCode(), false);
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindBack.getKeyCode(), true);
            }
        }




    }
    @Override
    public void onEnable() {
        super.onEnable();
        this.playerSpeedCheckTimer = System.currentTimeMillis();
    }

    @Override
    public void onDisable(){
        super.onDisable();
        keyboardLocker.unlockKeyboard();
        ismacroingReady = false;
        macroWalkStage = macroStages.DEFAULT;
        playerYaw = 0;
        playerPitch = 0;
        playerSpeed = 0;
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindBack.getKeyCode(), false);
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindAttack.getKeyCode(), false);
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindRight.getKeyCode(), false);
    }
    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent e){
        if(macroWalkStage != macroStages.DEFAULT){
            e.setCanceled(true);
        }
    }


    @SubscribeEvent
    public void onUnloadWorld(WorldEvent.Unload event) {
        super.setToggled(false);
        sendChatMessage.sendClientMessage(" cane macro was unloaded because you switched worlds", true);
    }
}
