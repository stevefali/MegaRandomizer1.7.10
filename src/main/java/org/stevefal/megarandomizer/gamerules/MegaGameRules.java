package org.stevefal.megarandomizer.gamerules;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.world.GameRules;

import java.util.HashMap;
import java.util.Map;


public class MegaGameRules {

    public static final String RULE_DO_BLOCK_RANDOM_DROPS = "doBlockRandomDrops";
    public static final String RULE_DO_ENTITY_RANDOM_DROPS = "doEntityRandomDrops";
    public static final String RULE_DO_PLAYER_RANDOM_DROPS = "doPlayerRandomDrops";
    public static final String RULE_DO_VOLATILE_DROPS = "doVolatileDrops";

//    private static final String[] MEGA_RULES = {RULE_DO_BLOCK_RANDOM_DROPS, RULE_DO_ENTITY_RANDOM_DROPS, RULE_DO_PLAYER_RANDOM_DROPS};

    private static final Map<String, String> gameruleDefaults = new HashMap<String, String>();


    public static void register() {
        GameRules gameRules = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getGameRules();

        gameruleDefaults.put(RULE_DO_BLOCK_RANDOM_DROPS, "true");
        gameruleDefaults.put(RULE_DO_ENTITY_RANDOM_DROPS, "true");
        gameruleDefaults.put(RULE_DO_PLAYER_RANDOM_DROPS, "true");
        gameruleDefaults.put(RULE_DO_VOLATILE_DROPS, "false");

        for (String megaRule : gameruleDefaults.keySet()) {
            if (!gameRules.hasRule(megaRule)) {
                gameRules.addGameRule(megaRule, gameruleDefaults.get(megaRule));
            }
        }

//        for (String megaRule : MEGA_RULES) {
//            if (!gameRules.hasRule(megaRule)) {
//                gameRules.addGameRule(megaRule, "true");
//            }
//        }
    }


}
