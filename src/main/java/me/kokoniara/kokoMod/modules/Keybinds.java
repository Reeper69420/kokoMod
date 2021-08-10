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
    private static KeyBinding autoclicker = new KeyBinding("autoclicker toggle", Keyboard.KEY_H, "kokomods");
    private static KeyBinding batEspkeybind = new KeyBinding("bat esp toggle", Keyboard.KEY_V, "kokomods");
    private static KeyBinding toggleSprintKeybind = new KeyBinding("togglesprint", Keyboard.KEY_J, "kokomods");
    private static KeyBinding caneFarmHelper = new KeyBinding("cane farming helper", Keyboard.KEY_8, "kokomods");



    //registering the keybind objects
    public Keybinds() {
        ClientRegistry.registerKeyBinding(batEspkeybind);
        ClientRegistry.registerKeyBinding(autoclicker);
        ClientRegistry.registerKeyBinding(toggleSprintKeybind);
        ClientRegistry.registerKeyBinding(caneFarmHelper);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {

        if (batEspkeybind.isPressed()) {
            kokoMod.objectsi.batEspinstance.toggled = !kokoMod.objectsi.batEspinstance.toggled;
            if(kokoMod.objectsi.batEspinstance.toggled){
                kokoMod.objectsi.batEspinstance.onEnable();
            }else {
                kokoMod.objectsi.batEspinstance.onDisable();
            }

        }



        if (toggleSprintKeybind.isPressed()) {
            kokoMod.objectsi.toggleSprintinstance.toggled = !kokoMod.objectsi.toggleSprintinstance.toggled;
            if(kokoMod.objectsi.toggleSprintinstance.toggled){
                kokoMod.objectsi.toggleSprintinstance.onEnable();
            }else if(!kokoMod.objectsi.toggleSprintinstance.toggled){
                kokoMod.objectsi.toggleSprintinstance.onDisable();
            }
        }

        if (autoclicker.isPressed()) {
            kokoMod.objectsi.autoClikerinstance.toggled = !kokoMod.objectsi.autoClikerinstance.toggled;
            if(kokoMod.objectsi.autoClikerinstance.toggled){
                kokoMod.objectsi.autoClikerinstance.onEnable();
            }else if(!kokoMod.objectsi.autoClikerinstance.toggled){
                kokoMod.objectsi.autoClikerinstance.onDisable();
            }
        }



        if(caneFarmHelper.isPressed()){
            kokoMod.objectsi.farmReadyinstance.toggled = !kokoMod.objectsi.farmReadyinstance.toggled;
            if(kokoMod.objectsi.farmReadyinstance.toggled){
                kokoMod.objectsi.farmReadyinstance.onEnable();
            }else if(!kokoMod.objectsi.farmReadyinstance.toggled){
                kokoMod.objectsi.farmReadyinstance.onDisable();
            }
        }
    }

}