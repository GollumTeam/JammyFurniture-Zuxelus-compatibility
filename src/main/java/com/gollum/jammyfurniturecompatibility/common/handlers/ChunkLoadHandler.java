package com.gollum.jammyfurniturecompatibility.common.handlers;

import static com.gollum.jammyfurniturecompatibility.ModJammyFurnitureZuxelusCompatibility.log;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import com.gollum.core.tools.registered.RegisteredObjects;
import com.gollum.jammyfurniturecompatibility.common.block.BlockReplace;
import com.gollum.jammyfurniturecompatibility.common.block.BlockReplace.ReplaceBlock;
import com.gollum.jammyfurniturecompatibility.common.item.ItemBlockReplace;
import com.gollum.jammyfurniturecompatibility.common.item.ItemReplace;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.event.world.ChunkDataEvent.Load;
import net.minecraftforge.event.world.ChunkDataEvent.Save;
import net.minecraftforge.event.world.WorldEvent;;

public class ChunkLoadHandler {
	
	public static final String KEY_CHECK_SAVE_CHUNK = "JammyZuxelusCompatibility";
	
	private static TreeMap<Integer, TreeSet<String>> running = new TreeMap<Integer, TreeSet <String>> ();
	private static TreeMap<Integer, TreeSet<String>> parsed = new TreeMap<Integer, TreeSet<String>> ();

	@SubscribeEvent
	public void onWorldLoad (WorldEvent.Load event) {
		if (event.world.isRemote) {
			return;
		}
		int wId = System.identityHashCode(event.world);
		if (running.containsKey(wId)) {
			running.remove(wId);
		}
		running.put(wId, new TreeSet<String>());
		
		if (parsed.containsKey(wId)) {
			parsed.remove(wId);
		}
		parsed.put(wId, new TreeSet<String>());
	}
	
	@SubscribeEvent
	public void onLoad (Load event) {
		
		Chunk c = event.getChunk();
		NBTTagCompound d = event.getData();
		
		if (c.worldObj.isRemote) {
			return;
		}
		
		try {
			if (
				(
					!d.hasKey(KEY_CHECK_SAVE_CHUNK) || 
					!d.getBoolean(KEY_CHECK_SAVE_CHUNK)
				) &&
				!this.isParsed (c) &&
				!this.isRunning(c)
			) {	
				log.debug("Migrate chunk start :", c.xPosition, c.zPosition);
				this.markRunning (c);
				this.migrate(c);
				this.markParsed (c);
				log.debug("Migrate chunk end :", c.xPosition, c.zPosition);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!this.isRunning(c)) {
			this.markParsed (c);
		}
	}
	
	public static boolean isParsed(Chunk c) {
		int wId = System.identityHashCode(c.worldObj);
		return parsed.containsKey(wId) && parsed.get(wId).contains(getChunckKey(c));
	}
	
	private static void markParsed(Chunk c) {
		int wId = System.identityHashCode(c.worldObj);
		if (!parsed.containsKey(wId)) {
			parsed.put(wId, new TreeSet<String>());
		}
		if (!isParsed(c)) {
			parsed.get(wId).add(getChunckKey(c));
		}
	}
	
	public static boolean isRunning(Chunk c) {
		int wId = System.identityHashCode(c.worldObj);
		return running.containsKey(wId) && running.get(wId).contains(getChunckKey(c));
	}
	
	private static void markRunning(Chunk c) {
		int wId = System.identityHashCode(c.worldObj);
		if (!running.containsKey(wId)) {
			running.put(wId, new TreeSet<String>());
		}
		if (!isRunning(c)) {
			running.get(wId).add(getChunckKey(c));
		}
	}
	
	private static String getChunckKey(Chunk c) {
		return c.xPosition+"x"+c.zPosition;
	}
	
	private void migrate(Chunk chunk) {
		ExtendedBlockStorage[] storages = chunk.getBlockStorageArray();
		for (int y = 0; y >> 4 < storages.length; y++) {
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					Block       b = chunk.getBlock(x, y, z);
					int         m = chunk.getBlockMetadata(x, y, z);
					TileEntity te = chunk.getTileEntityUnsafe(x, y, z);
					
					if (b instanceof BlockReplace) {
						this.replaceBlock(chunk, x, y, z, (BlockReplace)b, m);
					}
					
					if (te instanceof IInventory) {
						IInventory inv = (IInventory)te;
						for (int i = 0; i < inv.getSizeInventory(); i++) {
							ItemStack is = inv.getStackInSlot(i);
							if (is != null && (is.getItem() instanceof ItemReplace)) {
								ItemStack nIs = ((ItemReplace)is.getItem()).replaceItemStack(is);
								inv.setInventorySlotContents(i, nIs);
								log.message("Handler item in inventory transform x="+(x*chunk.xPosition*16)+", y="+y+", z="+(z*chunk.zPosition*16)+" ,\""+RegisteredObjects.instance().getRegisterName(is.getItem())+"\":"+is.getItemDamage()+" => \""+RegisteredObjects.instance().getRegisterName(nIs.getItem())+"\":"+nIs.getItemDamage()+"");
							}
							if (is != null && (is.getItem() instanceof ItemBlockReplace)) {
								ItemStack nIs = ((ItemBlockReplace)is.getItem()).replaceItemStack(is);
								inv.setInventorySlotContents(i, nIs);
								log.message("Handler item in inventory transform x="+(x*chunk.xPosition*16)+", y="+y+", z="+(z*chunk.zPosition*16)+" ,\""+RegisteredObjects.instance().getRegisterName(is.getItem())+"\":"+is.getItemDamage()+" => \""+RegisteredObjects.instance().getRegisterName(nIs.getItem())+"\":"+nIs.getItemDamage()+"");
							}
						}
					}
				}
			}
		}
	}
	
	private void replaceBlock(Chunk chunk, int x, int y, int z, BlockReplace block, int metadata) {
		
		ReplaceBlock target = ReplaceBlock.get(block, metadata);
		TileEntity te = chunk.func_150806_e(x, y, z);
		ItemStack[] itemStacks = new ItemStack[0];
		
		try {
			
			if (te instanceof IInventory) {
				
				IInventory inventory = (IInventory) te;
				
				itemStacks = new ItemStack[inventory.getSizeInventory()];
				for (int i = 0; i < inventory.getSizeInventory(); i++) {
					itemStacks[i] = inventory.getStackInSlot(i);
					inventory.setInventorySlotContents(i, null);
				}
			}
			
			chunk.func_150812_a(x, y, z, te);
			chunk.removeTileEntity(x, y, z);
			chunk.func_150807_a(x, y, z, target.getBlock(), target.getMetadata(metadata));
			
			log.message("Handler block transform x="+(x+chunk.xPosition*16)+", y="+y+", z="+(z+chunk.zPosition*16)+" ,\""+RegisteredObjects.instance().getRegisterName(block)+"\":"+metadata+"("+Block.getIdFromBlock(block)+") => \""+target.registerName+"\":"+target.getMetadata(metadata)+"("+Block.getIdFromBlock(target.getBlock())+")");
			
			te = chunk.func_150806_e(x, y, z);
			if (te instanceof IInventory) {
				
				IInventory inventory = (IInventory) te;
				
				for (int i = 0; i < inventory.getSizeInventory() && i < itemStacks.length; i++) {
					inventory.setInventorySlotContents(i, itemStacks[i]);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@SubscribeEvent
	public void onSave (Save event) {
		if (event.getChunk().worldObj.isRemote) {
			return;
		}
		NBTTagCompound d = event.getData();
		d.setBoolean(KEY_CHECK_SAVE_CHUNK, true);
	}
}
