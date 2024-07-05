package com.example.examplemod;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = MegaRandomizer1_7_10.MODID, version = MegaRandomizer1_7_10.VERSION)
public class MegaRandomizer1_7_10
{
    public static final String MODID = "megarandomizer1_7_10";
    public static final String VERSION = "1.0-1.7.10";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
}
