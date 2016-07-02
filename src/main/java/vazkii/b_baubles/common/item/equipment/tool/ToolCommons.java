/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Apr 13, 2014, 7:13:04 PM (GMT)]
 */
package vazkii.b_baubles.common.item.equipment.tool;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import vazkii.b_baubles.api.BotaniaAPI;
import vazkii.b_baubles.common.core.handler.ConfigHandler;
import vazkii.b_baubles.common.item.ModItems;

public final class ToolCommons {

	public static Material[] materialsPick = new Material[]{ Material.rock, Material.iron, Material.ice, Material.glass, Material.piston, Material.anvil };
	public static Material[] materialsShovel = new Material[]{ Material.grass, Material.ground, Material.sand, Material.snow, Material.craftedSnow, Material.clay };
	public static Material[] materialsAxe = new Material[]{ Material.coral, Material.leaves, Material.plants, Material.wood };


	public static void removeBlocksInIteration(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int xs, int ys, int zs, int xe, int ye, int ze, Block block, Material[] materialsListing, boolean silk, int fortune, boolean dispose) {
		float blockHardness = block == null ? 1F : block.getBlockHardness(world, x, y, z);

		for(int x1 = xs; x1 < xe; x1++)
			for(int y1 = ys; y1 < ye; y1++)
				for(int z1 = zs; z1 < ze; z1++)
					removeBlockWithDrops(player, stack, world, x1 + x, y1 + y, z1 + z, x, y, z, block, materialsListing, silk, fortune, blockHardness, dispose);
	}

	public static boolean isRightMaterial(Material material, Material[] materialsListing) {
		for(Material mat : materialsListing)
			if(material == mat)
				return true;

		return false;
	}

	public static void removeBlockWithDrops(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int bx, int by, int bz, Block block, Material[] materialsListing, boolean silk, int fortune, float blockHardness, boolean dispose) {
		removeBlockWithDrops(player, stack, world, x, y, z, bx, by, bz, block, materialsListing, silk, fortune, blockHardness, dispose, true);
	}

	public static void removeBlockWithDrops(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int bx, int by, int bz, Block block, Material[] materialsListing, boolean silk, int fortune, float blockHardness, boolean dispose, boolean particles) {
		if(!world.blockExists(x, y, z))
			return;

		Block blk = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);

		if(block != null && blk != block)
			return;

		Material mat = world.getBlock(x, y, z).getMaterial();
		if(!world.isRemote && blk != null && !blk.isAir(world, x, y, z) && blk.getPlayerRelativeBlockHardness(player, world, x, y, z) > 0) {
			if(!blk.canHarvestBlock(player, meta) || !isRightMaterial(mat, materialsListing))
				return;

			if(!player.capabilities.isCreativeMode) {
				int localMeta = world.getBlockMetadata(x, y, z);
				blk.onBlockHarvested(world, x, y, z, localMeta, player);

				if(blk.removedByPlayer(world, player, x, y, z, true)) {
					blk.onBlockDestroyedByPlayer(world, x, y, z, localMeta);

				}

			} else world.setBlockToAir(x, y, z);

		}
	}



	/**
	 * @author mDiyo
	 */
	public static MovingObjectPosition raytraceFromEntity(World world, Entity player, boolean par3, double range) {
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * f;
		if (!world.isRemote && player instanceof EntityPlayer)
			d1 += ((EntityPlayer) player).eyeHeight;
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = range;
		Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
		return world.rayTraceBlocks(vec3, vec31, par3);
	}

}
