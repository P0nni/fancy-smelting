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
        addDrop(FsBlocks.FURNACE_BLOCK);
    }

}
