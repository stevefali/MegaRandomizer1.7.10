package org.stevefal.megarandomizer;

import cpw.mods.fml.common.event.FMLServerStartedEvent;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.server.MinecraftServer;
import org.stevefal.megarandomizer.megadrops.RandomDrops;

@Mod(modid = MegaRandomizer1_7_10.MODID, version = MegaRandomizer1_7_10.VERSION)
public class MegaRandomizer1_7_10
{
    public static final String MODID = "megarandomizer1_7_10";
    public static final String VERSION = "1.0-1.7.10";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }


    @EventHandler
    public void onServerReady(FMLServerStartedEvent event) {
        RandomDrops.makeMegaItemLists(MinecraftServer.getServer().getEntityWorld().getSeed());
    }
}
