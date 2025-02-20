package com.example.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class ArmorClass extends ArmorItem {
    public ArmorClass(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return false;
    }

    @Override
    public int getProtection()
    {
        return 2;
    }
}
