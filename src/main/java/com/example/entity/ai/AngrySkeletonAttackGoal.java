package com.example.entity.ai;

import com.example.entity.AngrySkeleton;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;

public class AngrySkeletonAttackGoal extends MeleeAttackGoal {

    private final AngrySkeleton skeleton;
    public int attackDelay = 20;
    private int speed = 20;
    private boolean alive = false;

    public AngrySkeletonAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
        skeleton = ((AngrySkeleton) mob);
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 20;
        speed = 20;
    }

    private boolean isEnemyInRange(LivingEntity entity) {
        return this.skeleton.distanceTo(entity) <= 2f;
    }

    @Override
    protected void attack(LivingEntity target, double squaredDistance) {
        if(isEnemyInRange(target)) {
            alive = true;
            if(this.speed <= attackDelay){
                skeleton.setAttacking(true);
            }
            if(this.speed <= 0)
            {
                this.mob.getLookControl().lookAt(target.getX(), target.getY(), target.getZ());
                tryAttack(target);
            }

        }
        else{
            resetCooldown();
            alive = false;
            skeleton.setAttacking(false);
            attackDelay = 0;
        }
    }

    protected void resetCooldown()
    {
        this.speed = this.getTickCount(attackDelay + 2);
    }

    protected void tryAttack(LivingEntity target) {
        this.resetCooldown();
        this.mob.setTarget(target);
        this.mob.tryAttack(target);
    }

    @Override
    public void tick() {
        super.tick();
        if(alive) {
            this.speed = Math.max(this.speed - 1, 0);
        }
    }

    @Override
    public void stop() {
        skeleton.setAttacking(false);
        super.stop();
    }
}

