package com.gollum.jammyfurniturecompatibility.inits;

import static com.gollum.jammyfurniturecompatibility.ModJammyFurnitureZuxelusCompatibility.ZUXELUS_MODID;
import com.gollum.jammyfurniturecompatibility.common.block.BlockReplace;

public class ModBlocks {
	
	/////////////////////
	// Liste des blocs //
	/////////////////////
	
	public static BlockReplace blockWoodBlocksOne;
	public static BlockReplace blockWoodBlocksTwo;
	public static BlockReplace blockWoodBlocksThree;
	public static BlockReplace blockBathTub;
	public static BlockReplace blockIronBlocksOne;
	public static BlockReplace blockIronBlocksTwo;
	public static BlockReplace blockCeramicBlocksOne;
	public static BlockReplace blockRoofingBlocksOne;
	public static BlockReplace blockMobHeadsOne;
	public static BlockReplace blockMobHeadsTwo;
	public static BlockReplace blockMobHeadsThree;
	public static BlockReplace blockMobHeadsFour;
	public static BlockReplace blockArmChair;
	public static BlockReplace blockSofaPartLeft;
	public static BlockReplace blockSofaPartRight;
	public static BlockReplace blockSofaPartCenter;
	public static BlockReplace blockSofaPartCorner;
	public static BlockReplace blockMiscBlocksOne;
	public static BlockReplace blockLightsOn;
	public static BlockReplace blockLightsOff;
	
	public static void init() {
		
		ModBlocks.blockBathTub          = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "bathBlock"       , com.gollum.jammyfurniture.inits.ModBlocks.blockBathTub         ).setCreativeTab(null);
		ModBlocks.blockLightsOn         = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "lightsOn"        , com.gollum.jammyfurniture.inits.ModBlocks.blockLightsOn        ).setCreativeTab(null);
		ModBlocks.blockLightsOff        = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "lightsOff"       , com.gollum.jammyfurniture.inits.ModBlocks.blockLightsOff       ).setCreativeTab(null);
		ModBlocks.blockWoodBlocksOne    = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "woodBlocks"      , com.gollum.jammyfurniture.inits.ModBlocks.blockWoodBlocksOne   ).setCreativeTab(null);
		ModBlocks.blockWoodBlocksTwo    = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "woodBlocksTwo"   , com.gollum.jammyfurniture.inits.ModBlocks.blockWoodBlocksTwo   ).setCreativeTab(null);
		ModBlocks.blockWoodBlocksThree  = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "woodBlocksThree" , com.gollum.jammyfurniture.inits.ModBlocks.blockWoodBlocksThree ).setCreativeTab(null);
		ModBlocks.blockIronBlocksOne    = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "ironBlockOne"    , com.gollum.jammyfurniture.inits.ModBlocks.blockIronBlocksOne   ).setCreativeTab(null);
		ModBlocks.blockIronBlocksTwo    = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "ironBlocksTwo"   , com.gollum.jammyfurniture.inits.ModBlocks.blockIronBlocksTwo   ).setCreativeTab(null);
		ModBlocks.blockCeramicBlocksOne = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "ceramicBlockOne" , com.gollum.jammyfurniture.inits.ModBlocks.blockCeramicBlocksOne).setCreativeTab(null);
		ModBlocks.blockRoofingBlocksOne = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "roofingBlocksOne", com.gollum.jammyfurniture.inits.ModBlocks.blockRoofingBlocksOne).setCreativeTab(null);
		ModBlocks.blockMiscBlocksOne    = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "miscOne"         , com.gollum.jammyfurniture.inits.ModBlocks.blockMiscBlocksOne   ).setCreativeTab(null);
		ModBlocks.blockMobHeadsOne      = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "MobHeadsOne"     , com.gollum.jammyfurniture.inits.ModBlocks.blockMobHeadsOne     ).setCreativeTab(null);
		ModBlocks.blockMobHeadsTwo      = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "MobHeadsTwo"     , com.gollum.jammyfurniture.inits.ModBlocks.blockMobHeadsTwo     ).setCreativeTab(null);
		ModBlocks.blockMobHeadsThree    = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "MobHeadsThree"   , com.gollum.jammyfurniture.inits.ModBlocks.blockMobHeadsThree   ).setCreativeTab(null);
		ModBlocks.blockMobHeadsFour     = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "MobHeadsFour"    , com.gollum.jammyfurniture.inits.ModBlocks.blockMobHeadsFour    ).setCreativeTab(null);
		ModBlocks.blockArmChair         = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "armChair"        , com.gollum.jammyfurniture.inits.ModBlocks.blockArmChair        ).setCreativeTab(null);
		ModBlocks.blockSofaPartLeft     = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "sofaLeft"        , com.gollum.jammyfurniture.inits.ModBlocks.blockSofaPartLeft    ).setCreativeTab(null);
		ModBlocks.blockSofaPartRight    = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "sofaRight"       , com.gollum.jammyfurniture.inits.ModBlocks.blockSofaPartRight   ).setCreativeTab(null);
		ModBlocks.blockSofaPartCenter   = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "sofaCenter"      , com.gollum.jammyfurniture.inits.ModBlocks.blockSofaPartCenter  ).setCreativeTab(null);
		ModBlocks.blockSofaPartCorner   = (BlockReplace) new BlockReplace(ZUXELUS_MODID, "sofaCorner"      , com.gollum.jammyfurniture.inits.ModBlocks.blockSofaPartCorner  ).setCreativeTab(null);
		
	}
}
