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
import poney.fs.block.entity.FurnaceGoldEntity;
import poney.fs.util.enums.FurnaceLevel;

public class FurnaceGoldBlock extends AbstractFurnaceFsBlock{

    private static final MapCodec<? extends BlockWithEntity> CODEC = createCodec(FurnaceGoldBlock::new);

    public FurnaceGoldBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public FurnaceLevel getFurnaceLevel() {
        return FurnaceLevel.GOLD;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FurnaceGoldEntity(pos, state);
    }

    @Nullable
    @Override
    public <A extends BlockEntity> BlockEntityTicker<A> getTicker(World world, BlockState state, BlockEntityType<A> type) {
        return validateTicker(type, FsBlockEntities.FURNACE_GOLD_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
}
