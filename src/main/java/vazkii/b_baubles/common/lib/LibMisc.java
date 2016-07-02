/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 13, 2014, 6:32:05 PM (GMT)]
 */
package vazkii.b_baubles.common.lib;

import net.minecraftforge.common.util.ForgeDirection;

public final class LibMisc {

	// Mod Constants
	public static final String MOD_ID = "b_baubles";
	public static final String MOD_NAME = "Baubles Minus Botania";
	public static final String BUILD = "0";
	public static final String VERSION = "2." + BUILD;
	public static final String DEPENDENCIES = "required-after:Baubles;after:Thaumcraft";

	// Network Contants
	public static final String NETWORK_CHANNEL = MOD_ID;

	// Proxy Constants
	public static final String PROXY_COMMON = "vazkii.b_baubles.common.core.proxy.CommonProxy";
	public static final String PROXY_CLIENT = "vazkii.b_baubles.client.core.proxy.ClientProxy";
	public static final String GUI_FACTORY = "vazkii.b_baubles.client.core.proxy.GuiFactory";

	// IMC Keys
	public static final String BLACKLIST_ITEM = "blackListItem";

	public static final ForgeDirection[] CARDINAL_DIRECTIONS = new ForgeDirection[] {
		ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST
	};

	public static final int[] CONTROL_CODE_COLORS = new int[] {
		0x000000, 0x0000AA, 0x00AA00, 0x00AAAA,
		0xAA0000, 0xAA00AA, 0xFFAA00, 0xAAAAAA,
		0x555555, 0x5555FF, 0x55FF55, 0x55FFFF,
		0xFF5555, 0xFF55FF, 0xFFFF55, 0xFFFFFF
	};

}
