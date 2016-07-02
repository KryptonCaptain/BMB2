/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 14, 2014, 5:17:47 PM (GMT)]
 */
package vazkii.b_baubles.common.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.b_baubles.api.BotaniaAPI;

import vazkii.b_baubles.common.item.equipment.bauble.ItemFlightTiara;
import vazkii.b_baubles.common.item.equipment.bauble.ItemGreaterMagnetRing;
import vazkii.b_baubles.common.item.equipment.bauble.ItemIcePendant;
import vazkii.b_baubles.common.item.equipment.bauble.ItemKnockbackBelt;
import vazkii.b_baubles.common.item.equipment.bauble.ItemLavaPendant;
import vazkii.b_baubles.common.item.equipment.bauble.ItemMagnetRing;
import vazkii.b_baubles.common.item.equipment.bauble.ItemMiningRing;
import vazkii.b_baubles.common.item.equipment.bauble.ItemReachRing;
import vazkii.b_baubles.common.item.equipment.bauble.ItemSpeedUpBelt;
import vazkii.b_baubles.common.item.equipment.bauble.ItemSuperLavaPendant;
import vazkii.b_baubles.common.item.equipment.bauble.ItemSuperTravelBelt;
import vazkii.b_baubles.common.item.equipment.bauble.ItemTravelBelt;
import vazkii.b_baubles.common.item.equipment.bauble.ItemWaterRing;

import vazkii.b_baubles.common.item.rod.ItemDiviningRod;

import vazkii.b_baubles.common.item.rod.ItemFireRod;

//import vazkii.b_baubles.common.item.rod.ItemTerraformRod;


public final class ModItems {

	//public static Item terraformRod;
	public static Item travelBelt;
	public static Item knockbackBelt;
	public static Item icePendant;
	public static Item lavaPendant;
	public static Item magnetRing;
	public static Item waterRing;
	public static Item miningRing;
	public static Item flightTiara;
	public static Item superTravelBelt;
	public static Item magnetRingGreater;
	public static Item fireRod;
	public static Item reachRing;
	public static Item superLavaPendant;
	public static Item diviningRod;
	public static Item blackHoleTalisman;
	public static Item sextant;
	public static Item speedUpBelt;
	public static Item baubleBox;

	public static void init() {

		//terraformRod = new ItemTerraformRod();
		diviningRod = new ItemDiviningRod();
		travelBelt = new ItemTravelBelt();
		knockbackBelt = new ItemKnockbackBelt();
		icePendant = new ItemIcePendant();
		lavaPendant = new ItemLavaPendant();
		magnetRing = new ItemMagnetRing();
		waterRing = new ItemWaterRing();
		miningRing = new ItemMiningRing();
		flightTiara = new ItemFlightTiara();
		superTravelBelt = new ItemSuperTravelBelt();
		fireRod = new ItemFireRod();
		reachRing = new ItemReachRing();
		superLavaPendant = new ItemSuperLavaPendant();
		blackHoleTalisman = new ItemBlackHoleTalisman();
		magnetRingGreater = new ItemGreaterMagnetRing();
		sextant = new ItemSextant();
		speedUpBelt = new ItemSpeedUpBelt();
		baubleBox = new ItemBaubleBox();
		
	}
}
