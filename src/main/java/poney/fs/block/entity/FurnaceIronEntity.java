package poney.fs.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import poney.fs.block.FsBlockEntities;
import poney.fs.util.enums.FurnaceLevel;

public class FurnaceIronEntity extends AbstractFurnaceFsEntity{
    public FurnaceIronEntity(BlockPos pos, BlockState state) {
        super(pos, state, FsBlockEntities.FURNACE_IRON_BLOCK_ENTITY);
    }


    @Override
    public FurnaceLevel getFurnaceLevel() {
        return FurnaceLevel.IRON;
    }
}
