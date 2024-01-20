package poney.fs.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
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

import poney.fs.block.entity.AbstractFurnaceFsEntity;
import poney.fs.util.enums.FurnaceFuel;
import poney.fs.util.enums.FurnaceLevel;

import java.util.List;

public abstract class AbstractFurnaceFsBlock extends BlockWithEntity implements BlockEntityProvider {

    private static final VoxelShape SHAPE = Block.createCuboidShape(0,0,0,16,32,16);
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = Properties.LIT;
    public static final IntProperty LIGHT = IntProperty.of("light",0,13);
    public static final EnumProperty<FurnaceFuel> FUEL = EnumProperty.of("fuel",FurnaceFuel.class);
    public static final EnumProperty<FurnaceLevel> LEVEL = EnumProperty.of("level",FurnaceLevel.class);

    public AbstractFurnaceFsBlock(Settings settings) {
        super(settings);
    }

    public FurnaceLevel getFurnaceLevel(){
        return FurnaceLevel.STONE;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(LEVEL,getFurnaceLevel());
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, LIT,FUEL,LIGHT,LEVEL});
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

            NamedScreenHandlerFactory screenHandlerFactory = ((AbstractFurnaceFsEntity) world.getBlockEntity(pos));

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
            else if (screenHandlerFactory != null ) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(world.isClient()){
            if(!AbstractFurnaceFsBlock.isEmptyFuel(state)){
                if (random.nextFloat() < 0.11f) {
                    for (int i = 0; i < random.nextInt(2) + 2; ++i) {
                        particlesAndSounds(world, pos,state);
                    }
                }
            }
        }
        super.randomDisplayTick(state, world, pos, random);
    }

    private void particlesAndSounds(World world,BlockPos pos,BlockState state){

        Random random = world.getRandom();
        double d = (double)pos.getX() + 0.5;
        double e = pos.getY();
        double f = (double)pos.getZ() + 0.5;

        if(isLit(state)){
            world.addImportantParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true, (double)pos.getX() + 0.5 + random.nextDouble() / 3.0 * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + 1.8 + random.nextDouble() + random.nextDouble(), (double)pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (double)(random.nextBoolean() ? 1 : -1), 0.0, 0.07, 0.0);
        }

        if (random.nextDouble() < 0.1) {
            SoundEvent fuelSound = isLit(state) ? SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE : SoundEvents.BLOCK_LAVA_POP ;
            world.playSound(d, e, f, fuelSound, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
        }

        DefaultParticleType fuelParticle = random.nextFloat() < 0.11 ? (random.nextFloat() < 0.11 ? ParticleTypes.FLAME : ParticleTypes.SMOKE) : null;
        if(fuelParticle != null) world.addParticle(fuelParticle, pos.getX() +0.5 + randomDouble(random) , pos.getY()+0.3 , pos.getZ() +0.8 + randomDouble(random) , 0.0, 0.0, 0.0);
    }

    private double randomDouble(Random random){
        return  (random.nextFloat() * random.nextBetween(-1,1) )* 0.25;
    }


    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        BlockEntity blockEntity;
        if (itemStack.hasCustomName() && (blockEntity = world.getBlockEntity(pos)) instanceof AbstractFurnaceFsEntity) {
            ((AbstractFurnaceFsEntity)blockEntity).setCustomName(itemStack.getName());
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            return;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof AbstractFurnaceFsEntity) {
            if (world instanceof ServerWorld) {
                ItemScatterer.spawn(world, pos, (Inventory)((AbstractFurnaceFsEntity)blockEntity));
                ((AbstractFurnaceFsEntity)blockEntity).getRecipesUsedAndDropExperience((ServerWorld)world, Vec3d.ofCenter(pos));
            }
            super.onStateReplaced(state, world, pos, newState, moved);
            world.updateComparators(pos, this);
        } else {
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }


    public static Settings createFurnaceSettings() {
       return Settings.copy(Blocks.STONE)
               .strength(3.5f)
               .requiresTool()
               .sounds(BlockSoundGroup.STONE)
                .luminance(state -> state.get(LIGHT))
                .nonOpaque();
    }

    public static boolean isEmptyFuel(BlockState state){
        return state.get(FUEL) == FurnaceFuel.EMPTY;
    }

    public static boolean isLit(BlockState state){
        return state.get(LIT);
    }

    public static FurnaceLevel getLevel(BlockState state){
        return state.get(LEVEL);
    }

    public static FurnaceFuel getCurrentFuel(BlockState state){
        return state.get(FUEL);
    }

    public static Direction getDirection(BlockState state){return state.get(FACING);}

    public static void changeProperties(World world, BlockPos pos, BlockState state,FurnaceFuel newFuel,boolean lit, int luminance){
        BlockState newState = state;

        if(newFuel != state.get(FUEL)) newState = newState.with(FUEL,newFuel);
        if(lit != state.get(LIT)) newState = newState.with(LIT,lit);
        if(luminance != state.get(LIGHT)) newState = newState.with(LIGHT,luminance);

        world.setBlockState(pos,newState);
        world.updateListeners(pos,state,newState,3);
    }

    @Nullable
    @Override
    public <A extends BlockEntity> BlockEntityTicker<A> getTicker(World world, BlockState state, BlockEntityType<A> type) {
        return validateTicker(type, FsBlockEntities.FURNACE_STONE_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

}
