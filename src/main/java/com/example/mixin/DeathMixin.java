package com.example.mixin;


import com.example.ExampleMod;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class DeathMixin {
    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeath(DamageSource damageSource, CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
            if (player.getEquippedStack(EquipmentSlot.HEAD).equals(ExampleMod.lepsoru_mask))
            {
                player.getInventory().removeOne(player.getEquippedStack(EquipmentSlot.HEAD));
                player.equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
            }

    }
}
