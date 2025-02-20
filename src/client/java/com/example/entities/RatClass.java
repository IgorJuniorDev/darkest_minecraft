package com.example.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.world.World;

public class RatClass extends SilverfishEntity {
    public RatClass(EntityType<? extends SilverfishEntity> entityType, World world) {
        super(entityType, world);
    }
}
