package me.kokoniara.kokoMod.module.player;

import me.kokoniara.kokoMod.kokoMod;
import me.kokoniara.kokoMod.module.Category;
import me.kokoniara.kokoMod.module.Module;
import me.kokoniara.kokoMod.settings.Setting;
import me.kokoniara.kokoMod.util.sendChatMessage;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class iWillcancelYouOnTwitter extends Module {
    private long lastSpam;
    private double speed;
    public iWillcancelYouOnTwitter(){
        super("twitterWhiteGirls", "white twitter girls are the downfal on sociaty", Category.PLAYER);
        kokoMod.instance.settingsManager.rSetting(new Setting("cancel speed", this, 12, 1, 20, false));

    }

    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent e){
        if(System.currentTimeMillis() - lastSpam > speed * 100){
            sendChatMessage.sendClientMessage("ur canceled !", false);
            lastSpam = System.currentTimeMillis();
        }
    }

    @Override
    public void onEnable(){
        super.onEnable();
        speed = kokoMod.instance.settingsManager.getSettingByName(this, "cancel speed").getValDouble();
        lastSpam = System.currentTimeMillis();
        sendChatMessage.sendClientMessage("iwillcancelyou enabled", true);
    }

    @Override
    public void onDisable(){
        super.onDisable();
        sendChatMessage.sendClientMessage("iwillcancelyou disabled", true);
    }
}
