/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 13, 2014, 7:45:37 PM (GMT)]
 */
package vazkii.b_baubles.common.core.proxy;

import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.Level;

import vazkii.b_baubles.api.BotaniaAPI;
import vazkii.b_baubles.client.core.handler.PlayerWarningHandler;
import vazkii.b_baubles.common.Botania;
import vazkii.b_baubles.common.core.handler.*;
import vazkii.b_baubles.common.core.helper.Vector3;
import vazkii.b_baubles.common.crafting.ModCraftingRecipes;
import vazkii.b_baubles.common.entity.ModEntities;
import vazkii.b_baubles.common.item.ModItems;
import vazkii.b_baubles.common.lib.LibMisc;
import vazkii.b_baubles.common.network.GuiHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		BotaniaAPI.internalHandler = new InternalMethodHandler();

		ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
		
		ModItems.init();
		ModEntities.init();


		ModCraftingRecipes.init();


	}

	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Botania.instance, new GuiHandler());

		FMLCommonHandler.instance().bus().register(new CommonTickHandler());

		//Maybe doing them here will work better
		ItemStack flightBase = new ItemStack(ModItems.flightTiara,1,0);
		ItemStack flight1 = new ItemStack(ModItems.flightTiara,1,1);
		ItemStack flight2 = new ItemStack(ModItems.flightTiara,1,2);
		ItemStack flight3 = new ItemStack(ModItems.flightTiara,1,3);
		ItemStack flight4 = new ItemStack(ModItems.flightTiara,1,4);
		ItemStack flight5 = new ItemStack(ModItems.flightTiara,1,5);
		ItemStack ink = new ItemStack(Items.dye,1,0);
		
		////
		
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara,1,0), new Object[]{
			"F F", 
			" C ", 
			"   ",
			'C', flight1,
			'F', Blocks.glass});
		
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara,1,0), new Object[]{
			"F F", 
			" C ", 
			"   ",
			'C', flight2,
			'F', Blocks.glass});
		
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara,1,0), new Object[]{
			"F F", 
			" C ", 
			"   ",
			'C', flight3,
			'F', Blocks.glass});
		
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara,1,0), new Object[]{
			"F F", 
			" C ", 
			"   ",
			'C', flight4,
			'F', Blocks.glass});
		
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara,1,0), new Object[]{
			"F F", 
			" C ", 
			"   ",
			'C', flight5,
			'F', Blocks.glass});
		
		////
		
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara,1,1), new Object[]{
			"F F", 
			" C ", 
			"   ",
			'C', flightBase,
			'F', Items.feather});
		
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara,1,2), new Object[]{
			"F F", 
			"ICI", 
			"   ",
			'C', flightBase,
			'F', Items.feather,
			'I', ink}); 
		
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara,1,3), new Object[]{
			"S S", 
			"FCF", 
			"S S",
			'C', flightBase,
			'S', Items.snowball,
			'F', Items.feather});
		
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara,1,4), new Object[]{
			"F F", 
			"RCR", 
			"   ",
			'C', flightBase,
			'R', Items.blaze_rod,
			'F', Items.blaze_powder});
		
		GameRegistry.addRecipe(new ItemStack(ModItems.flightTiara,1,5), new Object[]{
			"F F", 
			"GCG", 
			"F F",
			'C', flightBase,
			'G', Items.glowstone_dust,
			'F', Items.feather});
		
		
		
		
		
		
		
	}

	public void postInit(FMLPostInitializationEvent event) {

		ConfigHandler.loadPostInit();

	}

	private int countWords(String s) {
		String s1 = StatCollector.translateToLocal(s);
		return s1.split(" ").length;
	}

	// Overriding the internal method handler will break everything as it changes regularly.
	// So just don't be a moron and don't override it. Thanks.
	public void serverAboutToStart(FMLServerAboutToStartEvent event) {
		String clname = BotaniaAPI.internalHandler.getClass().getName();
		String expect = "vazkii.b_baubles.common.core.handler.InternalMethodHandler";
		if(!clname.equals(expect)) {
			new IllegalAccessError("The Botania API internal method handler has been overriden. "
					+ "This will cause crashes and compatibility issues, and that's why it's marked as"
					+ " \"Do not Override\". Whoever had the brilliant idea of overriding it needs to go"
					+ " back to elementary school and learn to read. (Expected classname: " + expect + ", Actual classname: " + clname + ")").printStackTrace();
			FMLCommonHandler.instance().exitJava(1, true);
		}
	}

	public void serverStarting(FMLServerStartingEvent event) {

	}

	
	public boolean isTheClientPlayer(EntityLivingBase entity) {
		return false;
	}


	
	public String getLastVersion() {
		return LibMisc.BUILD;
	}

	public void setExtraReach(EntityLivingBase entity, float reach) {
		if(entity instanceof EntityPlayerMP)
			((EntityPlayerMP) entity).theItemInWorldManager.setBlockReachDistance(Math.max(5, ((EntityPlayerMP) entity).theItemInWorldManager.getBlockReachDistance() + reach));
	}


	public void setMultiblock(World world, int x, int y, int z, double radius, Block block) {
		// NO-OP
	}
	
	public void removeSextantMultiblock() {
		// NO-OP
	}
	
	public long getWorldElapsedTicks() {
		return MinecraftServer.getServer().worldServers[0].getTotalWorldTime();
	}

	public void setSparkleFXNoClip(boolean noclip) {
		// NO-OP
	}

	public void setSparkleFXCorrupt(boolean corrupt) {
		// NO-OP
	}

	public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m) {
		sparkleFX(world, x, y, z, r, g, b, size, m, false);
	}

	public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m, boolean fake) {
		// NO-OP
	}

	public void setWispFXDistanceLimit(boolean limit) {
		// NO-OP
	}

	public void setWispFXDepthTest(boolean depth) {
		// NO-OP
	}

	public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size) {
		wispFX(world, x, y, z, r, g, b, size, 0F);
	}

	public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float gravity) {
		wispFX(world, x, y, z, r, g, b, size, gravity, 1F);
	}

	public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float gravity, float maxAgeMul) {
		wispFX(world, x, y, z, r, g, b, size, 0, -gravity, 0, maxAgeMul);
	}

	public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float motionx, float motiony, float motionz) {
		wispFX(world, x, y, z, r, g, b, size, motionx, motiony, motionz, 1F);
	}

	public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float motionx, float motiony, float motionz, float maxAgeMul) {
		// NO-OP
	}

	public void lightningFX(World world, Vector3 vectorStart, Vector3 vectorEnd, float ticksPerMeter, int colorOuter, int colorInner) {
		lightningFX(world, vectorStart, vectorEnd, ticksPerMeter, System.nanoTime(), colorOuter, colorInner);
	}

	public void lightningFX(World world, Vector3 vectorStart, Vector3 vectorEnd, float ticksPerMeter, long seed, int colorOuter, int colorInner) {
		// NO-OP
	}

}
