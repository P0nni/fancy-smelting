package poney.fs.item.custom;

import net.minecraft.block.Block;
import poney.fs.util.enums.FurnaceLevel;

public class FurnaceIronItem extends AbstractFurnaceFsItem{

    public FurnaceIronItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public FurnaceLevel getFurnaceLevel() {
        return FurnaceLevel.IRON;
    }
}
