/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 19, 2014, 3:54:48 PM (GMT)]
 */
package vazkii.b_baubles.common.crafting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import vazkii.b_baubles.api.BotaniaAPI;
import vazkii.b_baubles.client.lib.LibRenderIDs;
import vazkii.b_baubles.common.Botania;
import vazkii.b_baubles.common.core.handler.ConfigHandler;
import vazkii.b_baubles.common.item.ModItems;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;

public final class ModCraftingRecipes {


	public static IRecipe recipeTerraformRod;
	public static IRecipe recipeFireRod;
	public static IRecipe recipeDiviningRod;
	
	public static IRecipe recipeTravelBelt;
	public static IRecipe recipeKnocbackBelt;
	public static IRecipe recipeSpeedUpBelt;
	public static IRecipe recipeSuperTravelBelt;
	
	public static IRecipe recipeIcePendant;
	public static IRecipe recipeFirePendant;
	public static IRecipe recipeSuperLavaPendant;
	public static IRecipe recipeWaterRing;
	public static IRecipe recipeMiningRing;
	public static IRecipe recipeMagnetRing;
	public static IRecipe recipeGreaterMagnetRing;
	public static IRecipe recipeReachRing;
	
	public static IRecipe recipeSextant;
	public static IRecipe recipeBlackHoleTalisman;
	
	public static IRecipe recipeFlightTiara;
	public static IRecipe recipeWing0;
	public static IRecipe recipeWing1;
	public static IRecipe recipeWing2;
	public static IRecipe recipeWing3;
	public static IRecipe recipeWing4;
	public static IRecipe recipeWing5;
	public static IRecipe recipeWingNull1;
	public static IRecipe recipeWingNull2;
	public static IRecipe recipeWingNull3;
	public static IRecipe recipeWingNull4;
	public static IRecipe recipeWingNull5;
	
	
	public static IRecipe recipeBaubleCase;



	public static void init() {
		int recipeListSize = CraftingManager.getInstance().getRecipeList().size();
		
		// Soujourner's Belt Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.travelBelt),
				"GLG", "L L", "RLR",
				'G', new ItemStack(Items.gold_ingot),
				'L', new ItemStack(Items.leather),
				'R', new ItemStack(Blocks.redstone_block)
		);
		recipeTravelBelt = BotaniaAPI.getLatestAddedRecipe();

		// Tectonic Girdle Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.knockbackBelt),
				"OLO", "L L", "ILI",
				'O', new ItemStack(Blocks.obsidian),
				'L', new ItemStack(Items.leather),
				'I', new ItemStack(Blocks.iron_block)
		);
		recipeKnocbackBelt = BotaniaAPI.getLatestAddedRecipe();

		// Snowflake Pendant Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.icePendant),
				"BSB", "G G", "IDI",
				'B', new ItemStack(Items.snowball),
				'S', new ItemStack(Items.string),
				'G', new ItemStack(Items.gold_ingot),
				'I', new ItemStack(Blocks.ice),
				'D', new ItemStack(Items.diamond)
		);
		recipeIcePendant = BotaniaAPI.getLatestAddedRecipe();

		// Pyroclast Pendant Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.lavaPendant),
				"BSB", "G G", "WDW",
				'B', new ItemStack(Items.blaze_powder),
				'S', new ItemStack(Items.string),
				'G', new ItemStack(Items.iron_ingot),
				'W', new ItemStack(Items.water_bucket),
				'D', new ItemStack(Items.diamond)
		);
		recipeFirePendant = BotaniaAPI.getLatestAddedRecipe();


		// Ring of Chordata Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.waterRing),
				"CDC", "I I", "FIP",
				'C', new ItemStack(Items.golden_carrot),
				'D', new ItemStack(Items.diamond),
				'I', new ItemStack(Items.iron_ingot),
				'F', new ItemStack(Items.fish,1,0),
				'P', new ItemStack(Items.fish,1,3)				
		);
		recipeWaterRing = BotaniaAPI.getLatestAddedRecipe();

		// Ring of the Mantle Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.miningRing),
				"RDR", "G G", "PGS",
				'R', new ItemStack(Blocks.redstone_block),
				'D', new ItemStack(Items.diamond),
				'G', new ItemStack(Items.gold_ingot),
				'P', new ItemStack(Items.golden_pickaxe),
				'S', new ItemStack(Items.golden_shovel)
		);
		recipeMiningRing = BotaniaAPI.getLatestAddedRecipe();

		// Ring of Magnetization Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.magnetRing),
				" DR", "I I", "LI ",
				'D', new ItemStack(Items.diamond),
				'R', new ItemStack(Items.redstone),
				'I', new ItemStack(Items.iron_ingot),
				'L', new ItemStack(Items.dye,1,4)
		);
		recipeMagnetRing = BotaniaAPI.getLatestAddedRecipe();


		// Globetrotter's Sash Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.superTravelBelt),
				"GRG", "RBR", "GRG",
				'G', new ItemStack(Items.gold_ingot),
				'R', new ItemStack(Blocks.redstone_block),
				'B', new ItemStack(ModItems.travelBelt)
		);
		recipeSuperTravelBelt = BotaniaAPI.getLatestAddedRecipe();
		
		
		//Ring of Far Reach Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.reachRing),
				"PDP", "G G", "SGS",
				'P', new ItemStack(Blocks.piston),
				'D', new ItemStack(Items.diamond),
				'G', new ItemStack(Items.gold_ingot),
				'S', new ItemStack(Items.slime_ball)				
		);
		recipeReachRing = BotaniaAPI.getLatestAddedRecipe();
		
		
		//Crimson Pendant recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.superLavaPendant),
				"RLR", "LCL", "RLR",
				'R', new ItemStack(Items.blaze_rod),
				'L', new ItemStack(Items.leather),
				'C', new ItemStack(ModItems.lavaPendant)				
		);
		recipeSuperLavaPendant = BotaniaAPI.getLatestAddedRecipe();
		

		// Rod of the Hells Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.fireRod),
				" BF", " T ", "R  ",
				'F', new ItemStack(Items.fire_charge),
				'B', new ItemStack(Items.diamond),
				'T', new ItemStack(Items.stick),
				'R', new ItemStack(Items.blaze_rod)
		);
		recipeFireRod = BotaniaAPI.getLatestAddedRecipe();
		


		// Rod of Divining Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.diviningRod),
				" DE", " SD", "S  ",
				'S', new ItemStack(Items.stick),
				'D', new ItemStack(Items.diamond),
				'E', new ItemStack(Items.ender_eye)
		);
		recipeDiviningRod = BotaniaAPI.getLatestAddedRecipe();


		// Black Hole Talisman Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.blackHoleTalisman),
				" L ", "LEL", " C ",
				'L', new ItemStack(Items.gold_ingot),
				'E', new ItemStack(Items.ender_eye),
				'C', new ItemStack(Blocks.chest)
		);
		recipeBlackHoleTalisman = BotaniaAPI.getLatestAddedRecipe();
		

		// Greater Ring of Magnetization Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.magnetRingGreater),
			" GR", "GBG", "LG ",
			'G', new ItemStack(Items.gold_ingot),
			'R', new ItemStack(Blocks.redstone_block),
			'B', new ItemStack(ModItems.magnetRing),
			'L', new ItemStack(Blocks.lapis_block)
		);
		recipeGreaterMagnetRing = BotaniaAPI.getLatestAddedRecipe();
		

		// Worldshaper's Sextant Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.sextant), 
				" GSB", "SSG", "GGG",
				'G', new ItemStack(Items.gold_ingot),
				'S', new ItemStack(Items.stick),
				'B', new ItemStack(Blocks.glass)
		);
		recipeSextant = BotaniaAPI.getLatestAddedRecipe();
		
		
		// Planestrider's Sash Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.speedUpBelt),
				"SGS", "GBG", "IGI",
				'B', new ItemStack(ModItems.travelBelt),
				'S', new ItemStack(Items.sugar),
				'G', new ItemStack(Blocks.redstone_block),
				'I', new ItemStack(Items.gold_ingot)
		);
		recipeSpeedUpBelt = BotaniaAPI.getLatestAddedRecipe();
		
		
		
		// Bauble Case Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.baubleBox), 
				"SLS", "LCL", "GLG",
				'L', new ItemStack(Items.leather),
				'S', new ItemStack(Items.string),
				'C', new ItemStack(Blocks.chest),
				'G', new ItemStack(Items.gold_ingot)
		);
		recipeBaubleCase = BotaniaAPI.getLatestAddedRecipe();
		

		
		// Flugel Tiara Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara),
				"FGF", "GSG", "DGD",
				'F', new ItemStack(Blocks.glass),
				'G', new ItemStack(Items.gold_ingot),
				'S', new ItemStack(Items.nether_star),
				'D', new ItemStack(Items.diamond)
		);
		recipeFlightTiara = BotaniaAPI.getLatestAddedRecipe();
		
		/*
		// Wing Recipes
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara,1,0), 
				"G G", " R ",
				'G', new ItemStack(Blocks.glass),
				'R', new ItemStack(ModItems.flightTiara,1,1)
		);
		recipeWingNull1 = BotaniaAPI.getLatestAddedRecipe();
		
		
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara,1,1), 
				"G G", " R ",
				'G', new ItemStack(Items.feather),
				'R', new ItemStack(ModItems.flightTiara)
		);
		recipeWing1 = BotaniaAPI.getLatestAddedRecipe();
		
		
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara,1,2), 
				"G G", "IRI",
				'G', new ItemStack(Items.feather),
				'I', new ItemStack(Items.dye,1,0),
				'R', new ItemStack(ModItems.flightTiara)
		);
		recipeWing2 = BotaniaAPI.getLatestAddedRecipe();
		
		
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara,1,3), 
				"G G", "SRS", "G G",
				'G', new ItemStack(Items.snowball),
				'S', new ItemStack(Items.feather),
				'R', new ItemStack(ModItems.flightTiara)
		);
		recipeWing3 = BotaniaAPI.getLatestAddedRecipe();
		
		
		
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara,1,4), 
				"G G", "BRB",
				'G', new ItemStack(Items.blaze_powder),
				'B', new ItemStack(Items.blaze_rod),
				'R', new ItemStack(ModItems.flightTiara)
		);
		recipeWing4 = BotaniaAPI.getLatestAddedRecipe();
		
		
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara,1,5), 
				"G G", " R ", "G G",
				'G', new ItemStack(Items.glowstone_dust),
				'R', new ItemStack(ModItems.flightTiara)
		);
		recipeWing5 = BotaniaAPI.getLatestAddedRecipe();		
		*/
		
		/*
		// Terra Firma Rod Recipe
		GameRegistry.addRecipe(new ItemStack(ModItems.terraformRod),
				" WT", "ARS", "GM ",
				'T', LibOreDict.TERRA_STEEL,
				'R', new ItemStack(ModItems.dirtRod),
				'G', new ItemStack(ModItems.grassSeeds),
				'W', LibOreDict.RUNE[7],
				'S', LibOreDict.RUNE[4],
				'M', LibOreDict.RUNE[5],
				'A', LibOreDict.RUNE[6]);
		recipeTerraformRod = BotaniaAPI.getLatestAddedRecipe();
		 */
		
	}

	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}

	private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, recipe));
	}
}
