package com.example.effects;

import com.example.ExampleMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;


public class LepsoryEffectServer extends StatusEffect {
    public LepsoryEffectServer(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity != null) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600));
            entity.addStatusEffect(new StatusEffectInstance(ExampleMod.effect, 600));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 600));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 600, 1));
        }
    }

    public static StatusEffect register(StatusEffect e, String id)
    {
        return BleedEffectServer.register(e, id);
    }
}

