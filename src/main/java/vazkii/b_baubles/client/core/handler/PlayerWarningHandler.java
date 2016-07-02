package vazkii.b_baubles.client.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

/**
 * Created by kotasc3 on 9/24/2015.
 */
public class PlayerWarningHandler {

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        String msg= " " + StatCollector.translateToLocal("botania.ingamewarning");

        String[] tmp =  msg.split(" ");
        String formatted = "";
        for(int x = 0; x < tmp.length; x++){
            formatted += EnumChatFormatting.RED.toString();
            formatted += tmp[x];
            formatted += " ";
        }
        event.player.addChatComponentMessage(new ChatComponentText("Botania A New Dawn:"+ formatted));
    }
}
