package org.stevefal.megarandomizer.megadrops;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

public class RandomDrops {

    private static ArrayList<Item> masterList;
    private static ArrayList<Item> shuffledList;


    public static void makeMegaItemLists(long gameSeed) {
        Set registeredItems = Item.itemRegistry.getKeys();

        masterList = new ArrayList<Item>();

        for (Object registeredItem : registeredItems) {
            if (!Arrays.asList(excludeItems).contains(registeredItem.toString())) {
                masterList.add((Item) Item.itemRegistry.getObject(registeredItem));
            }
        }

        shuffledList = new ArrayList<Item>(masterList);
        Collections.shuffle(shuffledList, new Random(gameSeed));
    }

    public static Item getRandomizedItem(ItemStack vanillaItem) {
        if (masterList != null) {
            int index = masterList.indexOf(vanillaItem.getItem());

            if (index == -1) {
                return vanillaItem.getItem();
            } else {
                return shuffledList.get(index);
            }
        } else {
            return vanillaItem.getItem();
        }
    }

    private static final String[] excludeItems = {
            "minecraft:flowing_lava",
            "minecraft:flowing_water",
            "minecraft:end_portal",
            "minecraft:command_block",
            "minecraft:lava",
            "minecraft:lit_furnace",
            "minecraft:farmland",
            "minecraft:bedrock",
            "minecraft:end_portal_frame",
            "minecraft:fire",
            "minecraft:portal",
            "minecraft:spawn_egg",
            "minecraft:water",
            "minecraft:monster_egg",
            "minecraft:mob_spawner",
            "minecraft:potatoes",
            "minecraft:cocoa",
            "minecraft:carrots"
    };
}
