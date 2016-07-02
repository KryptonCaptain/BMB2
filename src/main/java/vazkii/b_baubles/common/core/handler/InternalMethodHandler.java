/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 14, 2014, 6:44:59 PM (GMT)]
 */
package vazkii.b_baubles.common.core.handler;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vazkii.b_baubles.api.internal.DummyMethodHandler;

import vazkii.b_baubles.api.lexicon.multiblock.MultiblockSet;

import vazkii.b_baubles.client.core.handler.HUDHandler;
import vazkii.b_baubles.client.core.helper.IconHelper;
import vazkii.b_baubles.common.Botania;
import vazkii.b_baubles.common.item.ModItems;
import baubles.common.lib.PlayerHandler;
import baubles.common.network.PacketHandler;
import baubles.common.network.PacketSyncBauble;
import cpw.mods.fml.common.Optional;

public class InternalMethodHandler extends DummyMethodHandler {



	@Override
	public IInventory getBaublesInventory(EntityPlayer player) {
		return PlayerHandler.getPlayerBaubles(player);
	}



	@Override
	public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m) {
		Botania.proxy.sparkleFX(world, x, y, z, r, g, b, size, m);
	}


	@Override
	public long getWorldElapsedTicks() {
		return Botania.proxy.getWorldElapsedTicks();
	}


	@Override
	public void sendBaubleUpdatePacket(EntityPlayer player, int slot) {
		if(player instanceof EntityPlayerMP)
			PacketHandler.INSTANCE.sendTo(new PacketSyncBauble(player, slot), (EntityPlayerMP) player);
	}
}
