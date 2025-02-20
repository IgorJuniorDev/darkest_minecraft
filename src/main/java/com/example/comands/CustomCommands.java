package com.example.comands;

import com.example.ExampleMod;
import com.example.class_of_person.CustomClassData;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.Map;

public class CustomCommands {

    public static Map<String, Map<Integer, PlayerEntity>> playersInParty = new HashMap<>(3);
    public static Map<Integer, PlayerEntity> players = new HashMap<>();
    public static Map<HostileEntity, Map<Item, Integer>>  quest = new HashMap<>(10);

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(
                CommandManager.literal("createteam")
                        .executes(context -> {
                            PlayerEntity playerEntity = context.getSource().getPlayer();
                            players.put(0, playerEntity);
                            playersInParty.put(playerEntity.getName().getString(), players);
                            playerEntity.sendMessage(Text.of("You have created a team from " + playersInParty.size() + " players with a name " + playerEntity.getName().getString()));
                            return Command.SINGLE_SUCCESS;
                        })
        );

        dispatcher.register(
                CommandManager.literal("addtoteam")
                        .requires(source -> source.hasPermissionLevel(0))
                        .then(CommandManager.argument("player", StringArgumentType.string())
                                .executes(context -> {
                            PlayerEntity playerEntity = context.getSource().getPlayer();
                            if(playerEntity.getName().getString().equals(StringArgumentType.getString(context, "player")) && !(playersInParty.isEmpty()) && !(playersInParty.containsKey(playerEntity.getName().getString()))) {
                                players.put(players.size() + 1, playerEntity);
                                playersInParty.put(playerEntity.getName().getString(), players);
                                Map<Integer, PlayerEntity> buff =  playersInParty.get(players.get(0).getName().getString());
                                playerEntity.sendMessage(Text.of("Ypu added " + playerEntity.getName().getString() + " to party of" + buff.get(0).getName().getString()));
                                return Command.SINGLE_SUCCESS;
                            }
                                    playerEntity.sendMessage(Text.of("We can't find this player!"));
                                    return 0;
                        })
        ));
        dispatcher.register(
                CommandManager.literal("dataofteam")
                        .executes(context -> {
                            PlayerEntity entity = context.getSource().getPlayer();
                            if (playersInParty != null && !playersInParty.isEmpty()) {
                                StringBuilder playerNames = new StringBuilder();
                                for (Map.Entry<Integer, PlayerEntity> entry : players.entrySet()) {
                                    PlayerEntity member = entry.getValue();
                                    if (playerNames.length() > 0) {
                                        playerNames.append(", ");
                                    }
                                    playerNames.append(member.getName().getString());
                                }

                                entity.sendMessage(Text.of("In your team are " + playersInParty.size() + " players with names: " + playerNames), false);
                            } else {
                                entity.sendMessage(Text.of("No players found in your team."), false);
                            }

                            return Command.SINGLE_SUCCESS;
                        })
        );
        /*dispatcher.register(
                CommandManager.literal("listofquests")
                        .requires(source -> source.hasPermissionLevel(0))
                                .executes(context -> {
                                    PlayerEntity entity = context.getSource().getPlayer();
                                    quest.forEach((name, description) -> {
                                        context.getSource().sendFeedback(() -> Text.literal(name + ": " + description), false);
                                    });
                                    entity.sendMessage(Text.of("You have " + quest.size() + " quests"));
                                    return Command.SINGLE_SUCCESS;
                                }
                        ));*/
        dispatcher.register(
                CommandManager.literal("deletefromteam")
                        .requires(source -> source.hasPermissionLevel(0))
                        .then(CommandManager.argument("player", StringArgumentType.string())
                                .executes(context -> {
                                    PlayerEntity playerEntity = context.getSource().getPlayer();
                                    if(playerEntity.getName().getString().equals(StringArgumentType.getString(context, "player")) && !(playersInParty.isEmpty())) {
                                        playersInParty.remove(StringArgumentType.getString(context, "player"));
                                        playerEntity.sendMessage(Text.of(playerEntity.getName().getString() + "had been removed from team"));
                                        return Command.SINGLE_SUCCESS;
                                    }
                                    playerEntity.sendMessage(Text.of("We can't find this player!"));
                                    return 0;
                                })
                        ));
        dispatcher.register(
                CommandManager.literal("gettypeofunite")
                        .executes(context -> {
                            PlayerEntity playerEntity = context.getSource().getPlayer();

                            if (playerEntity == null) {
                                context.getSource().sendError(Text.of("Error!"));
                                return Command.SINGLE_SUCCESS;
                            }

                            String playerClass = ((CustomClassData) playerEntity).getCustomClass();
                            playerEntity.sendMessage(Text.of("You have a class: " + playerClass), false);

                            return Command.SINGLE_SUCCESS;
                        })
        );
        dispatcher.register(
                CommandManager.literal("getstress")
                        .executes(context -> {
                            PlayerEntity playerEntity = context.getSource().getPlayer();

                            if (playerEntity instanceof CustomClassData user) {
                                user.changeStress(10);
                                playerEntity.sendMessage(Text.of("Your new stress value is: " + user.getCountStress()), false);
                            }

                            return Command.SINGLE_SUCCESS;
                        })
        );
        dispatcher.register(
                CommandManager.literal("stresscount")
                        .executes(context -> {
                            PlayerEntity playerEntity = context.getSource().getPlayer();

                            if (playerEntity instanceof CustomClassData user) {

                                playerEntity.sendMessage(Text.of(String.valueOf(user.getCountStress())), false);
                            }

                            return Command.SINGLE_SUCCESS;
                        })
        );
    }
}
