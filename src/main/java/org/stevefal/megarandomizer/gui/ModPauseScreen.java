package org.stevefal.megarandomizer.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.*;

@SideOnly(Side.CLIENT)
public class ModPauseScreen extends GuiIngameMenu {

    public ModPauseScreen() {
    }

    @SuppressWarnings("unchecked")
    public void initGui() {
        super.initGui();
        GuiButton backButton = (GuiButton) this.buttonList.get(0);
        backButton.yPosition = this.height / 4 + 144 - 16;

        this.buttonList.add(new GuiButton(14, this.width / 2 - 100, this.height / 4 + 120 - 16, "Mega Randomizer Options"));
    }

    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);

        if (button.id == 14) {
            this.mc.displayGuiScreen(new MegaRandomOptionsScreen(this, this.mc.theWorld));
        }
    }
}
