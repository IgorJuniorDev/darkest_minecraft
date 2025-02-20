package com.example.mixin;

import com.example.class_of_person.CustomClassData;
import com.example.class_of_person.GiveOfClassesForPlayers;
import com.example.comands.CustomCommands;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;



@Mixin(PlayerEntity.class)


public class PlayerMixin implements CustomClassData {

    @Unique
    public String customClass = GiveOfClassesForPlayers.getClassToUser((PlayerEntity) (Object) this);

    @Unique
    public int stress = 0;

    @Override
    public String getCustomClass() {
        return customClass;
    }

    @Override
    public void setCustomClass(String newClass) {
        this.customClass = newClass;
    }

    @Override
    public int getCountStress() {
        return stress;
    }

    @Override
    public void setCountStress(int countStress) {
        this.stress = countStress;

    }

    @Override
    public void changeStress(int amount) {
        int newStress = getCountStress() + amount;
        setCountStress(Math.max(0, Math.min(newStress, 200)));
    }


    @Override
    public boolean isBlessed(PlayerEntity entity) {
        CustomClassData customClassData = (CustomClassData) entity;
        switch (customClassData.getCustomClass()) {
            case "crusader", "vestal", "plague_doctor":
                return true;
        }
        return false;
    }

    @Override
    public Map<String, Map<Integer, PlayerEntity>> getParty() {
        return CustomCommands.playersInParty;
    }

    @Override
    public void setParty(Map<String, Map<Integer, PlayerEntity>> party) {
        CustomCommands.playersInParty = party;
    }

    @Override
    public Map<HostileEntity, Map<Item, Integer>> getQuests() {
        return CustomCommands.quest;
    }


}
