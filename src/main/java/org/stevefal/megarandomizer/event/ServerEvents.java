package org.stevefal.megarandomizer.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import org.stevefal.megarandomizer.gamerules.MegaGameRules;
import org.stevefal.megarandomizer.megadrops.RandomDrops;

import java.util.ArrayList;

public class ServerEvents {

    @SubscribeEvent
    public void onBlockDrop(BlockEvent.HarvestDropsEvent event) {
        if (event.world.getGameRules().getGameRuleBooleanValue(MegaGameRules.RULE_DO_BLOCK_RANDOM_DROPS)) {
            ArrayList<ItemStack> randomizedDrops = new ArrayList<ItemStack>();
            for (ItemStack vanillaDrop : event.drops) {
                for (int i = 0; i < vanillaDrop.stackSize; i++) {
                    randomizedDrops.add(new ItemStack(RandomDrops.getRandomizedItem(vanillaDrop)));
                }
            }
            event.drops.clear();
            event.drops.addAll(randomizedDrops);
        }
    }

}
