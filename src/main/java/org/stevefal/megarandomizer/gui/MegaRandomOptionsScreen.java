package org.stevefal.megarandomizer.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import org.stevefal.megarandomizer.gamerules.MegaGameRules;

public class MegaRandomOptionsScreen extends GuiScreen {

    private final ModPauseScreen MOD_PAUSE_SCREEN;
    private final WorldClient WORLD;
    private int field_146444_f;

    private final String blockRule = MegaGameRules.RULE_DO_BLOCK_RANDOM_DROPS;

    public MegaRandomOptionsScreen(ModPauseScreen modPauseScreen, WorldClient worldClient) {
        this.MOD_PAUSE_SCREEN = modPauseScreen;
        this.WORLD = worldClient;
    }

    @SuppressWarnings("unchecked")
    public void initGui() {
        this.buttonList.clear();
        byte b0 = -16;

        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 24 + b0,
                getButtonString(MegaGameRules.RULE_DO_BLOCK_RANDOM_DROPS, "Block")));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 48 + b0,
                getButtonString(MegaGameRules.RULE_DO_ENTITY_RANDOM_DROPS, "Entity")));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 72 + b0,
                getButtonString(MegaGameRules.RULE_DO_PLAYER_RANDOM_DROPS, "Player")));

        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 96 - b0, "Done"));
    }


    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                System.out.println("Test button clicked!!");
                // Blocks
                break;
            case 1:
                // Entities
                break;
            case 2:
                // Players
                break;
            case 3:
            case 4:
                Minecraft.getMinecraft().displayGuiScreen(this.MOD_PAUSE_SCREEN);
                break;
        }
    }

    private String getButtonString(String megaGameRule, String ruleName) {
        boolean ruleValue = this.WORLD.getGameRules().getGameRuleBooleanValue(megaGameRule);
        String onOff = ruleValue ? "ON" : "Off";
        return "Randomize " + ruleName + " Drops: " + onOff;
    }

    public void updateScreen() {
        super.updateScreen();
        ++this.field_146444_f;
    }


    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Mega Randomizer Options", this.width / 2, 40, 16777215);
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }
}
