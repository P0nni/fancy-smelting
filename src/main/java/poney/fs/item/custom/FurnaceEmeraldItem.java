package poney.fs.item.custom;

import net.minecraft.block.Block;
import poney.fs.util.enums.FurnaceLevel;

public class FurnaceEmeraldItem extends AbstractFurnaceFsItem{

    public FurnaceEmeraldItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public FurnaceLevel getFurnaceLevel() {
        return FurnaceLevel.EMERALD;
    }
}
