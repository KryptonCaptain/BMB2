/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Apr 26, 2014, 7:08:53 PM (GMT)]
 */
package vazkii.b_baubles.common.item.equipment.bauble;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;

import org.lwjgl.opengl.GL11;

import vazkii.b_baubles.api.item.IBaubleRender;
import vazkii.b_baubles.client.lib.LibResources;
import vazkii.b_baubles.common.lib.LibItemNames;
import baubles.api.BaubleType;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemKnockbackBelt extends ItemBaubleModifier implements IBaubleRender {

	private static final ResourceLocation texture = new ResourceLocation(LibResources.MODEL_KNOCKBACK_BELT);
	private static ModelBiped model;

	public ItemKnockbackBelt() {
		super(LibItemNames.KNOCKBACK_BELT);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.BELT;
		
	}
	
	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase player) {
		super.onWornTick(stack, player);

		if(player instanceof EntityPlayer && !player.worldObj.isRemote) {
			int manaCost = 5;
			boolean hasMana = true;
			if(!hasMana)
				onUnequipped(stack, player);
			else {
				if(player.getActivePotionEffect(Potion.resistance) != null)
					player.removePotionEffect(Potion.resistance.id);

				player.addPotionEffect(new PotionEffect(Potion.resistance.id, Integer.MAX_VALUE, 0, true));
			}

		}
	}

	@Override
	public void onUnequipped(ItemStack stack, EntityLivingBase player) {
		PotionEffect effect = player.getActivePotionEffect(Potion.resistance);
		if(effect != null && effect.getAmplifier() == 0)
			player.removePotionEffect(Potion.resistance.id);
	}
	

	@Override
	void fillModifiers(Multimap<String, AttributeModifier> attributes, ItemStack stack) {
		attributes.put(SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), new AttributeModifier(getBaubleUUID(stack), "Bauble modifier", 1, 0));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onPlayerBaubleRender(ItemStack stack, RenderPlayerEvent event, RenderType type) {
		if(type == RenderType.BODY) {
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			Helper.rotateIfSneaking(event.entityPlayer);
			boolean armor = event.entityPlayer.getCurrentArmor(1) != null;
			GL11.glTranslatef(0F, 0.2F, 0F);

			float s = 1.05F / 16F;
			GL11.glScalef(s, s, s);

			if(model == null)
				model = new ModelBiped();

			model.bipedBody.render(1F);
		}
	}

}
