package com.example.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class ArmorMaterialLeper implements ArmorMaterial {
    private static final int ENCHANTABILITY = 31;



    @Override
    public int getDurability(ArmorItem.Type type) {
        return 0;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        if (type == ArmorItem.Type.HELMET)
        {
            return 2;
        }
        if (type == ArmorItem.Type.CHESTPLATE)
        {
            return 5;
        }
        if (type == ArmorItem.Type.LEGGINGS)
        {
            return 4;
        }
        if (type == ArmorItem.Type.BOOTS)
        {
            return 2;
        }
        return 0;
    }

    @Override
    public int getEnchantability() {
        return ENCHANTABILITY;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }

    @Override
    public String getName() {
        return "example:lepsory_mask";
    }

    @Override
    public float getToughness() {
        return 2.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.1F;
    }
}
