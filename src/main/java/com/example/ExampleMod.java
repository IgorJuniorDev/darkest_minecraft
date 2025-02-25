package com.example;

import com.example.armor.ArmorClass;
import com.example.armor.ArmorMaterialCrusader;
import com.example.armor.ArmorMaterialLeper;
import com.example.blocks.BlockOfExcalibur;
import com.example.class_of_person.CustomClassData;
import com.example.class_of_person.GiveOfClassesForPlayers;
import com.example.comands.CustomCommands;
import com.example.effects.BleedEffectServer;
import com.example.effects.LepsoryEffectServer;
import com.example.effects.MarkEffect;
import com.example.items.BagItem;
import com.example.items.BandageItem;
import com.example.structures.ChurchStructure;
import com.example.weapon.*;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ExampleMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("bleed-and-steel");


	//Решить проблему с мобом, ссылка на гайд : https://www.youtube.com/watch?v=wgVnkqqBGFs&ab_channel=ModdingbyKaupenjoe
	public static final ArmorMaterialLeper LEPSORY_ARMOR_MATERIAL = new ArmorMaterialLeper();
	public static final CustomToolMaterial LEPSORY_TOOL_MATERIAL = new CustomToolMaterial();
	public static final ArmorMaterialCrusader armorMaterialCrusader = new ArmorMaterialCrusader();
	public  static Item goldCoin = new Item((new Item.Settings()));
	public static Item bandageItem = new BandageItem((new Item.Settings()));
	// Эффекты
	public static final MarkEffect markEffect = new MarkEffect(StatusEffectCategory.NEUTRAL, 1);
	public static final BleedEffectServer effect = new BleedEffectServer(StatusEffectCategory.HARMFUL, 0);
	public static final LepsoryEffectServer lepsory = new LepsoryEffectServer(StatusEffectCategory.NEUTRAL, 1);
	public static final CustomCrusaderToolMaterial CUSTOM_CRUSADER_TOOL_MATERIAL = new CustomCrusaderToolMaterial();
	public static final Excalibur excalibur_tool = new Excalibur();

	// Предметы
	public static final ArmorClass lepsoru_mask = new ArmorClass(LEPSORY_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings());
	public static final ArmorClass lepsoru_chestplate = new ArmorClass(LEPSORY_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings());
	public static final ArmorClass lepsoru_leggings = new ArmorClass(LEPSORY_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings());
	public static final ArmorClass lepsoru_boots = new ArmorClass(LEPSORY_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings());
	public static final LeperSword leper_sword = new LeperSword(LEPSORY_TOOL_MATERIAL, 2, -3.3F, new Item.Settings());
	public static final BagItem bag = new BagItem(new Item.Settings().maxCount(1));
	public static final Identifier CLASS_SYNC_PACKET = new Identifier("example", "update_class");

	public static final Excalibur2 excalibur = new Excalibur2(excalibur_tool, 0, -2.5F, new Item.Settings());
	public static final BlockItem item = new BlockItem(BlockOfExcalibur.blockOfExcalibur, new Item.Settings());

	public static final Feature<DefaultFeatureConfig> churchStructure = new ChurchStructure(DefaultFeatureConfig.CODEC);


	public static final ArmorClass crusader_helmet = new ArmorClass(armorMaterialCrusader, ArmorItem.Type.HELMET, new Item.Settings());
	public static final ArmorClass crusader_chestplate = new ArmorClass(armorMaterialCrusader, ArmorItem.Type.CHESTPLATE, new Item.Settings());
	public static final ArmorClass crusader_leggings = new ArmorClass(armorMaterialCrusader, ArmorItem.Type.LEGGINGS, new Item.Settings());
	public static final ArmorClass crusader_boots = new ArmorClass(armorMaterialCrusader, ArmorItem.Type.BOOTS, new Item.Settings());
	public static final CrusaderSword crusader_sword = new CrusaderSword(CUSTOM_CRUSADER_TOOL_MATERIAL, 0, -2.5F, new Item.Settings());
	public static Map<Item, Integer> sets = new HashMap<>();

	public static void sendStressToClient(PlayerEntity player) {
		int stress = ((CustomClassData) player).getCountStress();
		PacketByteBuf stressBuffer = new PacketByteBuf(Unpooled.buffer());
		stressBuffer.writeInt(stress);
		if (player instanceof ServerPlayerEntity entity) {
			ServerPlayNetworking.send(entity, new Identifier("example", "update_stress"), stressBuffer);
		}
	}

	public static void sendClassToClient(PlayerEntity player) {
		if(player instanceof CustomClassData data) {
			String classOfPerson = data.getCustomClass();
			if (classOfPerson == null)
			{
				return;
			}
			PacketByteBuf classBuf = new PacketByteBuf(Unpooled.buffer());
			classBuf.writeString(classOfPerson);
			if (player instanceof ServerPlayerEntity entity) {
				ServerPlayNetworking.send(entity, CLASS_SYNC_PACKET, classBuf);
			}
		}
	}


	public static ActionResult psychicalTest(PlayerEntity player) {
		Random random = new Random();
		if (player instanceof CustomClassData data) {
			int stress = data.getCountStress();
			if (stress >= 100 && stress < 200) {
				boolean hasAbsorption = player.hasStatusEffect(StatusEffects.ABSORPTION);
				boolean hasStrength = player.hasStatusEffect(StatusEffects.STRENGTH);
				boolean hasWeakness = player.hasStatusEffect(StatusEffects.WEAKNESS);
				boolean hasSlowness = player.hasStatusEffect(StatusEffects.SLOWNESS);

				if (!hasAbsorption && !hasStrength && !hasWeakness && !hasSlowness) {
					boolean isPositiveEffect = random.nextInt(100) > 70;
					if (isPositiveEffect) {
						if (!hasAbsorption) {
							player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, StatusEffectInstance.INFINITE, 0));
						}
						if (!hasStrength) {
							player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, StatusEffectInstance.INFINITE, 0));
						}
					} else {
						if (!hasWeakness) {
							player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, StatusEffectInstance.INFINITE, 0));
						}
						if (!hasSlowness) {
							player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, StatusEffectInstance.INFINITE, 0));
						}
					}
					return ActionResult.SUCCESS;
				}
			}
		}
		return ActionResult.PASS;
	}

	public static ActionResult checkStress(PlayerEntity player) {
		if (player instanceof CustomClassData customClassData) {
			int stress = customClassData.getCountStress();
			if (stress >= 200) {
				boolean hasAbsorption = player.hasStatusEffect(StatusEffects.ABSORPTION);
				boolean hasStrength = player.hasStatusEffect(StatusEffects.STRENGTH);
				boolean hasWeakness = player.hasStatusEffect(StatusEffects.WEAKNESS);
				boolean hasSlowness = player.hasStatusEffect(StatusEffects.SLOWNESS);

				if (hasAbsorption && hasStrength) {
					player.removeStatusEffect(StatusEffects.ABSORPTION);
					player.removeStatusEffect(StatusEffects.STRENGTH);
					customClassData.setCountStress(0);
					return ActionResult.SUCCESS;
				} else if (hasWeakness && hasSlowness) {
					player.removeStatusEffect(StatusEffects.WEAKNESS);
					player.removeStatusEffect(StatusEffects.SLOWNESS);
					customClassData.setCountStress(0);
					player.damage(player.getDamageSources().magic(), Float.MAX_VALUE);
					return ActionResult.SUCCESS;
				}
			}
		}
		return ActionResult.PASS;
	}




	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, new Identifier("example", "excalibur_on_rocks"), item);
		BlockOfExcalibur.registerBlocks();
		Registry.register(Registries.FEATURE, new Identifier("example", "church"), churchStructure);
		Registry.register(Registries.ITEM, new Identifier("example", "bag"), bag);
		Registry.register(Registries.ITEM, new Identifier("example", "crusader_sword"), crusader_sword);
		Registry.register(Registries.ITEM, new Identifier("example", "excalibur"), excalibur);
		Registry.register(Registries.ITEM, new Identifier("example", "crusader_helmet"), crusader_helmet);
		Registry.register(Registries.ITEM, new Identifier("example", "crusader_chestplate"), crusader_chestplate);
		Registry.register(Registries.ITEM, new Identifier("example", "crusader_leggings"), crusader_leggings);
		Registry.register(Registries.ITEM, new Identifier("example", "crusader_boots"), crusader_boots);
		Registry.register(Registries.ITEM, new Identifier("example", "bandage_server"), bandageItem);
		Registry.register(Registries.ITEM, new Identifier("example", "gold_coin_server"), goldCoin);
		Registry.register(Registries.ITEM, new Identifier("example", "leper_sword"), leper_sword);
		Registry.register(Registries.ITEM, new Identifier("example", "lepsory_mask"), lepsoru_mask);
		Registry.register(Registries.ITEM, new Identifier("example", "lepsory_chestplate"), lepsoru_chestplate);
		Registry.register(Registries.ITEM, new Identifier("example", "lepsory_leggings"), lepsoru_leggings);
		Registry.register(Registries.ITEM, new Identifier("example", "lepsory_boots"), lepsoru_boots);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("example", "bleed"), effect);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("example", "mark"), markEffect);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("example", "lepsory"), lepsory);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.add(leper_sword);
			entries.add(lepsoru_mask);
			entries.add(lepsoru_chestplate);
			entries.add(lepsoru_leggings);
			entries.add(lepsoru_boots);
			entries.add(crusader_helmet);
			entries.add(crusader_chestplate);
			entries.add(crusader_leggings);
			entries.add(crusader_boots);
			entries.add(crusader_sword);
		});
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (PlayerEntity player : server.getPlayerManager().getPlayerList()) {
				ItemStack offHandItem = player.getOffHandStack();

				if (!offHandItem.isEmpty() && player.getMainHandStack().getItem() instanceof SwordItem) {
					player.dropItem(offHandItem, true);
					player.getInventory().offHand.set(0, ItemStack.EMPTY);
				}
			}
		});
		ServerTickEvents.START_WORLD_TICK.register(world -> {
			for (PlayerEntity entity : world.getPlayers())
			{
					psychicalTest(entity);
					checkStress(entity);
			}
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.add(goldCoin);
			entries.add(bandageItem);
			entries.add(bag);
		});
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			ServerPlayerEntity player = handler.getPlayer();
			GiveOfClassesForPlayers giveOfClassesForPlayers = new GiveOfClassesForPlayers();
			if (player instanceof CustomClassData customData) {
				customData.setCustomClass(giveOfClassesForPlayers.getClassToUser(player));
			}
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			CustomCommands.register(dispatcher, registryAccess);
		});
		ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
			if (source.getAttacker() instanceof PlayerEntity attacker && entity instanceof PlayerEntity target) {
				if (attacker instanceof CustomClassData customAttacker && target instanceof CustomClassData customTarget) {
					if (customAttacker.getParty().equals(customTarget.getParty())) {
						attacker.sendMessage(Text.of("You can't bite your teammate! It's terrible!!"));
						return false;
					}
				}
			}
            return true;
        });
		ServerPlayNetworking.registerGlobalReceiver(new Identifier("example", "update_stress"), (server, player, handler, buf, responseSender) -> {
			sendStressToClient(player);
		});
		ServerPlayNetworking.registerGlobalReceiver(new Identifier("example", "update_class"), (server, player, handler, buf, responseSender) -> {
			sendClassToClient(player);
		});
		LOGGER.info("Hello Fabric world!");
	}



}
