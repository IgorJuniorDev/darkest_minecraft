package com.example.mixin;

import com.example.ExampleMod;
import com.example.class_of_person.CustomClassData;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.example.ExampleMod.sendClassToClient;


@Mixin(ServerPlayerEntity.class)
public class PlayerClassMixin {


    @Inject(method = "onSpawn", at = @At("HEAD"),  cancellable = true)
    private void onPlayerRespawn(CallbackInfo ci) {
        sendClassToClient((ServerPlayerEntity) (Object) this);
        NbtCompound nbtCompound = ExampleMod.lepsoru_mask.getDefaultStack().getOrCreateNbt();
        NbtCompound nbtCompound2 = ExampleMod.lepsoru_chestplate.getDefaultStack().getOrCreateNbt();
        NbtCompound compound = ExampleMod.leper_sword.getDefaultStack().getOrCreateNbt();
        compound.putBoolean("Unbreakable", true);
        compound.putBoolean("NoDrop", true);
        nbtCompound.putBoolean("Unbreakable", true);
        nbtCompound2.putBoolean("Unbreakable", true);
        nbtCompound.putBoolean("NoDrop", true);
        nbtCompound2.putBoolean("NoDrop", true);
        PlayerEntity player = (ServerPlayerEntity) (Object) this;
        String className = ((CustomClassData) player).getCustomClass();
        switch (className) {
            case "leper":
                player.addStatusEffect(new StatusEffectInstance(ExampleMod.lepsory, StatusEffectInstance.INFINITE, 0));
                player.equipStack(EquipmentSlot.HEAD, ExampleMod.lepsoru_mask.getDefaultStack());
                player.equipStack(EquipmentSlot.CHEST, ExampleMod.lepsoru_chestplate.getDefaultStack());
                player.equipStack(EquipmentSlot.LEGS, ExampleMod.lepsoru_leggings.getDefaultStack());
                player.equipStack(EquipmentSlot.FEET, ExampleMod.lepsoru_boots.getDefaultStack());
                ItemStack stack = new ItemStack(ExampleMod.leper_sword);
                player.giveItemStack(stack);
                break;
            case "crusader":
                player.equipStack(EquipmentSlot.HEAD, ExampleMod.crusader_helmet.getDefaultStack());
                player.equipStack(EquipmentSlot.CHEST, ExampleMod.crusader_chestplate.getDefaultStack());
                player.equipStack(EquipmentSlot.LEGS, ExampleMod.crusader_leggings.getDefaultStack());
                player.equipStack(EquipmentSlot.FEET, ExampleMod.crusader_boots.getDefaultStack());
                ItemStack stack2 = new ItemStack(ExampleMod.crusader_sword);
                player.giveItemStack(stack2);
                break;
        }


    }


    }



