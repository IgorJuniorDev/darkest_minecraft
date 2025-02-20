package com.example.mixin;

import com.example.ExampleMod;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class MaskMixin {
    @Inject(method = "equipStack", at = @At("HEAD"), cancellable = true)
    private void preventHelmetRemoval(EquipmentSlot slot, ItemStack stack, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (slot == EquipmentSlot.HEAD) {
            ItemStack currentHelmet = player.getEquippedStack(EquipmentSlot.HEAD);
            if (currentHelmet.isOf(ExampleMod.lepsoru_mask) && (stack.isEmpty() || !stack.isOf(ExampleMod.lepsoru_mask))) {
                ci.cancel();
            }
        }
    }
}
