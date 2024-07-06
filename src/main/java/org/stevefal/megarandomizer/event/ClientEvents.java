package org.stevefal.megarandomizer.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import org.stevefal.megarandomizer.gui.ModPauseScreen;

@SideOnly(Side.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public void onPauseMenuTruggered(GuiScreenEvent.InitGuiEvent event) {
        if (event.gui instanceof GuiIngameMenu) {
            Minecraft.getMinecraft().displayGuiScreen(new ModPauseScreen((GuiIngameMenu) event.gui));
        }
    }
}
