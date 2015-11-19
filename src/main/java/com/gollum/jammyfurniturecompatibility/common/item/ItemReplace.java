package com.gollum.jammyfurniturecompatibility.common.item;

import static com.gollum.jammyfurniturecompatibility.ModJammyFurnitureZuxelusCompatibility.log;

import java.lang.reflect.Field;
import java.util.HashMap;

import com.gollum.core.tools.helper.items.HItem;
import com.gollum.core.tools.registered.RegisteredObjects;
import com.gollum.jammyfurniturecompatibility.ModJammyFurnitureZuxelusCompatibility;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemReplace extends HItem {
	
	private String modId;
	private Item target;
	
	public static class ReplaceItem implements Cloneable {
		
		public ReplaceItem() {
		}
		
		public Object clone() {
			return new ReplaceItem();
		}
	}
	
	public ItemReplace(String modId, String registerName, Item target) {
		super(registerName);
		this.modId  = modId;
		this.target = target;
	}
	
	/**
	 * Enregistrement du item. Appelé a la fin du postInit
	 */
	public void register () {
		
		ModContainer mc = Loader.instance().activeModContainer();
		HashMap<String, Object> descriptor = new HashMap<String, Object>();
		try {
			Field f = mc.getClass().getDeclaredField("descriptor");
			f.setAccessible(true);
			descriptor = (HashMap<String, Object>) f.get(mc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		descriptor.put("modid", this.modId);
		GameRegistry.registerItem(this, getRegisterName ());
		descriptor.put("modid", ModJammyFurnitureZuxelusCompatibility.MODID);
	}
	
	public ItemStack replaceItemStack (ItemStack is) {
		int damage = is.getItemDamage();
		return new ItemStack(this.target, is.stackSize, damage);
	}
	
	private void trace () {
		this.trace(new ItemStack(this));
	}
	
	private void trace (ItemStack is) {
		ItemStack nIs = this.replaceItemStack(is);
		log.message("Item transform \""+RegisteredObjects.instance().getRegisterName(this)+"\":"+is.getItemDamage()+" => \""+RegisteredObjects.instance().getRegisterName(nIs.getItem())+"\":"+nIs.getItemDamage()+"");
	}
	
	@Override
	public void registerIcons(IIconRegister iconRegister) {
	}
	
	@Override
	public IIcon getIcon(ItemStack is, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		ItemStack nIs        = this.replaceItemStack(is);
		ItemStack nUsingItem = this.replaceItemStack(usingItem);
		return nIs.getItem().getIcon(is, renderPass, player, nUsingItem, useRemaining);
	}
	
	@Override
	public String getUnlocalizedName() {
		return this.target.getUnlocalizedName();
	}
	
	@Override
	public String getUnlocalizedName(ItemStack is) {
		ItemStack nIs = this.replaceItemStack(is);
		return nIs.getItem().getUnlocalizedName(nIs);
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer e, World w, int x, int y, int z, int side, float posX, float posY, float posZ) {
		ItemStack nIs = this.replaceItemStack(is);
		this.trace(is);
		return nIs.getItem().onItemUse(is, e, w, x, y, z, side, posX, posY, posZ);
	}
	
	@Override
	public void onUpdate(ItemStack is, World w, Entity e, int slot, boolean b) {
		
		ItemStack nIs = this.replaceItemStack(is);
		
		if (e instanceof EntityPlayer) {
			((EntityPlayer) e).inventory.setInventorySlotContents(slot, nIs);
			this.trace(is);
		}
		nIs.getItem().onUpdate(is, w, e, slot, b);
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack is) {
		ItemStack nIs = this.replaceItemStack(is);
		return nIs.getItem().getContainerItem(nIs);
	}
	
}
