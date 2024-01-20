package poney.fs.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import poney.fs.block.FsBlockEntities;
import poney.fs.util.enums.FurnaceLevel;

public class FurnaceGoldEntity extends AbstractFurnaceFsEntity{
    public FurnaceGoldEntity(BlockPos pos, BlockState state) {
        super(pos, state, FsBlockEntities.FURNACE_GOLD_BLOCK_ENTITY);
    }


    @Override
    public FurnaceLevel getFurnaceLevel() {
        return FurnaceLevel.GOLD;
    }
}
