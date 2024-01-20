package poney.fs.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import poney.fs.block.FsBlockEntities;
import poney.fs.util.enums.FurnaceLevel;

public class FurnaceEmeraldEntity extends AbstractFurnaceFsEntity{
    public FurnaceEmeraldEntity(BlockPos pos, BlockState state) {
        super(pos, state, FsBlockEntities.FURNACE_EMERALD_BLOCK_ENTITY);
    }


    @Override
    public FurnaceLevel getFurnaceLevel() {
        return FurnaceLevel.EMERALD;
    }

}
