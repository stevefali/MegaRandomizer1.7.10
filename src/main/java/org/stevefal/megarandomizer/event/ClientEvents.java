package org.stevefal.megarandomizer.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import org.stevefal.megarandomizer.gui.ModPauseScreen;
import org.stevefal.megarandomizer.networking.MegaMessages;
import org.stevefal.megarandomizer.networking.packets.RequestGameRulesSyncC2SPacket;

@SideOnly(Side.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public void onPauseMenuTriggered(GuiScreenEvent.InitGuiEvent event) {
        MegaMessages.sendToServer(new RequestGameRulesSyncC2SPacket());
        if (event.gui instanceof GuiIngameMenu) {
            Minecraft.getMinecraft().displayGuiScreen(new ModPauseScreen((GuiIngameMenu) event.gui));
        }
    }
}
