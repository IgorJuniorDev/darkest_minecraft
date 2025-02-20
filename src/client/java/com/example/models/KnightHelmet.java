package com.example.models;


import com.example.ExampleMod;
import com.example.armor.ArmorClass;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.impl.client.rendering.ArmorRendererRegistryImpl;
import net.minecraft.client.model.*;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class KnightHelmet implements ArmorRenderer {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier("example", "textures/models/armor/crusader_helmet.png"), "main");
	public static ModelPart Head;

	public KnightHelmet(ModelPart root) {
		Head = root.getChild("Head");
	}


	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Head = modelPartData.addChild("Head", ModelPartBuilder.create().uv(0, 10).cuboid(-2.0F, 3.0F, -2.0F, 5.0F, 5.0F, 5.0F, new Dilation(0.0F))
				.uv(18, 14).cuboid(0.2F, 3.0F, -2.9F, 0.6F, 1.8F, 0.9F, new Dilation(0.0F))
				.uv(20, 12).cuboid(0.6F, 5.6F, -2.2F, 2.4F, 2.4F, 0.2F, new Dilation(0.0F))
				.uv(4, 1).cuboid(-2.0F, 5.6F, -2.2F, 2.4F, 2.4F, 0.2F, new Dilation(0.0F))
				.uv(2, 24).cuboid(-2.0F, 4.0F, -2.2F, 0.6F, 1.1F, 0.2F, new Dilation(0.0F))
				.uv(3, 24).cuboid(2.4F, 4.0F, -2.2F, 0.6F, 1.1F, 0.2F, new Dilation(0.0F))
				.uv(6, 6).mirrored().cuboid(-2.0F, 3.0F, -2.2F, 2.4F, 1.4F, 0.2F, new Dilation(0.0F)).mirrored(false)
				.uv(7, 8).cuboid(0.4F, 3.0F, -2.2F, 2.6F, 1.4F, 0.2F, new Dilation(0.0F))
				.uv(14, 21).cuboid(-2.1F, 5.6F, -2.1F, 0.2F, 2.4F, 2.4F, new Dilation(0.0F))
				.uv(19, 22).cuboid(-2.1F, 4.2F, -1.1F, 0.2F, 1.4F, 1.4F, new Dilation(0.0F))
				.uv(8, 18).cuboid(2.9F, 4.2F, -1.1F, 0.2F, 1.4F, 1.4F, new Dilation(0.0F))
				.uv(3, 22).mirrored().cuboid(-2.1F, 3.4F, -2.1F, 0.2F, 1.7F, 1.4F, new Dilation(0.0F)).mirrored(false)
				.uv(9, 10).cuboid(-2.1F, 2.9F, -2.1F, 5.2F, 0.5F, 1.4F, new Dilation(0.0F))
				.uv(1, 22).mirrored().cuboid(2.9F, 3.2F, -2.1F, 0.2F, 1.9F, 1.4F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 21).cuboid(2.9F, 5.6F, -2.1F, 0.2F, 2.4F, 2.4F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.0F, 0.0F));
		Head.addChild("cube_r1", ModelPartBuilder.create().uv(21, 24).cuboid(-0.1F, -0.4F, -0.4F, 0.4F, 0.8F, 0.8F, new Dilation(0.0F))
				.uv(14, 20).cuboid(0.0F, -0.7F, -0.7F, 0.3F, 1.4F, 1.4F, new Dilation(0.0F))
				.uv(16, 5).cuboid(5.1F, -0.4F, -0.4F, 0.4F, 0.8F, 0.8F, new Dilation(0.0F))
				.uv(12, 11).cuboid(5.1F, -0.7F, -0.7F, 0.3F, 1.4F, 1.4F, new Dilation(0.0F)), ModelTransform.of(-2.2F, 5.7F, 0.4F, -0.7854F, 0.0F, 0.0F));
		Head.addChild("cube_r2", ModelPartBuilder.create().uv(1, 18).cuboid(-1.4F, -1.4F, -0.5F, 2.4F, 1.8F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.8F, 4.4F, -2.0F, 0.0F, -0.3927F, 0.0F));
		Head.addChild("cube_r3", ModelPartBuilder.create().uv(0, 18).cuboid(-1.4F, -1.4F, -0.5F, 2.4F, 1.8F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 4.4F, -2.1F, 0.0F, 0.3927F, 0.0F));
		Head.addChild("cube_r4", ModelPartBuilder.create().uv(6, 18).cuboid(-1.4F, -1.4F, -0.5F, 2.4F, 2.4F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.2F, 7.0F, -2.2F, 0.0F, 0.3927F, 0.0F));
		Head.addChild("cube_r5", ModelPartBuilder.create().uv(4, 3).cuboid(-1.4F, -1.4F, -0.5F, 2.4F, 2.4F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.7F, 7.0F, -2.0F, 0.0F, -0.3927F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}


	private Identifier getTexture(LivingEntity entity) {
		return new Identifier("example", "textures/models/armor/crusader_helmet.png");
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, BipedEntityModel<LivingEntity> contextModel) {
		if (slot == EquipmentSlot.HEAD) {
			matrices.push();
			contextModel.getHead().rotate(matrices);
			if (entity instanceof PlayerEntity) {
				matrices.translate(-0.07, -3, -0.05);
				matrices.scale(2, 2, 2);
			} else if (entity instanceof ArmorStandEntity) {
				matrices.translate(-0.07, -3, -0.05);
				matrices.scale(2, 2, 2);
			}
			VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(getTexture(entity)));
			Head.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
			matrices.pop();
		}
	}
}

