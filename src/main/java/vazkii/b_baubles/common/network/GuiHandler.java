/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 14, 2014, 6:50:15 PM (GMT)]
 */
package vazkii.b_baubles.common.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import vazkii.b_baubles.client.gui.box.ContainerBaubleBox;
import vazkii.b_baubles.client.gui.box.GuiBaubleBox;
import vazkii.b_baubles.common.lib.LibGuiIDs;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case LibGuiIDs.BAUBLE_BOX :
			return new ContainerBaubleBox(player);
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case LibGuiIDs.BAUBLE_BOX :
			return new GuiBaubleBox(player);
		}

		return null;
	}

}
