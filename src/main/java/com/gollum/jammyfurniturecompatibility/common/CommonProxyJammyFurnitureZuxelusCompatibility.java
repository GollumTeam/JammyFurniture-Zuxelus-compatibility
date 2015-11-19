package com.gollum.jammyfurniturecompatibility.common;

import com.gollum.core.common.handlers.WorldHandler;
import com.gollum.core.common.handlers.WorldTickHandler;
import com.gollum.jammyfurniturecompatibility.common.handlers.ChunkLoadHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxyJammyFurnitureZuxelusCompatibility {

	public void registerEvents () {
		MinecraftForge.EVENT_BUS.register(new ChunkLoadHandler());
	}
}