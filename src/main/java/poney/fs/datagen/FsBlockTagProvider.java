package poney.fs.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import poney.fs.block.FsBlocks;

import java.util.concurrent.CompletableFuture;

public class FsBlockTagProvider  extends FabricTagProvider.BlockTagProvider {
    public FsBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(FsBlocks.FURNACE_STONE_BLOCK)
                .add(FsBlocks.FURNACE_DIAMOND_BLOCK)
                .add(FsBlocks.FURNACE_COPPER_BLOCK)
                .add(FsBlocks.FURNACE_IRON_BLOCK)
                .add(FsBlocks.FURNACE_GOLD_BLOCK)
                .add(FsBlocks.FURNACE_EMERALD_BLOCK)
                .add(FsBlocks.FURNACE_NETHERITE_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(FsBlocks.FURNACE_GOLD_BLOCK)
                .add(FsBlocks.FURNACE_DIAMOND_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(FsBlocks.FURNACE_EMERALD_BLOCK)
                .add(FsBlocks.FURNACE_NETHERITE_BLOCK);
    }
}
