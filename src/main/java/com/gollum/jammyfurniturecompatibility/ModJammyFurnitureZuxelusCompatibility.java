package com.gollum.jammyfurniturecompatibility;

import com.gollum.core.common.i18n.I18n;
import com.gollum.core.common.log.Logger;
import com.gollum.core.common.mod.GollumMod;
import com.gollum.jammyfurniture.ModJammyFurniture;
import com.gollum.jammyfurniturecompatibility.common.CommonProxyJammyFurnitureZuxelusCompatibility;
import com.gollum.jammyfurniturecompatibility.common.config.ConfigJammyFunitureZuxelusCompatibility;
import com.gollum.jammyfurniturecompatibility.inits.ModBlocks;
import com.gollum.jammyfurniturecompatibility.inits.ModItems;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
	modid = ModJammyFurnitureZuxelusCompatibility.MODID,
	name = ModJammyFurnitureZuxelusCompatibility.MODNAME,
	version = ModJammyFurnitureZuxelusCompatibility.VERSION,
	acceptedMinecraftVersions = ModJammyFurnitureZuxelusCompatibility.MINECRAFT_VERSION,
	dependencies = ModJammyFurnitureZuxelusCompatibility.DEPENDENCIES
)
public class ModJammyFurnitureZuxelusCompatibility extends GollumMod {

	public final static String MODID = "JammyFurnitureZuxelusCompatibility";
	public final static String MODNAME = "Jammy Furniture Zuxelus compatibility";
	public final static String VERSION = "1.0.0";
	public final static String MINECRAFT_VERSION = "1.7.10";
	public final static String DEPENDENCIES = "required-after:"+ModJammyFurniture.MODID;
	
	public final static String ZUXELUS_MODID = "jammyfurniture";
	
	@Instance(ModJammyFurnitureZuxelusCompatibility.MODID)
	public static ModJammyFurnitureZuxelusCompatibility instance;
	
	@SidedProxy(
		clientSide = "com.gollum.jammyfurniturecompatibility.client.ClientProxyJammyFurnitureZuxelusCompatibility",
		serverSide = "com.gollum.jammyfurniturecompatibility.common.CommonProxyJammyFurnitureZuxelusCompatibility"
	)
	public static CommonProxyJammyFurnitureZuxelusCompatibility proxy;
	
	/**
	 * Gestion des logs
	 */
	public static Logger log;
	
	/**
	 * Gestion de l'i18n
	 */
	public static I18n i18n;
	
	/**
	 * La configuration
	 */
	public static ConfigJammyFunitureZuxelusCompatibility config;
	
	@EventHandler public void handler(FMLPreInitializationEvent event)  { super.handler (event); }
	@EventHandler public void handler(FMLInitializationEvent event)     { super.handler (event); }
	@EventHandler public void handler(FMLPostInitializationEvent event) { super.handler (event); }
	
	/** 1 */
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		
		// Initialisation des blocks
		ModBlocks.init ();
		
		// Initialisation des items
		ModItems.init ();
	}

	/** 2 **/
	public void init(FMLInitializationEvent event) {
		// Enregistre les events
		this.proxy.registerEvents();
	}
	
	/** 3 **/
	public void postInit(FMLPostInitializationEvent event) {
	}
	
}
