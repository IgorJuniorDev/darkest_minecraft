package com.example.sounds;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;


public class MenuSound {

    public static final Identifier register = new  Identifier("example", "the_hamlet");

    public static final SoundEvent HAMLET = SoundEvent.of(register);


}
