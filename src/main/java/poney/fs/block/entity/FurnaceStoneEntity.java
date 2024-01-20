package poney.fs.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poney.fs.block.FsBlockEntities;
import poney.fs.util.enums.FurnaceLevel;

public class FurnaceStoneEntity extends AbstractFurnaceFsEntity{
    public FurnaceStoneEntity(BlockPos pos, BlockState state) {
        super(pos, state, FsBlockEntities.FURNACE_STONE_BLOCK_ENTITY);
    }

}
