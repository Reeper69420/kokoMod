package me.kokoniara.kokoMod.modules;

import me.kokoniara.kokoMod.kokoMod;
import me.kokoniara.kokoMod.helpers.util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class Keybinds {
    private Minecraft client = Minecraft.getMinecraft();

    //making the keybind objects
    public static KeyBinding autoclicker = new KeyBinding("autoclicker toggle", Keyboard.KEY_H, "kokomods");
    public static KeyBinding batEspkeybind = new KeyBinding("bat esp toggle", Keyboard.KEY_V, "kokomods");
    public static KeyBinding toggleSprintKeybind = new KeyBinding("togglesprint", Keyboard.KEY_J, "kokomods");
    public static KeyBinding caneFarmHelper = new KeyBinding("cane farming helper", Keyboard.KEY_8, "kokomods");

    //registering the keybind objects
    public static void registerKeybinds() {
        ClientRegistry.registerKeyBinding(batEspkeybind);
        ClientRegistry.registerKeyBinding(autoclicker);
        ClientRegistry.registerKeyBinding(toggleSprintKeybind);
        ClientRegistry.registerKeyBinding(caneFarmHelper);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {

        if (Keybinds.batEspkeybind.isPressed()) {
            util.sendClientMessage(" batEsp toggled!", true);
            kokoMod.batEspToggle = !kokoMod.batEspToggle;
        }

        if (Keybinds.toggleSprintKeybind.isPressed()) {
            kokoMod.toggleSprintStatus = !kokoMod.toggleSprintStatus;
            if(kokoMod.toggleSprintStatus){
                kokoMod.toggleSprintinstance.onEnable();
            }else if(!kokoMod.toggleSprintStatus){
                kokoMod.toggleSprintinstance.onDisable();
            }
        }

        if (Keybinds.autoclicker.isPressed()) {
            kokoMod.autoClikerStatus = !kokoMod.autoClikerStatus;
            if(kokoMod.autoClikerStatus){
                kokoMod.autoClikerinstance.onEnable();
            }else if(!kokoMod.autoClikerStatus){
                kokoMod.autoClikerinstance.onDisable();
            }
        }

        if(Keybinds.caneFarmHelper.isPressed()){
            kokoMod.caneFarmHelperStatus = !kokoMod.caneFarmHelperStatus;
            if(kokoMod.caneFarmHelperStatus){
                kokoMod.farmReadyinstance.onEnable();
            }else if(!kokoMod.caneFarmHelperStatus){
                kokoMod.farmReadyinstance.onDisable();
            }
        }
    }

}