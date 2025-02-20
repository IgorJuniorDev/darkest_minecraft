package com.example;

import com.example.class_of_person.CustomClassData;
import com.example.models.KnightHelmet;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.model.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import static com.example.ExampleMod.CLASS_SYNC_PACKET;


public class ExampleModClient implements ClientModInitializer {


	ModelPart root = KnightHelmet.getTexturedModelData().createModel();
	static Identifier texture = new Identifier("example", "textures/gui/stress.png");


	@Override
	public void onInitializeClient() {
        registerClassPacket();
		registerPackets();
		ArmorRenderer.register(new KnightHelmet(root), ExampleMod.crusader_helmet
		);
		EntityModelLayerRegistry.registerModelLayer(KnightHelmet.LAYER_LOCATION, KnightHelmet::getTexturedModelData);
		HudRenderCallback.EVENT.register(ExampleModClient::renderCounter);

	}
	private void registerPackets() {
		ClientPlayNetworking.registerGlobalReceiver(new Identifier("example", "update_stress"), (client, handler, buf, responseSender) -> {
			int stress = buf.readInt();
			client.execute(() -> {
				if (client.player instanceof CustomClassData player) {
					player.setCountStress(stress);
				}
			});
		});
	}

    public static void registerClassPacket() {
        ClientPlayNetworking.registerGlobalReceiver(CLASS_SYNC_PACKET, (client, handler, buf, responseSender) -> {
            String classOfPerson = buf.readString();
            client.execute(() -> {
                if (client.player instanceof CustomClassData player) {
                    player.setCustomClass(classOfPerson);
                    System.out.println("Класс установлен на клиенте: " + player.getCustomClass());
                }
            });
        });
    }


    public static void renderCounter(DrawContext context, float tickDelta) {
        String name = "";
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        PlayerEntity player = client.player;
        if (!(player instanceof CustomClassData customData)) return;
        String classOfPerson = customData.getCustomClass();
        int stress = customData.getCountStress();
        int xTextureCord = client.getWindow().getScaledWidth() / 2 + 80;
        int yTextureCord = client.getWindow().getScaledHeight() - 40;
        int xText = xTextureCord + 24;
        int yText = yTextureCord + 5;
        context.drawTexture(texture, xTextureCord, yTextureCord, 0, 0, 16, 16, 16, 16);
        context.drawText(client.textRenderer, ": " + stress, xText, yText, 0xFFFFFF, false);
        if (classOfPerson != null) {
             switch (classOfPerson) {
                 case "grave_robber" : name = "Grave Robber";
                    break;
                 case "crusader" : name =  "Crusader";
                    break;
                 case "leper" : name = "Leper";
                    break;
                 case "vestal" : name = "Vestal";
                    break;
                 case "plague_doctor" : name = "Plague Doctor";
                    break;
                 case "hellion" : name = "Hellion";
                    break;
                 case "highwayman" : name = "Highwayman";
                    break;
                 case "jester" : name = "Jester";
                    break;
                 case "houndmaster" : name = "Houndmaster";
                    break;
                 case "abomination" : name = "Abomination";
                    break;
                 case "occultist" : name = "Occultist";
                    break;
                 case "arbalest" : name = "Arbalest";
                    break;
                 case "man_at_arms" : name = "Man-at-Arms";
                    break;
                 case "antiquarian" : name = "Antiquarian";
                    break;
                 case "flagellant" : name = "Flagellant";
                    break;
                 case "shieldbreaker" : name = "Shieldbreaker";
                    break;
            }
        } else {
            name = "Unknown";
        }

        context.drawText(client.textRenderer, "Class: " + name, xText, yText - 10, 0xFFFFFF, false);
    }
}








