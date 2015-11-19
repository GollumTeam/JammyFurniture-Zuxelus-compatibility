package com.gollum.jammyfurniturecompatibility.common.item;

import static com.gollum.jammyfurniturecompatibility.ModJammyFurnitureZuxelusCompatibility.log;

import com.gollum.core.tools.registered.RegisteredObjects;
import com.gollum.jammyfurniturecompatibility.common.block.BlockReplace;
import com.gollum.jammyfurniturecompatibility.common.block.BlockReplace.ReplaceBlock;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemBlockReplace extends ItemBlock {
	
	BlockReplace block;
	
	public ItemBlockReplace(Block block) {
		super(block);
		this.block = (BlockReplace) block;
	}
	
	private ReplaceBlock getTarget () {
		return this.getTarget(0);
	}
	private ReplaceBlock getTarget (int damage) {
		return ReplaceBlock.get(this.block, damage);
	}
	public ItemStack replaceItemStack (ItemStack is) {
		int metadata = is.getItemDamage();
		ReplaceBlock replaceBlock = ReplaceBlock.get(this.block, metadata);
		return new ItemStack(replaceBlock.getBlock(), is.stackSize, replaceBlock.getMetadata(metadata));
	}
	
	private void trace () {
		this.trace(new ItemStack(this));
	}
	private void trace (ItemStack is) {
		ItemStack nIs = this.replaceItemStack(is);
		log.message("Block transform \""+RegisteredObjects.instance().getRegisterName(this)+"\":"+is.getItemDamage()+" => \""+RegisteredObjects.instance().getRegisterName(nIs.getItem())+"\":"+nIs.getItemDamage()+"");
	}
	
	@Override
	public IIcon getIcon(ItemStack is, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		ItemStack nIs        = this.replaceItemStack(is);
		ItemStack nUsingItem = this.replaceItemStack(usingItem);
		return nIs.getItem().getIcon(is, renderPass, player, nUsingItem, useRemaining);
	}
	
	@Override
	public String getUnlocalizedName() {
		return this.getTarget().getItem().getUnlocalizedName();
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
