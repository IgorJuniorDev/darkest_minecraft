package com.example.structures;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Optional;

public class ChurchStructure extends Feature<DefaultFeatureConfig> {
    public ChurchStructure(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }


    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        return true;
    }




}
