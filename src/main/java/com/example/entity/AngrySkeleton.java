package com.example.entity;

import com.example.entity.ai.AngrySkeletonAttackGoal;
import net.minecraft.entity.EntityType;

import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.World;

public class AngrySkeleton extends HostileEntity {

    private static final TrackedData<Boolean> tracker = DataTracker.registerData(AngrySkeleton.class, TrackedDataHandlerRegistry.BOOLEAN);

    public AngrySkeleton(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.dataTracker.startTracking(tracker, true);
    }

    public static DefaultAttributeContainer.Builder angrySkeleton() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_ARMOR, 5).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 120).add(EntityAttributes.GENERIC_MAX_HEALTH, 20);
    }

    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(tracker);
    }




    @Override
    protected void initGoals()
    {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AngrySkeletonAttackGoal(this, 1.0D, true));
        this.goalSelector.add(1, new WanderAroundFarGoal(this, 0.5f));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.goalSelector.add(1, new RevengeGoal(this));

    }
}
