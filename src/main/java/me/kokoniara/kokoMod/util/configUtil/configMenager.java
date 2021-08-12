package me.kokoniara.kokoMod.util.configUtil;

import me.kokoniara.kokoMod.kokoMod;
import me.kokoniara.kokoMod.settings.Setting;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;


import java.util.ArrayList;

import static me.kokoniara.kokoMod.main.config;

public class configMenager {
    private double rmbAutoClikerMin;
    private double rmbAutoClikerMax;

    public void saveConfig(){
        System.out.println("called save config");
        ArrayList<Setting> temp = kokoMod.instance.settingsManager.getSettings();
        System.out.println(temp);

            for (Setting s : temp){
                if(s.getName() == "RbmMinCPS"){
                    s.setValDouble(rmbAutoClikerMin);
                }
                if(s.getName() == "RbmMaxCPS"){
                    s.setValDouble(rmbAutoClikerMax);
                }
            }

            kokoMod.instance.settingsManager.putSettings(temp);


    }

//    public static boolean isPancakesEnabled = true;

    public void syncConfig() { // Gets called from preInit
        System.out.println("called sync config");
        try {
            // Load config
            config.load();

            // Read props from config
//            Property isPancakesEnabledProp = config.get(Configuration.CATEGORY_GENERAL, // What category will it be saved to, can be any string
//                    "isPancakesEnabled", // Property name
//                    "true", // Default value
//                    "Whether pancakes are enabled"); // Comment

            Property rmbAutoClikerMin = config.get(Configuration.CATEGORY_GENERAL,
                    "rmbAutoClikerMin",
                    6F,
                    "minium value of rbm autoclicker");

            this.rmbAutoClikerMin = rmbAutoClikerMin.getDouble();

            Property rmbAutoClikerMax = config.get(Configuration.CATEGORY_GENERAL,
                    "rmbAutoClikerMax",
                    12F,
                    "maxium value of rbm autoclicker");

            this.rmbAutoClikerMax = rmbAutoClikerMax.getDouble();


//            isPancakesEnabled = isPancakesEnabledProp.getBoolean(); // Get the boolean value, also set the property value to boolean
        } catch (Exception e) {
            // Failed reading/writing, just continue
        } finally {
            // Save props to config IF config changed
            if (config.hasChanged()) config.save();
            this.saveConfig();
        }
    }


}
