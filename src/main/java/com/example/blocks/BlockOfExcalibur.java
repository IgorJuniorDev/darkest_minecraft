package com.example.blocks;

import com.example.ExampleMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockOfExcalibur extends Block {
    public BlockOfExcalibur(AbstractBlock.Settings settings) {
        super(settings);
    }

    public static final BlockOfExcalibur blockOfExcalibur = new BlockOfExcalibur(
            AbstractBlock.Settings.create().mapColor(Blocks.STONE.getDefaultMapColor()).strength(Float.MAX_VALUE).nonOpaque()
    );

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = new ItemStack(ExampleMod.excalibur);
        if (!world.isClient && !(player.getInventory().contains(stack))) {
            player.giveItemStack(stack);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }


    public static void registerBlocks() {
        Registry.register(Registries.BLOCK, new Identifier("example", "excalibur_on_rocks"), blockOfExcalibur);
    }
}
