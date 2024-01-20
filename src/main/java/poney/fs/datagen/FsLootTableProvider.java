package poney.fs.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import poney.fs.block.FsBlocks;


public class FsLootTableProvider extends FabricBlockLootTableProvider {

    public FsLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(FsBlocks.FURNACE_STONE_BLOCK);
        addDrop(FsBlocks.FURNACE_DIAMOND_BLOCK);
        addDrop(FsBlocks.FURNACE_IRON_BLOCK);
        addDrop(FsBlocks.FURNACE_GOLD_BLOCK);
        addDrop(FsBlocks.FURNACE_NETHERITE_BLOCK);
        addDrop(FsBlocks.FURNACE_EMERALD_BLOCK);
    }

}
