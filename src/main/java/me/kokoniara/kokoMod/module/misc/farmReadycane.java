package me.kokoniara.kokoMod.module.misc;

import me.kokoniara.kokoMod.kokoMod;
import me.kokoniara.kokoMod.settings.Setting;
import me.kokoniara.kokoMod.util.GuiNotif;
import me.kokoniara.kokoMod.module.Category;
import me.kokoniara.kokoMod.module.Module;
import me.kokoniara.kokoMod.util.sendChatMessage;

import net.minecraft.client.Minecraft;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import org.lwjgl.input.Mouse;

public class farmReadycane extends Module {

    public farmReadycane(){
        super("farmReady", "gets you ready to farm cane", Category.MISC);
        kokoMod.instance.settingsManager.rSetting(new Setting("unload on world change", this , true));
    }
    private Minecraft client = Minecraft.getMinecraft();
    public boolean toggled;

    GuiNotif notifier = new GuiNotif();
    private long temptime;


    private boolean headlockCondition = false;

    private int playerYaw;
    private int playerPitch;

    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent e) {
        if( mc == null || mc.theWorld == null || mc.thePlayer == null ){
            return;
        }

            if(System.currentTimeMillis() - temptime < 7 * 1000){
                notifier.GuiNotif(client, "farm helper will lock your head postion on the right angle");
            }


            updatePitchAndYaw();

            headlockCondition = checkHeadCondition(playerPitch, playerYaw);

            if(headlockCondition){
                Mouse.getDX();
                Mouse.getDY();
                client.mouseHelper.deltaX = client.mouseHelper.deltaY = 0;
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

    @Override
    public void onEnable() {
        super.onEnable();
        temptime = System.currentTimeMillis();
        //mouseToggleTimer = System.currentTimeMillis();
        sendChatMessage.sendClientMessage(" farmReady-cane enabled", true);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        headlockCondition = false;
        playerYaw = playerPitch = 0;
        sendChatMessage.sendClientMessage(" farmReady-cane disabled", true);
        //KeyBinding.setKeyBindState(rmbKeyCode, false);
    }
    @SubscribeEvent
    public void onUnloadWorld(WorldEvent.Unload event) {
        if(kokoMod.instance.settingsManager.getSettingByName(this, "farmready cane unload on world change").getValBoolean()){
            super.setToggled(false);
            sendChatMessage.sendClientMessage(" farmReady-cane was unloaded because you switched worlds", true);
        }
    }
}
