package me.kokoniara.kokoMod.settings;

import java.util.ArrayList;

import me.kokoniara.kokoMod.module.Module;
import me.kokoniara.kokoMod.util.configUtil.configMenager;


/**
 *  Made by HeroCode
 *  it's free to use
 *  but you have to credit him
 *
 *  @author HeroCode
 */
public class SettingsManager {
	
	private ArrayList<Setting> settings;

	private configMenager configMenager = new configMenager();
	
	public SettingsManager(){
		this.settings = new ArrayList<Setting>();
	}
	
	public void rSetting(Setting in){
		this.settings.add(in);
	}
	
	public ArrayList<Setting> getSettings(){
		return this.settings;
	}

	public void putSettings(ArrayList<Setting> a){
		settings = a;
	}

	public void settingsSaveToConfigCall(){
		configMenager.syncConfig();
	}
	
	public ArrayList<Setting> getSettingsByMod(Module mod){
		ArrayList<Setting> out = new ArrayList<Setting>();
		for(Setting s : getSettings()){
			if(s.getParentMod().equals(mod)){
				out.add(s);
			}
		}
		if(out.isEmpty()){
			return null;
		}
		return out;
	}
	
	public Setting getSettingByName(String name){
		configMenager.syncConfig();
		for(Setting set : getSettings()){
			if(set.getName().equalsIgnoreCase(name)){
				return set;
			}
		}
		System.err.println("[Tutorial] Error Setting NOT found: '" + name +"'!");
		return null;
	}



}