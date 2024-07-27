# Mega Randomizer 1.7.10

This is the 1.7.10 version of my [Mega Randomizer](https://github.com/stevefali/MegaRandomizer) Minecraft mod.

This mod comes with custom game rules that allow you to individually turn on or off item drop randomization for
Blocks, Entities or Players. (More to come in future versions!)

### The repositories for the other versions of Mega Randomizer can be found here:
- [Main version](https://github.com/stevefali/MegaRandomizer)
- [1.16.5](https://github.com/stevefali/MegaRandomizer1.16.5)
- [1.12.10](https://github.com/stevefali/MegaRandomizer1.12.10)


## Here is a high-level overview of how the code works
- There are 3 new custom game rules, doBlockRandomDrops, doEntityRandomDrops, and doPlayerRandomDrops. These can be turned on or off using normal gamerule commands, or using the custom GUI menu.
- There is a custom menu screen with buttons for turning the drop randomization types on or off.
    - The event for the in-game menu (the pause screen) is intercepted so that I can instead show my customized version of the pause screen that includes a button for the Mega Randomizer menu screen.
    - That button is placed in the second-to-last place on the screen, so as to maintain the normal UX flow of the pause screen, i.e., the button for returning to the main menu is on the bottom.
    - The Mega Randomizer options screen runs only on the client, so network packets are used to communicate with the server to update the custom game rules.
    - When the Mega Randomizer options screen is called, it also sends a request to the server to update the client with the current state of the 3 custom game rules.
- Item drop randomization is achieved by listening for a drop event, checking whether the relevant custom game rule is on or off, and using the event object to replace the vanilla drops with the randomized ones.
- The actual randomization of all drops is achieved by:
    - Getting a copy of the list of all registered items at runtime. This allows the mod to include randomization of drop items that are added by other Forge mods that may be running.
       - That list does not include "subtypes" of items, that is, items such as different types of logs or leaves, different colors of wool, etc.
       - Those "subtype" items all have the same ID and key as the original item, so in order to add them to the list, each ID is checked against a hash map of ID's of items that have subtypes.
          - That hashmap includes the number of subtypes for that item, which can then be used to apply the "itemDamage" attribute, which is what differentiates each subtype. For most items, the "itemDamage" values are chronological, i.e., if there are 16 subtypes of an item, the "itemDamage" will range from 0 to 15 inclusive. This makes it easy to add those items to the main list by simply iterating over them. The exception is potions, which I chose to ignore for now.
    - Once the list is complete, items that I don't want to be obtainable as drops (spawn eggs, debug stick, command blocks, etc.) are removed from the list.
    - Another copy of the list is made and then shuffled using the game seed to produce a randomly-ordered list that is unique to that world.
    - When an item drop is triggered, the vanilla drop's index in the master list is obtained, and then the item at that index in the shuffled list is returned to replace the vanilla item.
 
## Help Support Mega Randomizer
If you like Mega Randomizer, consider buying me a cup of coffee! Your generosity helps keep this project going.

[![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/H2H5103UQ6)
