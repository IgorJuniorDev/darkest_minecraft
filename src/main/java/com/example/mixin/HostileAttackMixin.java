package com.example.mixin;


import com.example.ExampleMod;
import com.example.class_of_person.CustomClassData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(LivingEntity.class)
public class HostileAttackMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getAttacker() instanceof HostileEntity) {
            if (source.getSource() instanceof PlayerEntity player && player instanceof CustomClassData customData) {
                customData.setCountStress(Math.min(customData.getCountStress() + 15, 200));
            }
        }
    }
}