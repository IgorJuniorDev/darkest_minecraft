package com.example.models;

import com.example.entity.AngrySkeleton;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class SkeletonModel<T extends AngrySkeleton> extends SinglePartEntityModel<T> {
    private final ModelPart skeleton;
    private final ModelPart edges;
    private final ModelPart bones;
    private final ModelPart leggs;
    public SkeletonModel(ModelPart root) {
        this.skeleton = root.getChild("skeleton");
        this.edges = this.skeleton.getChild("edges");
        this.bones = this.skeleton.getChild("bones");
        this.leggs = this.skeleton.getChild("leggs");
    }


    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData skeleton = modelPartData.addChild("skeleton", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -20.5F, -2.5F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData edges = skeleton.addChild("edges", ModelPartBuilder.create().uv(20, 16).cuboid(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(20, 17).cuboid(-4.0F, 0.0F, -3.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(20, 18).cuboid(-4.0F, 2.0F, -3.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(20, 19).cuboid(-1.0F, 2.0F, -3.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(20, 20).cuboid(-1.0F, 4.0F, -3.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(4, 21).cuboid(-4.0F, 4.0F, -3.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(4, 9).cuboid(-4.0F, 2.0F, -1.0F, 5.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(4, 10).cuboid(-4.0F, 4.0F, -1.0F, 5.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(4, 8).cuboid(-4.0F, 0.0F, -1.0F, 5.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -14.0F, 1.0F));

        ModelPartData cube_r1 = edges.addChild("cube_r1", ModelPartBuilder.create().uv(16, 20).cuboid(-0.75F, 0.0F, -1.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(20, 15).cuboid(-0.75F, 2.0F, -1.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(20, 14).cuboid(-0.75F, 4.0F, -1.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 0.0F, -2.25F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r2 = edges.addChild("cube_r2", ModelPartBuilder.create().uv(20, 13).cuboid(-0.75F, 0.0F, -1.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(20, 12).cuboid(-0.75F, -4.0F, -1.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(12, 20).cuboid(-0.75F, -2.0F, -1.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.0F, -2.25F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bones = skeleton.addChild("bones", ModelPartBuilder.create().uv(0, 8).cuboid(0.0F, -17.0F, -1.0F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r3 = bones.addChild("cube_r3", ModelPartBuilder.create().uv(20, 6).cuboid(1.0F, -0.8F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(20, 4).cuboid(-6.6F, -0.8F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -11.0F, -1.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData cube_r4 = bones.addChild("cube_r4", ModelPartBuilder.create().uv(20, 10).cuboid(1.0F, -0.8F, -0.6F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(20, 8).cuboid(-6.5F, -0.8F, -0.6F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -9.0F, -3.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData cube_r5 = bones.addChild("cube_r5", ModelPartBuilder.create().uv(0, 19).cuboid(0.0F, -1.3F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 17).cuboid(-7.5F, -1.3F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -10.0F, -1.0F, -0.7854F, 0.0F, 0.0F));

        ModelPartData cube_r6 = bones.addChild("cube_r6", ModelPartBuilder.create().uv(8, 11).cuboid(0.0F, -3.3F, -1.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -13.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

        ModelPartData cube_r7 = bones.addChild("cube_r7", ModelPartBuilder.create().uv(4, 11).cuboid(0.0F, -5.0F, -1.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -11.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

        ModelPartData cube_r8 = bones.addChild("cube_r8", ModelPartBuilder.create().uv(16, 4).cuboid(-2.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -14.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData cube_r9 = bones.addChild("cube_r9", ModelPartBuilder.create().uv(16, 0).cuboid(-2.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -14.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData leggs = skeleton.addChild("leggs", ModelPartBuilder.create().uv(20, 0).cuboid(-2.2F, -2.5F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(16, 12).cuboid(2.0F, -4.5F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, -1.0F));

        ModelPartData cube_r10 = leggs.addChild("cube_r10", ModelPartBuilder.create().uv(4, 17).cuboid(-0.7F, -12.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r11 = leggs.addChild("cube_r11", ModelPartBuilder.create().uv(16, 16).cuboid(-2.5F, -2.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -5.0F, 1.0F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r12 = leggs.addChild("cube_r12", ModelPartBuilder.create().uv(12, 11).cuboid(-2.0F, -3.5F, -1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, 2.0F, 0.3927F, 0.0F, 0.0F));

        ModelPartData cube_r13 = leggs.addChild("cube_r13", ModelPartBuilder.create().uv(12, 16).cuboid(-2.2F, -2.5F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        ModelPartData cube_r14 = leggs.addChild("cube_r14", ModelPartBuilder.create().uv(16, 8).cuboid(-2.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -4.0F, 1.0F, 0.0F, 0.0F, 1.5708F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        skeleton.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return skeleton;
    }

    @Override
    public void setAngles(AngrySkeleton skeleton, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}