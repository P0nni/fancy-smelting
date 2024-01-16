package poney.fs.block.custom;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;
import poney.fs.FancySmelting;
import poney.fs.block.FsBlockEntities;
import poney.fs.block.entity.FurnaceEntity;

import java.awt.*;
import java.util.List;

import static net.minecraft.client.util.ParticleUtil.spawnParticles;

public class FurnaceBlock extends BlockWithEntity implements BlockEntityProvider {

    private static final VoxelShape SHAPE = Block.createCuboidShape(0,0,0,16,32,16);
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = Properties.LIT;
    public static final IntProperty LIGHT = IntProperty.of("light",0,13);
    public static final EnumProperty<FurnaceFuel> FUEL = EnumProperty.of("fuel",FurnaceFuel.class);
    public static final MapCodec<FurnaceBlock> CODEC = createCodec(FurnaceBlock::new);
    public FurnaceBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, LIT,FUEL,LIGHT});
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("block." + FancySmelting.ID + ".furnace_fs.tooltip"));
        super.appendTooltip(stack, world, tooltip, options);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    /* BLOCK ENTITY */

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (!world.isClient) {

            NamedScreenHandlerFactory screenHandlerFactory = ((FurnaceEntity) world.getBlockEntity(pos));

            if(isEmptyFuel(state)){
                String pathItem = Registries.ITEM.getId(player.getStackInHand(hand).getItem()).getPath();
                if(pathItem.equals("lava_bucket")){
                   if(!player.isCreative()){
                       player.getStackInHand(hand).decrement(1);
                       if (!player.getInventory().insertStack(new ItemStack(Items.BUCKET))) {
                           // Se o inventário estiver cheio, você pode jogar o balde vazio no chão
                           player.dropItem(new ItemStack(Items.BUCKET), false);
                       }
                   }

                    changeProperties(world,pos,state,FurnaceFuel.LAVA,false,13);

                }
                else if(pathItem.equals("magma_block")){
                    if(!player.isCreative()) {
                        player.getStackInHand(hand).decrement(1);
                    }

                    changeProperties(world,pos,state,FurnaceFuel.MAGMA,false,13);
                }

            }
            else if (screenHandlerFactory != null && !isEmptyFuel(state) ) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(world.isClient()){
            if(!FurnaceBlock.isEmptyFuel(state)){
                if (random.nextFloat() < 0.11f) {
                    for (int i = 0; i < random.nextInt(2) + 2; ++i) {
                        spawnParticles(world, pos,state);
                    }
                }
            }
        }
        super.randomDisplayTick(state, world, pos, random);
    }

    private void spawnParticles(World world,BlockPos pos,BlockState state){

        Random random = world.getRandom();
        if(isLit(state)){
            world.addImportantParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true, (double)pos.getX() + 0.5 + random.nextDouble() / 3.0 * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + 1.8 + random.nextDouble() + random.nextDouble(), (double)pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (double)(random.nextBoolean() ? 1 : -1), 0.0, 0.07, 0.0);
        }

        DefaultParticleType fuelParticle = random.nextFloat() < 0.11 ? (random.nextFloat() < 0.11 ? ParticleTypes.FLAME : ParticleTypes.SMOKE) : null;
        if(fuelParticle != null) world.addParticle(fuelParticle, pos.getX() +0.5 + randomDouble(random) , pos.getY()+0.3 , pos.getZ() +0.8 + randomDouble(random) , 0.0, 0.0, 0.0);
    }

    private double randomDouble(Random random){
        return  (random.nextFloat() * random.nextBetween(-1,1) )* 0.25;
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FurnaceEntity(pos, state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof FurnaceEntity) {
                ItemScatterer.spawn(world, pos, (FurnaceEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Nullable
    @Override
    public <A extends BlockEntity> BlockEntityTicker<A> getTicker(World world, BlockState state, BlockEntityType<A> type) {
        return validateTicker(type, FsBlockEntities.FURNACE_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    public static Settings createFurnaceSettings() {
       return FabricBlockSettings.copyOf(Blocks.FURNACE)
                .luminance(state -> state.get(LIGHT))
                .nonOpaque()
                .requiresTool()
                .strength(4.0f);
    }

    public static boolean isEmptyFuel(BlockState state){
        return state.get(FUEL) == FurnaceFuel.EMPTY;
    }

    public static boolean isLit(BlockState state){
        return state.get(LIT);
    }

    public static FurnaceFuel getCurrentFuel(BlockState state){
        return state.get(FUEL);
    }

    public static void changeProperties(World world, BlockPos pos, BlockState state,FurnaceFuel newFuel,boolean lit, int luminance){
        BlockState newState = state;

        if(newFuel != state.get(FUEL)) newState = newState.with(FUEL,newFuel);
        if(lit != state.get(LIT)) newState = newState.with(LIT,lit);
        if(luminance != state.get(LIGHT)) newState = newState.with(LIGHT,luminance);

        world.setBlockState(pos,newState);
        world.updateListeners(pos,state,newState,3);

    }



}