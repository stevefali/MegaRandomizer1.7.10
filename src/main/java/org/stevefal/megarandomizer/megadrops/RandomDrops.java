package org.stevefal.megarandomizer.megadrops;

import net.minecraft.item.ItemStack;

import java.util.*;

public class RandomDrops {

    private static ArrayList<ItemStack> masterList;
    private static ArrayList<ItemStack> shuffledList;


    public static void makeMegaItemLists(long gameSeed) {

        masterList = new ArrayList<ItemStack>();
        masterList.addAll(MegaItemRegistry.createMegaItemList(excludeItems));

        shuffledList = new ArrayList<ItemStack>(masterList);
        Collections.shuffle(shuffledList, new Random(gameSeed));
    }

    public static ItemStack getRandomizedItem(ItemStack vanillaItem) {
        if (masterList != null) {
            int index = getMasterListIndex(vanillaItem);

            if (index == -1) {
                return vanillaItem;
            } else {
                return shuffledList.get(index);
            }
        } else {
            return vanillaItem;
        }
    }

    private static int getMasterListIndex(ItemStack itemStack){
        for (int i = 0; i < masterList.size(); i++) {
            if (ItemStack.areItemStacksEqual(itemStack, masterList.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private static final String[] excludeItems = {
            "minecraft:air",
            "minecraft:flowing_lava",
            "minecraft:flowing_water",
            "minecraft:end_portal",
            "minecraft:command_block",
            "minecraft:command_block_mine_cart",
            "minecraft:lava",
            "minecraft:lit_furnace",
            "minecraft:farmland",
            "minecraft:bedrock",
            "minecraft:end_portal_frame",
            "minecraft:fire",
            "minecraft:portal",
            "minecraft:spawn_egg",
            "minecraft:water",
            "minecraft:mob_spawner",
            "minecraft:redstone_wire"
    };
}
