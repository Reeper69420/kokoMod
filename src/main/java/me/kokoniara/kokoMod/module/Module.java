package me.kokoniara.kokoMod.module;

import me.kokoniara.kokoMod.config.configObject;
import me.kokoniara.kokoMod.kokoMod;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import static me.kokoniara.kokoMod.main.config;

import java.util.UUID;

import static me.kokoniara.kokoMod.config.confgValueType.KEYBIND;

public class Module {
	final String uuid = UUID.randomUUID().toString().replace("-", "");

	protected static Minecraft mc = Minecraft.getMinecraft();
	
	private String name, description;
	private int key;


	private Category category;
	private boolean toggled;
	public boolean visible = true;
	private boolean keybindEnabled = false;
	private Property keybindProp;
	
	public Module(String name, String description, Category category,boolean keybindEnabled) {
		super();
		this.name = name;
		this.description = description;
		this.key = key;
		this.keybindEnabled = keybindEnabled;
		if(keybindEnabled){
			try{
				config.load();
				keybindProp = config.get(Configuration.CATEGORY_GENERAL,
						this.name + "-keybind",
						0);
				this.key = keybindProp.getInt();
				config.save();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		this.category = category;
		this.toggled = false;

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getKey() {
		return this.key;
	}

	public void setKey(int key) {
		this.key = key;
		if(keybindEnabled){
			config.getCategory(Configuration.CATEGORY_GENERAL).get(this.name + "-keybind").set(key);
			config.save();
		}
	}

	public boolean isToggled() {
		return toggled;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
		
		if (this.toggled) {
			this.onEnable();
		} else {
			this.onDisable();
		}
	}
	
	public void toggle() {
		this.toggled = !this.toggled;
		
		if (this.toggled) {
			this.onEnable();
		} else {
			this.onDisable();
		}
	}
	
	public void onEnable() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	public void onDisable() {
		MinecraftForge.EVENT_BUS.unregister(this);
	}
	
	public String getName() {
		return this.name;
	}
	
	public Category getCategory() {
		return this.category;
	}
}
