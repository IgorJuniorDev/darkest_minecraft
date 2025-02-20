package com.example.class_of_person;

import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public interface CustomClassData {
    String getCustomClass();
    void setCustomClass(String customClass);
    int getCountStress();
    void setCountStress(int countStress);
    void changeStress(int amount);
    boolean isBlessed(PlayerEntity entity);
    Map<String, Map<Integer, PlayerEntity> > getParty();
    void setParty(Map<String, Map<Integer, PlayerEntity>> party);
    public Map<HostileEntity, Map<Item, Integer>> getQuests();

}
