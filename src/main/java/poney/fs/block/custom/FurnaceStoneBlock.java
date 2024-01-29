package poney.fs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import poney.fs.block.entity.FurnaceStoneEntity;
import poney.fs.util.enums.FurnaceLevel;

public class FurnaceStoneBlock extends AbstractFurnaceFsBlock{

    private static final MapCodec<? extends BlockWithEntity> CODEC = createCodec(FurnaceStoneBlock::new);

    public FurnaceStoneBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FurnaceStoneEntity(pos,state);
    }
}
