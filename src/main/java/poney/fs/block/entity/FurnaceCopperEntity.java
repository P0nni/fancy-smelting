package poney.fs.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import poney.fs.block.FsBlockEntities;
import poney.fs.util.enums.FurnaceLevel;

public class FurnaceCopperEntity extends AbstractFurnaceFsEntity{
    public FurnaceCopperEntity(BlockPos pos, BlockState state) {
        super(pos, state, FsBlockEntities.FURNACE_COPPER_BLOCK_ENTITY);
    }

    @Override
    public FurnaceLevel getFurnaceLevel() {
        return FurnaceLevel.COPPER;
    }
}
