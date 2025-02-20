package com.example.util;

import com.example.ExampleMod;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class CustomTrades {

    public static void registerVillagerOffers() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(ExampleMod.goldCoin, 10),
                    ItemStack.EMPTY,
                    new ItemStack(ExampleMod.lepsoru_mask, 1),
                    12,
                    2,
                    0.05F
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(ExampleMod.goldCoin, 15),
                    new ItemStack(ExampleMod.lepsoru_chestplate, 1),
                    5,
                    3,
                    0.1F
            ));
        });
    }
}