package com.example.comands;

import com.example.ExampleMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomHostileMob {
    public static HostileEntity getRandomHostileEntity(World world) {
        List<EntityType<?>> hostileEntities = Registries.ENTITY_TYPE.stream()
                .filter(type -> type.create(world) instanceof HostileEntity)
                .toList();

        if (hostileEntities.isEmpty()) return null;

        EntityType<?> randomType = hostileEntities.get(Random.create().nextInt(hostileEntities.size()));
        return (HostileEntity) randomType.create(world);
    }

    public Map<HostileEntity, Map<Item, Integer>> addToListOfTasks(World world) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        HostileEntity hostileEntity = getRandomHostileEntity(world);
        Map<Item, Integer> reward = new HashMap<>(1);
        java.util.Random random = new java.util.Random();
        Integer randInt  = random.nextInt(128);
        if(hostileEntity != null) {
            for (int i = 0; i < CustomCommands.quest.size(); i++) {
                reward.put(ExampleMod.goldCoin, randInt);
                CustomCommands.quest.putIfAbsent(hostileEntity, reward);
                return CustomCommands.quest;
            }
        }
        return null;
    }
}

