/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 25, 2014, 6:11:10 PM (GMT)]
 */
package vazkii.b_baubles.client.core.handler;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.profiler.Profiler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import vazkii.b_baubles.api.BotaniaAPI;

import vazkii.b_baubles.client.core.helper.RenderHelper;
import vazkii.b_baubles.client.lib.LibResources;
import vazkii.b_baubles.common.Botania;

import vazkii.b_baubles.common.core.handler.ConfigHandler;

import vazkii.b_baubles.common.item.ItemSextant;

import vazkii.b_baubles.common.item.ModItems;
import vazkii.b_baubles.common.item.equipment.bauble.ItemFlightTiara;

import vazkii.b_baubles.common.lib.LibObfuscation;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;

public final class HUDHandler {



	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onDrawScreenPre(RenderGameOverlayEvent.Pre event) {
		Minecraft mc = Minecraft.getMinecraft();
		Profiler profiler = mc.mcProfiler;

		if(event.type == ElementType.HEALTH) {
            profiler.startSection("botania-hud");
            ItemStack amulet = PlayerHandler.getPlayerBaubles(mc.thePlayer).getStackInSlot(0);
            profiler.endSection();

            if (amulet != null && amulet.getItem() == ModItems.flightTiara) {
                profiler.startSection("flugelTiara");
                ItemFlightTiara.renderHUD(event.resolution, mc.thePlayer, amulet);
                profiler.endSection();
            }
        }
	}

	@SubscribeEvent
	public void onDrawScreenPost(RenderGameOverlayEvent.Post event) {
		Minecraft mc = Minecraft.getMinecraft();
		Profiler profiler = mc.mcProfiler;
		ItemStack equippedStack = mc.thePlayer.getCurrentEquippedItem();

		if(event.type == ElementType.ALL) {
			profiler.startSection("botania-hud");
			MovingObjectPosition pos = mc.objectMouseOver;


			if(MultiblockRenderHandler.currentMultiblock != null && MultiblockRenderHandler.anchor == null) {
				profiler.startSection("multiblockRightClick");
				String s = StatCollector.translateToLocal("botaniamisc.rightClickToAnchor");
				mc.fontRenderer.drawStringWithShadow(s, event.resolution.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(s) / 2, event.resolution.getScaledHeight() / 2 - 30, 0xFFFFFF);
				profiler.endSection();
			}

			
			if(equippedStack != null && equippedStack.getItem() instanceof ItemSextant) {
				profiler.startSection("sextant");
				ItemSextant.renderHUD(event.resolution, mc.thePlayer, equippedStack);
				profiler.endSection();
			}

			/*if(equippedStack != null && equippedStack.getItem() == ModItems.flugelEye) {
				profiler.startSection("flugelEye");
				ItemFlugelEye.renderHUD(event.resolution, mc.thePlayer, equippedStack);
				profiler.endSection();
			}*/


			profiler.startSection("manaBar");
			EntityPlayer player = mc.thePlayer;
			int totalMana = 0;
			int totalMaxMana = 0;
			boolean anyRequest = false;
			boolean creative = false;

			IInventory mainInv = player.inventory;
			IInventory baublesInv = PlayerHandler.getPlayerBaubles(player);

			int invSize = mainInv.getSizeInventory();
			int size = invSize;
			if(baublesInv != null)
				size += baublesInv.getSizeInventory();

			for(int i = 0; i < size; i++) {
				boolean useBaubles = i >= invSize;
				IInventory inv = useBaubles ? baublesInv : mainInv;
				ItemStack stack = inv.getStackInSlot(i - (useBaubles ? invSize : 0));


			}


			profiler.endStartSection("itemsRemaining");
			ItemsRemainingRenderHandler.render(event.resolution, event.partialTicks);
			profiler.endSection();
			profiler.endSection();

			GL11.glColor4f(1F, 1F, 1F, 1F);
		}
	}


}
