package org.stevefal.megarandomizer.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
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

    @SubscribeEvent
    public void onEntityDrop(LivingDropsEvent event) {
        World world = event.entity.worldObj;
        Entity ent = event.entity;

        if (ent instanceof EntityPlayer) {
            if (world.getGameRules().getGameRuleBooleanValue(MegaGameRules.RULE_DO_PLAYER_RANDOM_DROPS)) {
                randomizeEntityDrops(event, world, ent);
            }
        } else {
            if (world.getGameRules().getGameRuleBooleanValue(MegaGameRules.RULE_DO_ENTITY_RANDOM_DROPS)) {
                randomizeEntityDrops(event, world, ent);
            }
        }
    }

    private static void randomizeEntityDrops(LivingDropsEvent event, World world, Entity ent) {
        ArrayList<EntityItem> randomizedDrops = new ArrayList<EntityItem>();
        for (EntityItem vanillaDrop : event.drops) {
            for (int i = 0; i < vanillaDrop.getEntityItem().stackSize; i++) {
                randomizedDrops.add(new EntityItem(world, ent.posX, ent.posY, ent.posZ, new ItemStack(RandomDrops.getRandomizedItem(vanillaDrop.getEntityItem()))));
            }
        }
        event.drops.clear();
        event.drops.addAll(randomizedDrops);
    }

}
