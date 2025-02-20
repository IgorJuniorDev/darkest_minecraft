package com.example.weapon;

import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;

public class LeperSword extends SwordItem {
    public LeperSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public float getAttackDamage()
    {
        return 7.0F;
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = super.getDefaultStack();
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putBoolean("Unbreakable", true);
        return stack;
    }

}
