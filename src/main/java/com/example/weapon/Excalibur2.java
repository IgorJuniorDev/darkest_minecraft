package com.example.weapon;


import com.example.class_of_person.CustomClassData;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.stat.Stat;

public class Excalibur2 extends SwordItem {

    public Excalibur2(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }



    @Override
    public float getAttackDamage()
    {
        PlayerEntity entity = (PlayerEntity) (Object) this;
        if(entity instanceof CustomClassData data && data.getCustomClass().equals("crusader")){
            entity.setHealth(10);
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, StatusEffectInstance.INFINITE, 1));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, StatusEffectInstance.INFINITE, 0));
            return 7.0F;
        }
        return 5.0F;
    }


}
