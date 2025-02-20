package com.example.items;


import com.example.ExampleMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static com.example.ExampleMod.goldCoin;
import static com.example.ExampleMod.sets;


public class BagItem extends Item {


    public BagItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!(user.getWorld().isClient)) {
            if (user.getOffHandStack().getItem().equals(goldCoin) && user.getMainHandStack().getItem().equals(ExampleMod.bag)) {
                Integer currentCoins = ExampleMod.sets.getOrDefault(goldCoin, 0);
                ExampleMod.sets.put(goldCoin, currentCoins + user.getOffHandStack().getCount());
                user.getInventory().removeOne(user.getOffHandStack());
                user.sendMessage(Text.of("Coins on bag = " + ExampleMod.sets.get(goldCoin)));
                return TypedActionResult.success(user.getStackInHand(hand));
            }
            if (user.getOffHandStack().getItem().equals(ExampleMod.bag)) {
                ItemStack itemStack = new ItemStack(goldCoin, sets.get(goldCoin));
                user.giveItemStack(itemStack);
                user.sendMessage(Text.of("You receive = " + sets.get(goldCoin)));
                sets.put(goldCoin, 0);
                return TypedActionResult.success(user.getStackInHand(hand));
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
