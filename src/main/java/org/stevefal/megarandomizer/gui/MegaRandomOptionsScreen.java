package org.stevefal.megarandomizer.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import org.stevefal.megarandomizer.gamerules.MegaGameRules;
import org.stevefal.megarandomizer.networking.MegaMessages;
import org.stevefal.megarandomizer.networking.packets.SetGameRulesC2SPacket;

public class MegaRandomOptionsScreen extends GuiScreen {

    private final ModPauseScreen MOD_PAUSE_SCREEN;
    private final WorldClient WORLD;

    private final String BLOCKS = "Randomize Block Drops: ";
    private final String ENTITIES = "Randomize Entity Drops: ";
    private final String PLAYERS = "Randomize Player Drops: ";

    public MegaRandomOptionsScreen(ModPauseScreen modPauseScreen, WorldClient worldClient) {
        this.MOD_PAUSE_SCREEN = modPauseScreen;
        this.WORLD = worldClient;
    }

    @SuppressWarnings("unchecked")
    public void initGui() {
        this.buttonList.clear();
        byte b0 = -16;

        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 24 + b0,
                getButtonString(MegaGameRules.RULE_DO_BLOCK_RANDOM_DROPS, BLOCKS)));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 48 + b0,
                getButtonString(MegaGameRules.RULE_DO_ENTITY_RANDOM_DROPS, ENTITIES)));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 72 + b0,
                getButtonString(MegaGameRules.RULE_DO_PLAYER_RANDOM_DROPS, PLAYERS)));

        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 96 - b0, "Done"));

    }

    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                // Blocks
                setRule(MegaGameRules.RULE_DO_BLOCK_RANDOM_DROPS);
                break;
            case 1:
                // Entities
                setRule(MegaGameRules.RULE_DO_ENTITY_RANDOM_DROPS);
                break;
            case 2:
                // Players
                setRule(MegaGameRules.RULE_DO_PLAYER_RANDOM_DROPS);
                break;
            case 4:
                Minecraft.getMinecraft().displayGuiScreen(this.MOD_PAUSE_SCREEN);
                break;
        }
        initGui();
        updateToServer();
    }

    private String getButtonString(String megaGameRule, String ruleName) {
        boolean ruleValue = WORLD.getGameRules().getGameRuleBooleanValue(megaGameRule);
        String onOff = ruleValue ? "ON" : "Off";
        return ruleName + onOff;
    }

    private void setRule(String gamerule) {
        WORLD.getGameRules().setOrCreateGameRule(gamerule,
                String.valueOf(!WORLD.getGameRules().getGameRuleBooleanValue(gamerule)));
    }

    private void updateToServer() {
        MegaMessages.sendToServer(new SetGameRulesC2SPacket(
                WORLD.getGameRules().getGameRuleBooleanValue(MegaGameRules.RULE_DO_BLOCK_RANDOM_DROPS),
                WORLD.getGameRules().getGameRuleBooleanValue(MegaGameRules.RULE_DO_ENTITY_RANDOM_DROPS),
                WORLD.getGameRules().getGameRuleBooleanValue(MegaGameRules.RULE_DO_PLAYER_RANDOM_DROPS)));
    }

    public void updateScreen() {
        super.updateScreen();
    }


    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Mega Randomizer Options", this.width / 2, 40, 16777215);
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }
}
