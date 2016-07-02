/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 14, 2014, 6:15:28 PM (GMT)]
 */
package vazkii.b_baubles.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.b_baubles.api.internal.DummyMethodHandler;
import vazkii.b_baubles.api.internal.IInternalMethodHandler;


import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import cpw.mods.fml.common.Loader;

public final class BotaniaAPI {


	public static Set<String> magnetBlacklist = new LinkedHashSet<String>();

	public static EnumRarity rarityRelic = EnumHelper.addRarity("RELIC", EnumChatFormatting.GOLD, "Relic");


	/**
	 * The internal method handler in use.
	 * <b>DO NOT OVERWRITE THIS OR YOU'RE GOING TO FEEL MY WRATH WHEN I UPDATE THE API.</b>
	 * The fact I have to write that means some moron already tried, don't be that moron.
	 * @see IInternalMethodHandler
	 */
	public static IInternalMethodHandler internalHandler = new DummyMethodHandler();



	
	/**
	 * Blacklists an item from being pulled by the Ring of Magnetization.
	 * Short.MAX_VALUE can be used as the stack's damage for a wildcard.
	 */
	public static void blacklistItemFromMagnet(ItemStack stack) {
		String key = getMagnetKey(stack);
		magnetBlacklist.add(key);
	}

	/**
	 * Blacklists a block from having items on top of it being pulled by the Ring of Magnetization.
	 * Short.MAX_VALUE can be used as meta for a wildcard.
	 */
	public static void blacklistBlockFromMagnet(Block block, int meta) {
		String key = getMagnetKey(block, meta);
		magnetBlacklist.add(key);
	}

	public static boolean isItemBlacklistedFromMagnet(ItemStack stack) {
		return isItemBlacklistedFromMagnet(stack, 0);
	}
	
	public static boolean isItemBlacklistedFromMagnet(ItemStack stack, int recursion) {
		if(recursion > 5)
			return false;
		
		if(stack.getItemDamage() != Short.MAX_VALUE) {
			ItemStack copy = new ItemStack(stack.getItem(), 0, Short.MAX_VALUE);
			boolean general = isItemBlacklistedFromMagnet(copy, recursion + 1);
			if(general)
				return true;
		}

		String key = getMagnetKey(stack);
		return magnetBlacklist.contains(key);
	}
	
	public static boolean isBlockBlacklistedFromMagnet(Block block, int meta) {
		return isBlockBlacklistedFromMagnet(block, meta, 0);
	}

	public static boolean isBlockBlacklistedFromMagnet(Block block, int meta, int recursion) {
		if(recursion >= 5)
			return false;
			
		if(meta != Short.MAX_VALUE) {
			boolean general = isBlockBlacklistedFromMagnet(block, Short.MAX_VALUE, recursion + 1);
			if(general)
				return true;
		}

		String key = getMagnetKey(block, meta);
		return magnetBlacklist.contains(key);
	}




	/**
	 * Gets the last recipe to have been added to the recipe list.
	 */
	public static IRecipe getLatestAddedRecipe() {
		List<IRecipe> list = CraftingManager.getInstance().getRecipeList();
		return list.get(list.size() - 1);
	}

	/**
	 * Gets the last x recipes added to the recipe list.
	 */
	public static List<IRecipe> getLatestAddedRecipes(int x) {
		List<IRecipe> list = CraftingManager.getInstance().getRecipeList();
		List<IRecipe> newList = new ArrayList();
		for(int i = x - 1; i >= 0; i--)
			newList.add(list.get(list.size() - 1 - i));

		return newList;
	}


	
	private static String getMagnetKey(ItemStack stack) {
		if(stack == null)
			return "";
			
		return "i_" + stack.getItem().getUnlocalizedName() + "@" + stack.getItemDamage();
	}

	private static String getMagnetKey(Block block, int meta) {
		return "bm_" + block.getUnlocalizedName() + "@" + meta;
	}
	
}
