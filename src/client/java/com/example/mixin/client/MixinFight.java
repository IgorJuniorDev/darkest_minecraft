package com.example.mixin.client;


import com.example.ExampleMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;


@Mixin(PlayerEntity.class)
public class MixinFight {

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void onAttack(Entity entity, CallbackInfo cir) {
        if(!(entity instanceof SkeletonEntity)) {
            if (entity instanceof LivingEntity) {
                Random random = new Random();
                int randomNum = random.nextInt(100) + 1;
                LivingEntity target = (LivingEntity) entity;
                Iterable<ItemStack> setOfArmor = target.getArmorItems();
                int count = 0;
                for (ItemStack armor : setOfArmor) {
                    if (!armor.isEmpty()) {
                        count++;
                    }
                }
                switch (count) {
                    case 0:
                        if (randomNum > 50) {
                            target.addStatusEffect(new StatusEffectInstance(ExampleMod.effect, 600, 0));
                        }
                        break;
                    case 1:
                        if (randomNum > 55 && target.getArmor() > 6 && target.getArmor() <= 12) {
                            target.addStatusEffect(new StatusEffectInstance(ExampleMod.effect, 600, 0));
                        }
                        break;
                    case 2:
                        if (randomNum > 65 && target.getArmor() > 12 && target.getArmor() <= 18) {
                            target.addStatusEffect(new StatusEffectInstance(ExampleMod.effect, 600, 0));
                        }
                        break;
                    case 3:
                        if (randomNum > 75 && target.getArmor() > 18 && target.getArmor() <= 22) {
                            target.addStatusEffect(new StatusEffectInstance(ExampleMod.effect, 600, 0));
                        }
                        break;
                    case 4:
                        if (((LivingEntity) entity).getArmor() > 0 && ((LivingEntity) entity).getArmor() <= 10) {
                            if (randomNum > 60) {
                                target.addStatusEffect(new StatusEffectInstance(ExampleMod.effect, 600, 0));
                                break;
                            }
                        } else if (((LivingEntity) entity).getArmor() > 10 && ((LivingEntity) entity).getArmor() <= 16) {
                            if (randomNum > 70) {
                                target.addStatusEffect(new StatusEffectInstance(ExampleMod.effect, 600, 0));
                                break;
                            }
                        } else if (((LivingEntity) entity).getArmor() > 16 && ((LivingEntity) entity).getArmor() <= 22) {
                            if (randomNum > 90) {
                                target.addStatusEffect(new StatusEffectInstance(ExampleMod.effect, 600, 0));
                                break;
                            }
                        }
                        break;
                }
            }
        }
    }
}
