/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 26, 2014, 4:11:03 PM (GMT)]
 */
package vazkii.b_baubles.common.entity;

import vazkii.b_baubles.common.Botania;

import vazkii.b_baubles.common.lib.LibEntityNames;
import cpw.mods.fml.common.registry.EntityRegistry;

public final class ModEntities {

	public static void init() {
		int id = 0;


		EntityRegistry.registerModEntity(EntityFlameRing.class, LibEntityNames.FLAME_RING, id++, Botania.instance, 32, 40, false);

	}

}
