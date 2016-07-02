/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Aug 6, 2014, 6:02:29 PM (GMT)]
 */
package vazkii.b_baubles.api.item;

/**
 * An interface that defines an instance of PlayerControllerMP with
 * the ability to modify reach. See vazkii.b_baubles.client.core.handler.BotaniaPlayerController
 */
public interface IExtendedPlayerController {

	/**
	 * Sets the extra reach the player should have.
	 */
	public void setReachDistanceExtension(float f);

	/**
	 * Gets the current reach extension.
	 */
	public float getReachDistanceExtension();
}
