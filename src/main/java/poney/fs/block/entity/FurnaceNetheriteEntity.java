package poney.fs.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import poney.fs.block.FsBlockEntities;
import poney.fs.util.enums.FurnaceLevel;

public class FurnaceNetheriteEntity extends AbstractFurnaceFsEntity{
    public FurnaceNetheriteEntity(BlockPos pos, BlockState state) {
        super(pos, state, FsBlockEntities.FURNACE_NETHERITE_BLOCK_ENTITY);
    }


    @Override
    public FurnaceLevel getFurnaceLevel() {
        return FurnaceLevel.NETHERITE;
    }

}
