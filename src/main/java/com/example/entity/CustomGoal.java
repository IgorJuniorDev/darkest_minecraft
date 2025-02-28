package com.example.entity;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.ZombieEntity;

public class CustomGoal extends Goal {
    @Override
    public boolean canStart() {
        return true;
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }
}
