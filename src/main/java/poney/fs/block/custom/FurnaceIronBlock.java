package poney.fs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import poney.fs.block.FsBlockEntities;
import poney.fs.block.entity.FurnaceCopperEntity;
import poney.fs.block.entity.FurnaceIronEntity;
import poney.fs.util.enums.FurnaceLevel;

public class FurnaceIronBlock extends AbstractFurnaceFsBlock{

    private static final MapCodec<? extends BlockWithEntity> CODEC = createCodec(FurnaceIronBlock::new);

    public FurnaceIronBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public FurnaceLevel getFurnaceLevel() {
        return FurnaceLevel.IRON;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FurnaceIronEntity(pos, state);
    }

    @Nullable
    @Override
    public <A extends BlockEntity> BlockEntityTicker<A> getTicker(World world, BlockState state, BlockEntityType<A> type) {
        return validateTicker(type, FsBlockEntities.FURNACE_IRON_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
}
