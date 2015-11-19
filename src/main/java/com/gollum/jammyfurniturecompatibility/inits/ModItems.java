package com.gollum.jammyfurniturecompatibility.inits;

import com.gollum.jammyfurniturecompatibility.common.item.ItemReplace;
import static com.gollum.jammyfurniturecompatibility.ModJammyFurnitureZuxelusCompatibility.ZUXELUS_MODID;

public class ModItems {

	/////////////////////
	// Liste des items //
	/////////////////////
	public static ItemReplace itemLightBulb;
	public static ItemReplace itemMantlePieceUnf;
	public static ItemReplace itemCeramicPanelUnf;
	public static ItemReplace itemCeramicPanel;
	public static ItemReplace itemWMDrum;
	public static ItemReplace itemBlindPart;
	
	public static void init() {
		ModItems.itemLightBulb       = (ItemReplace) new ItemReplace("jammyfurniture", "itemLightBulb"   , com.gollum.jammyfurniture.inits.ModItems.itemLightBulb      ).setCreativeTab(null);
		ModItems.itemMantlePieceUnf  = (ItemReplace) new ItemReplace("jammyfurniture", "mantlePieceUnf"  , com.gollum.jammyfurniture.inits.ModItems.itemMantlePieceUnf ).setCreativeTab(null);
		ModItems.itemCeramicPanelUnf = (ItemReplace) new ItemReplace("jammyfurniture", "ceramicPanelUnf" , com.gollum.jammyfurniture.inits.ModItems.itemCeramicPanelUnf).setCreativeTab(null);
		ModItems.itemCeramicPanel    = (ItemReplace) new ItemReplace("jammyfurniture", "itemCeramicPanel", com.gollum.jammyfurniture.inits.ModItems.itemCeramicPanel   ).setCreativeTab(null);
		ModItems.itemWMDrum          = (ItemReplace) new ItemReplace("jammyfurniture", "itemWMDrum"      , com.gollum.jammyfurniture.inits.ModItems.itemWMDrum         ).setCreativeTab(null);
		ModItems.itemBlindPart       = (ItemReplace) new ItemReplace("jammyfurniture", "itemBlindPart"   , com.gollum.jammyfurniture.inits.ModItems.itemBlindPart      ).setCreativeTab(null);
	}
}
