package com.example.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class ArmorMaterialCrusader implements ArmorMaterial {

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
            return 4;
        }
        if (type == ArmorItem.Type.LEGGINGS)
        {
            return 3;
        }
        if (type == ArmorItem.Type.BOOTS)
        {
            return 1;
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
        return "example:crusader_set";
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
