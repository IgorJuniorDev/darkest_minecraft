package com.example.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface BleedCallback
{
    Event<BleedCallback> EVENT = EventFactory.createArrayBacked(BleedCallback.class,
            (listeners) -> (player) -> {
                for (BleedCallback listener : listeners) {
                    ActionResult result = listener.interact(player);

                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult interact(PlayerEntity player);
}
