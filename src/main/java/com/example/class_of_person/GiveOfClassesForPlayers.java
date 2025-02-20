package com.example.class_of_person;

import net.minecraft.entity.player.PlayerEntity;
import java.util.*;

public class GiveOfClassesForPlayers {


    public static String getClassToUser(PlayerEntity player) {
        Random rand = new Random();
        String[] Types = {
                "leper", "plague_doctor", "crusader", "jester", "vestal",
                "highwayman", "abomination", "arbalest", "hound_master", "man_at_arms",
                "occultist", "shieldbreaker", "grave_robber", "flagellant", "musketeer", "mercenary"
        };
        int randInt = rand.nextInt(Types.length);
        return Types[randInt];
    }


    public static boolean canMark(PlayerEntity player) {
//        switch (((CustomClassData) player).getCustomClass()) {
//            case "arbalest", "occultist", "musketeer", "hound_master", "mercenary" :
//                return true;
//        }
        return true;
    }

}
