package com.example.models;

import com.example.entity.AngrySkeleton;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.util.Identifier;

public class SkeletonRenderer extends MobEntityRenderer<AngrySkeleton, SkeletonModel<AngrySkeleton>> {

    private static final Identifier texture = new Identifier("example", "textures/entity/skeleton.png");

    public SkeletonRenderer(EntityRendererFactory.Context context) {
        super(context,new SkeletonModel<>(context.getPart(ModModelLayers.layer)),  0.5f);
    }


    @Override
    public Identifier getTexture(AngrySkeleton entity) {
        return texture;
    }

    @Override
    public void render(AngrySkeleton mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(2, 2, 2);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
