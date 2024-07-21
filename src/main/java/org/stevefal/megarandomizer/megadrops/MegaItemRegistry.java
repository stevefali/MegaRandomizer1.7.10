package org.stevefal.megarandomizer.megadrops;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MegaItemRegistry {

    private static final Map<Integer, Integer> subtypeQuantities = createSubtypeQuantities();

    public static ArrayList<ItemStack> createMegaItemList(String[] excludeItems) {
        ArrayList<ItemStack> megaItems = new ArrayList<ItemStack>();

        for (Object registeredItem : Item.itemRegistry.getKeys()) {
            if (!Arrays.asList(excludeItems).contains(registeredItem.toString())) {
                ItemStack itemStack = new ItemStack((Item) Item.itemRegistry.getObject(registeredItem));

                if (!itemStack.getHasSubtypes()) {
                    megaItems.add(itemStack);
                } else {
                    if (!megaItems.contains(itemStack)) {
                        int itemId = Item.getIdFromItem(itemStack.getItem());
                        if (subtypeQuantities.containsKey(itemId)) {
                            // Add all the item's subtypes
                            int subtypeQuantity = subtypeQuantities.get(itemId);
                            for (int i = 0; i < subtypeQuantity; i++) {
                                ItemStack subtype = new ItemStack(Item.getItemById(itemId));
                                subtype.setItemDamage(i);
                                megaItems.add(subtype);
                            }
                        } else {
                            // Just add the one
                            megaItems.add(itemStack);
                        }
                    }
                }
            }
        }
        return megaItems;
    }

    private static Map<Integer, Integer> createSubtypeQuantities() {
        Map<Integer, Integer> subtypeQuantities = new HashMap<Integer, Integer>();
        subtypeQuantities.put(322, 2);
        subtypeQuantities.put(3, 3);
        subtypeQuantities.put(5, 6);
        subtypeQuantities.put(6, 6);
        subtypeQuantities.put(263, 2);
        subtypeQuantities.put(139, 2);
        subtypeQuantities.put(12, 2);
        subtypeQuantities.put(397, 5);
        subtypeQuantities.put(144, 5);
        subtypeQuantities.put(17, 6);
        subtypeQuantities.put(145, 3);
        subtypeQuantities.put(18, 4);
        subtypeQuantities.put(24, 3);
        subtypeQuantities.put(155, 3);
        subtypeQuantities.put(349, 4);
        subtypeQuantities.put(350, 4);
        subtypeQuantities.put(31, 3);
        subtypeQuantities.put(95, 16);
        subtypeQuantities.put(159, 16);
        subtypeQuantities.put(351, 16);
        subtypeQuantities.put(160, 16);
        subtypeQuantities.put(97, 6);
        subtypeQuantities.put(161, 2);
        subtypeQuantities.put(98, 4);
        subtypeQuantities.put(162, 2);
        subtypeQuantities.put(35, 16);
        subtypeQuantities.put(38, 9);
        subtypeQuantities.put(43, 10);
        subtypeQuantities.put(171, 16);
        subtypeQuantities.put(44, 8);
        subtypeQuantities.put(175, 6);
        subtypeQuantities.put(125, 6);
        subtypeQuantities.put(126, 6);

        return subtypeQuantities;
    }

}
