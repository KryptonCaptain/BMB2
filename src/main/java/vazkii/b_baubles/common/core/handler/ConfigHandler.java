/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 13, 2014, 9:01:32 PM (GMT)]
 */
package vazkii.b_baubles.common.core.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import vazkii.b_baubles.api.BotaniaAPI;
import vazkii.b_baubles.common.Botania;
import vazkii.b_baubles.common.lib.LibMisc;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public final class ConfigHandler {

	public static Configuration config;
	
	public static boolean useVanillaParticleLimiter = true;

	public static boolean renderBaubles = true;

	public static int manaBarHeight = 29;
	public static int glSecondaryTextureUnit = 7;

	public static boolean invertMagnetRing = false;

    public static boolean flugelTiaraNerfDisabled = false;
    public static int flugelTiaraMaxFlyTime = 800;

    public static void loadConfig(File configFile) {
		config = new Configuration(configFile);

		config.load();
		load();

		FMLCommonHandler.instance().bus().register(new ChangeListener());
	}

	public static void load() {
		String desc;

		desc = "Set this to false to always display all particles regardless of the \"Particles\" setting in the Vanilla options menu.";
		useVanillaParticleLimiter = loadPropBool("vanillaParticleConfig.enabled", desc, useVanillaParticleLimiter);

		desc = "Set this to false to disable rendering of baubles in the player.";
		renderBaubles = loadPropBool("baubleRender.enabled", desc, renderBaubles);

		desc = "Set this to true to invert the Ring of Magnetization's controls (from shift to stop to shift to work)";
		invertMagnetRing = loadPropBool("magnetRing.invert", desc, invertMagnetRing);
		
        desc = "Set this to true to disable flugel tiara infinite flight";
        flugelTiaraNerfDisabled = loadPropBool("flugeltiaranerf.enabled", desc, flugelTiaraNerfDisabled);

        desc = "Change the value of this to set the max fly time in ticks of the flugel tiara";
        flugelTiaraMaxFlyTime = loadPropInt("flugeltiara.maxflytime", desc, flugelTiaraMaxFlyTime);


		if(config.hasChanged())
			config.save();
	}

	public static void loadPostInit() {

		if(config.hasChanged())
			config.save();
	}

	public static int loadPropInt(String propName, String desc, int default_) {
		Property prop = config.get(Configuration.CATEGORY_GENERAL, propName, default_);
		prop.comment = desc;
		
		
		return prop.getInt(default_);
	}

	public static double loadPropDouble(String propName, String desc, double default_) {
		Property prop = config.get(Configuration.CATEGORY_GENERAL, propName, default_);
		prop.comment = desc;
		
		
		return prop.getDouble(default_);
	}

	public static boolean loadPropBool(String propName, String desc, boolean default_) {
		Property prop = config.get(Configuration.CATEGORY_GENERAL, propName, default_);
		prop.comment = desc;
		
		
		return prop.getBoolean(default_);
	}


	public static class ConfigAdaptor {
		
		private boolean enabled;
		private int lastBuild;
		private int currentBuild; 
		
		private Map<String, List<AdaptableValue>> adaptableValues = new HashMap();
		private List<String> changes = new ArrayList();
		
		public ConfigAdaptor(boolean enabled) {
			this.enabled = enabled;
			
			String lastVersion = Botania.proxy.getLastVersion();
			try {
				lastBuild = Integer.parseInt(lastVersion);
				currentBuild = Integer.parseInt(LibMisc.BUILD);
			} catch(NumberFormatException e) {
				this.enabled = false;
			}
		}
		
		public <T> void adaptProperty(Property prop, T val) {
			if(!enabled)
				return;
			
			String name = prop.getName();

			if(!adaptableValues.containsKey(name))
				return;
			
			AdaptableValue<T> bestValue = null;
			for(AdaptableValue<T> value : adaptableValues.get(name)) {
				if(value.version >= lastBuild) // If version is newer than what we last used we don't care about it
					continue;
				
				if(bestValue == null || value.version > bestValue.version)
					bestValue = value;
			}
			
			if(bestValue != null) {
				T expected = bestValue.value;
				if(val.getClass() == Double.class || val.getClass() == Float.class) {
					double epsilon = 1.0E-6;
					float valF = ((Number) val).floatValue();
					float expectedF = ((Number) expected).floatValue();
					
					if(Math.abs(valF - expectedF) < epsilon) {
						prop.setValue(prop.getDefault());
						changes.add(" " + prop.getName() + ": " + val + " -> " + prop.getDefault());
					}
				} else if(val == expected) {
					prop.setValue(prop.getDefault());
					changes.add(" " + prop.getName() + ": " + val + " -> " + prop.getDefault());
				}
			}
		}
		
		public <T> void addMapping(int version, String key, T val) {
			if(!enabled)
				return;
			
			AdaptableValue<T> adapt = new AdaptableValue<T>(version, val);
			if(!adaptableValues.containsKey(key)) {
				ArrayList list = new ArrayList();
				adaptableValues.put(key, list);
			}

			List<AdaptableValue> list = adaptableValues.get(key);
			list.add(adapt);
		}
		
		public void tellChanges(EntityPlayer player) {
			if(changes.size() == 0)
				return;
			
			player.addChatComponentMessage(new ChatComponentTranslation("botaniamisc.adaptativeConfigChanges").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD)));
			for(String change : changes)
				player.addChatMessage(new ChatComponentText(change).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.LIGHT_PURPLE)));
		}
		
		public void addMappingInt(int version, String key, int val) {
			this.<Integer>addMapping(version, key, val);
		}
		
		public void addMappingDouble(int version, String key, double val) {
			this.<Double>addMapping(version, key, val);
		}
		
		public void addMappingBool(int version, String key, boolean val) {
			this.<Boolean>addMapping(version, key, val);
		}
		
		public void adaptPropertyInt(Property prop, int val) {
			this.<Integer>adaptProperty(prop, val);
		}
		
		public void adaptPropertyDouble(Property prop, double val) {
			this.<Double>adaptProperty(prop, val);
		}

		public void adaptPropertyBool(Property prop, boolean val) {
			this.<Boolean>adaptProperty(prop, val);
		}
		
		public static class AdaptableValue<T> {
			
			public final int version;
			public final T value;
			public final Class<? extends T> valueType;
			
			public AdaptableValue(int version, T value) {
				this.version = version;
				this.value = value;
				valueType = (Class<? extends T>) value.getClass();
			}
			
		}
		
	}
	
	public static class ChangeListener {

		@SubscribeEvent
		public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
			if(eventArgs.modID.equals(LibMisc.MOD_ID))
				load();
		}

	}
}
