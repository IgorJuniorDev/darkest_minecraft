package com.example.items;

import com.example.ExampleModClient;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItem {



    public static Item register(Item item, String id)
    {
        Identifier itemId = Identifier.of("example", id);

        Item registeredItem = Registry.register(Registries.ITEM, itemId, item);

         return registeredItem;
    }
    public static void initialize() {

    }
}
