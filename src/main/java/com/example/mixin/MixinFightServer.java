package com.example.mixin;


import com.example.ExampleMod;
import com.example.class_of_person.CustomClassData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

import static com.example.ExampleMod.sendStressToClient;


@Mixin(PlayerEntity.class)
public class MixinFightServer {

    @Inject(method = "damage", at = @At("HEAD"))
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getAttacker() instanceof HostileEntity) {
            if (this instanceof CustomClassData customData) {
                customData.setCountStress(Math.min(customData.getCountStress() + 15, 200));
                sendStressToClient((PlayerEntity) (Object) this);
            }
        }
    }
        //Этот метод для атаки игрока
    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void onAttack(Entity entity, CallbackInfo cir) {
        Random random = new Random();
        int rand = random.nextInt(100) + 1;
        PlayerEntity attackerPlayer = (PlayerEntity) (Object) this;
        if (entity instanceof PlayerEntity targetPlayer) {
            int equippedArmor = 0;
            for (ItemStack armor : targetPlayer.getArmorItems()) {
                if (!armor.isEmpty()) {
                    equippedArmor++;
                }
            }

            if (attackerPlayer instanceof CustomClassData attackerData && targetPlayer instanceof CustomClassData targetData) {
                attackerData.setCountStress(Math.max(attackerData.getCountStress() - 10, 0));
                sendStressToClient(attackerPlayer);

                if (!attackerData.getParty().equals(targetData.getParty())) {
                    return;
                }
                if (attackerData.getCountStress() == 100) {
                    if (rand > 70) {
                        attackerPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 1200, 0));
                        attackerPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200, 0));
                    } else {
                        attackerPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 1200, 0));
                        attackerPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 1200, 0));
                    }
                }

                if (attackerData.getCountStress() == 200) {
                    if (attackerPlayer.hasStatusEffect(StatusEffects.ABSORPTION) && attackerPlayer.hasStatusEffect(StatusEffects.STRENGTH)) {
                        attackerPlayer.removeStatusEffect(StatusEffects.ABSORPTION);
                        attackerPlayer.removeStatusEffect(StatusEffects.STRENGTH);
                        attackerData.setCountStress(0);
                    } else if (attackerPlayer.hasStatusEffect(StatusEffects.WEAKNESS) && attackerPlayer.hasStatusEffect(StatusEffects.SLOWNESS)) {
                        attackerPlayer.removeStatusEffect(StatusEffects.WEAKNESS);
                        attackerPlayer.removeStatusEffect(StatusEffects.SLOWNESS);
                        attackerData.setCountStress(0);
                        attackerPlayer.kill();
                    }
                }
            }

            switch (equippedArmor) {
                case 0 -> {
                    if (rand > 50) {
                        targetPlayer.addStatusEffect(new StatusEffectInstance(ExampleMod.effect, StatusEffectInstance.INFINITE, 0));
                    }
                }
                case 1, 2, 3, 4 -> {
                    int armorPoints = targetPlayer.getArmor();
                    if (armorPoints > 0 && armorPoints <= 10 && rand > 65) {
                        targetPlayer.addStatusEffect(new StatusEffectInstance(ExampleMod.effect, StatusEffectInstance.INFINITE, 0));
                    } else if (armorPoints > 10 && armorPoints <= 16 && rand > 70) {
                        targetPlayer.addStatusEffect(new StatusEffectInstance(ExampleMod.effect, StatusEffectInstance.INFINITE, 0));
                    } else if (armorPoints > 16 && armorPoints <= 22 && rand > 80) {
                        targetPlayer.addStatusEffect(new StatusEffectInstance(ExampleMod.effect, StatusEffectInstance.INFINITE, 0));
                    }
                }
            }
        }

        if (((Entity) (Object) this) instanceof HostileEntity) {
            assert attackerPlayer instanceof CustomClassData;
            ((CustomClassData) attackerPlayer).setCountStress(Math.max(((CustomClassData) attackerPlayer).getCountStress() - 10, 0));
                sendStressToClient(attackerPlayer);
        }

        if (attackerPlayer instanceof CustomClassData playerData) {
            if (entity instanceof HostileEntity) {
                playerData.setCountStress(Math.max(playerData.getCountStress() - 10, 0));
                sendStressToClient(attackerPlayer);
            }
        }
    }





}


