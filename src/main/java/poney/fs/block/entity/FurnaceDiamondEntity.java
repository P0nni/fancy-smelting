package poney.fs.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poney.fs.block.FsBlockEntities;
import poney.fs.util.enums.FurnaceLevel;

public class FurnaceDiamondEntity extends AbstractFurnaceFsEntity{

    public FurnaceDiamondEntity(BlockPos pos, BlockState state) {
        super(pos, state, FsBlockEntities.FURNACE_DIAMOND_BLOCK_ENTITY);
    }

    @Override
    public FurnaceLevel getFurnaceLevel() {
        return FurnaceLevel.DIAMOND;
    }

}
