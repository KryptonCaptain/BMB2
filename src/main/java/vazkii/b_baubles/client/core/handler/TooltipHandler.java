/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jul 26, 2014, 2:33:04 AM (GMT)]
 */
package vazkii.b_baubles.client.core.handler;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import vazkii.b_baubles.common.core.handler.ConfigHandler;
import vazkii.b_baubles.common.core.helper.ItemNBTHelper;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public final class TooltipHandler {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onTooltipEvent(ItemTooltipEvent event) {
		if(event.itemStack.getItem() == Item.getItemFromBlock(Blocks.dirt) && event.itemStack.getItemDamage() == 1) {
			event.toolTip.add(StatCollector.translateToLocal("botaniamisc.coarseDirt0"));
			event.toolTip.add(StatCollector.translateToLocal("botaniamisc.coarseDirt1"));
		} else if(event.itemStack.getItem() == Item.getItemFromBlock(Blocks.mob_spawner) && event.entityPlayer.capabilities.isCreativeMode)
			event.toolTip.add(StatCollector.translateToLocal("botaniamisc.spawnerTip"));


        Item item = event.itemStack.getItem();

	}

}
