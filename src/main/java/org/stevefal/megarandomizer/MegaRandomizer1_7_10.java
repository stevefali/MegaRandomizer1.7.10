package org.stevefal.megarandomizer;

import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import org.stevefal.megarandomizer.event.ServerEvents;
import org.stevefal.megarandomizer.gamerules.MegaGameRules;
import org.stevefal.megarandomizer.megadrops.RandomDrops;

@Mod(modid = MegaRandomizer1_7_10.MODID, version = MegaRandomizer1_7_10.VERSION)
public class MegaRandomizer1_7_10
{
    public static final String MODID = "megarandomizer1_7_10";
    public static final String VERSION = "1.0-1.7.10";
    
    @EventHandler
    public void init(FMLInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(new ServerEvents());
    }


    @EventHandler
    public void onServerReady(FMLServerStartedEvent event) {

        MegaGameRules.register();

        RandomDrops.makeMegaItemLists(MinecraftServer.getServer().getEntityWorld().getSeed());
    }
}
