package com.example.items;

import com.example.ExampleMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;


public class BandageItem extends Item {

    public BandageItem(Settings settings) {
        super(settings);
    }



    @Override
    public ActionResult useOnEntity(ItemStack stack,
                                     PlayerEntity user,
                                     LivingEntity entity,
                                     Hand hand)
    {
        World world = user.getEntityWorld();
        if(!world.isClient) {
            if (entity.hasStatusEffect(ExampleMod.effect)) {
                ExampleMod.LOGGER.info("Effect is active on the player.");
                entity.removeStatusEffect(ExampleMod.effect);
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.ENTITY_ARROW_HIT_PLAYER, SoundCategory.PLAYERS, 1.0F, 1.0F);
                if (!user.getAbilities().creativeMode) {
                    stack.decrement(1);
                }

                return ActionResult.SUCCESS;
            }
            ExampleMod.LOGGER.info("Effect is not active on the player.");
        }
        return ActionResult.PASS;

    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if(!world.isClient) {
            if (user.hasStatusEffect(ExampleMod.effect)) {
                user.removeStatusEffect(ExampleMod.effect);
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.ENTITY_ARROW_HIT_PLAYER, SoundCategory.PLAYERS, 1.0F, 1.0F);
                if (!user.getAbilities().creativeMode) {
                    stack.decrement(1);
                }

                return TypedActionResult.success(stack);
            }
        }
        return TypedActionResult.pass(stack);
    }




}
