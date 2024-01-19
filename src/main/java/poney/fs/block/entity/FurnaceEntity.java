package poney.fs.block.entity;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.*;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import poney.fs.block.FsBlockEntities;
import poney.fs.block.custom.FurnaceBlock;
import poney.fs.block.custom.FurnaceFuel;
import poney.fs.gui.FurnaceScreenHandler;
import poney.fs.util.MathFS;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;

import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class FurnaceEntity extends BlockEntity implements GeoBlockEntity, ExtendedScreenHandlerFactory, ImplementedInventory {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this,true);

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    private Text customName;
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    private int timeFuel = 0;

    public int ticks;
    public float currentAngle;
    public float nextAngle;
    private int sector;
    private boolean animating;
    private float animationAngleStart;
    private float animationAngleEnd;
    private double startTicks;
    private double playerAngle;

    public int maxTimefuel (){
        if(FurnaceBlock.isEmptyFuel(getCachedState())) return 0;

        return FurnaceBlock.getCurrentFuel(getCachedState()) == FurnaceFuel.LAVA ? 100000 : 50000;
    }

    private final Object2IntOpenHashMap<Identifier> recipesUsed = new Object2IntOpenHashMap<>();
    private final RecipeManager.MatchGetter<Inventory, ? extends AbstractCookingRecipe> matchGetter;

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    public FurnaceEntity(BlockPos pos, BlockState state) {
        super(FsBlockEntities.FURNACE_BLOCK_ENTITY, pos, state);
        matchGetter  = RecipeManager.createCachedMatchGetter(RecipeType.SMELTING);

        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> FurnaceEntity.this.progress;
                    case 1 -> FurnaceEntity.this.maxProgress;
                    case 2 -> FurnaceEntity.this.timeFuel;
                    case 3 -> FurnaceEntity.this.maxTimefuel();
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> FurnaceEntity.this.progress = value;
                    case 1 -> FurnaceEntity.this.maxProgress = value;
                    case 2 -> FurnaceEntity.this.timeFuel = value;
                }
            }

            @Override
            public int size() {
                return 4;
            }
        };
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public void tick(World world, BlockPos blockPos, BlockState blockState) {
        if(world.isClient()){
            ++this.ticks;
            if (isEmpty()) return;
            PlayerEntity player = world.getClosestPlayer((double)getPos().getX() + 0.5D, (double)getPos().getY() + 0.5D, (double)getPos().getZ() + 0.5D, 3.0D, false);
            if (player != null) {
                double d0 = player.getX() - ((double)getPos().getX() + 0.5D);
                double d1 = player.getZ() - ((double)getPos().getZ() + 0.5D);
                this.playerAngle = (Math.atan2(-d0, -d1) + 3.9269908169872414D) % 6.283185307179586D;
            }
            // most animation code is taken from the old RealBench mod (https://www.curseforge.com/minecraft/mc-mods/realbench)
            int sector = (int) (this.playerAngle * 2.0 / Math.PI);
            if (this.sector != sector) {
                this.animating = true;
                this.animationAngleStart = this.currentAngle;
                float delta1 = sector * 90.0F - this.currentAngle;
                float abs1 = Math.abs(delta1);
                float delta2 = delta1 + 360.0F;
                float shift = Math.abs(delta2);
                float delta3 = delta1 - 360.0F;
                float abs3 = Math.abs(delta3);
                if (abs3 < abs1 && abs3 < shift) {
                    this.animationAngleEnd = delta3 + this.currentAngle;
                } else if (shift < abs1 && shift < abs3) {
                    this.animationAngleEnd = delta2 + this.currentAngle;
                } else {
                    this.animationAngleEnd = delta1 + this.currentAngle;
                }
                this.startTicks = this.ticks;
                this.sector = sector;
            }
            if (this.animating) {
                if (this.ticks >= this.startTicks + 20) {
                    this.animating = false;
                    this.currentAngle = this.nextAngle = (this.animationAngleEnd + 360.0F) % 360.0F;
                } else {
                    this.currentAngle = (MathFS.easeOutQuad(this.ticks - this.startTicks, this.animationAngleStart, this.animationAngleEnd - this.animationAngleStart, 20.0) + 360.0F) % 360.0F;
                    this.nextAngle = (MathFS.easeOutQuad(Math.min(this.ticks + 1 - this.startTicks, 20), this.animationAngleStart, this.animationAngleEnd - this.animationAngleStart, 20.0) + 360.0F) % 360.0F;
                    if (this.currentAngle != 0.0F || this.nextAngle != 0.0F) {
                        if (this.currentAngle == 0.0F && this.nextAngle >= 180.0F) {
                            this.currentAngle = 360.0F;
                        }
                        if (this.nextAngle == 0.0F && this.currentAngle >= 180.0F) {
                            this.nextAngle = 360.0F;
                        }
                    }
                }
            }
            return;
        }

            if(!FurnaceBlock.isEmptyFuel(blockState)){
                this.increaseTimeFuel();
                if(timeFuel >= maxTimefuel()){
                    FurnaceBlock.changeProperties(world,blockPos,blockState,FurnaceFuel.EMPTY,false,0);
                    this.resetTimeFuel();
                }

                if(isOutputSlotEmptyOrReceivable()) {
                    if(this.hasRecipe()) {
                        maxProgress = getCookTime(world,this);
                        this.increaseCraftProgress();
                        markDirty(world, pos, blockState);

                        if(hasCraftingFinished()) {
                            this.craftItem();

                            this.resetCraftProgress();
                        }
                    } else {
                        this.resetCraftProgress();
                    }
                } else {
                    this.resetCraftProgress();
                    markDirty(world, pos, blockState);
                }
            }

    }

    @Override
    public boolean onSyncedBlockEvent(int type, int data) {
        return super.onSyncedBlockEvent(type, data);
    }

    private static int getCookTime(World world, FurnaceEntity furnace) {
        return furnace.matchGetter.getFirstMatch(furnace, world).map(recipe -> ((AbstractCookingRecipe)recipe.value()).getCookingTime()).orElse(200);
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }

    @Override
    public void markDirty() {
        assert world != null;
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("furnace_fs.progress", progress);
        nbt.putInt("furnace_fs.timeFuel", timeFuel);
        NbtCompound nbtCompound = new NbtCompound();
        recipesUsed.forEach((identifier, count) -> nbtCompound.putInt(identifier.toString(), (int)count));
        nbt.put("RecipesUsed", nbtCompound);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("furnace_fs.progress");
        timeFuel = nbt.getInt("furnace_fs.timeFuel");

        NbtCompound nbtCompound = nbt.getCompound("RecipesUsed");
        for (String string : nbtCompound.getKeys()) {
            recipesUsed.put(new Identifier(string), nbtCompound.getInt(string));
        }
    }

    public void dropExperienceForRecipesUsed(ServerPlayerEntity player) {
        List<RecipeEntry<?>> list = this.getRecipesUsedAndDropExperience(player.getServerWorld(), player.getPos());
        player.unlockRecipes((Collection<RecipeEntry<?>>)list);
        for (RecipeEntry<?> recipeEntry : list) {
            if (recipeEntry == null) continue;
            player.onRecipeCrafted(recipeEntry, this.inventory);
        }
        this.recipesUsed.clear();
    }

    public List<RecipeEntry<?>> getRecipesUsedAndDropExperience(ServerWorld world, Vec3d pos) {
        ArrayList<RecipeEntry<?>> list = Lists.newArrayList();
        for (Object2IntMap.Entry entry : this.recipesUsed.object2IntEntrySet()) {
            world.getRecipeManager().get((Identifier)entry.getKey()).ifPresent(recipe -> {
                list.add((RecipeEntry<?>)recipe);
                dropExperience(world, pos, entry.getIntValue(), ((AbstractCookingRecipe)recipe.value()).getExperience());
            });
        }
        return list;
    }

    private static void dropExperience(ServerWorld world, Vec3d pos, int multiplier, float experience) {
        int i = MathHelper.floor((float)multiplier * experience);
        float f = MathHelper.fractionalPart((float)multiplier * experience);
        if (f != 0.0f && Math.random() < (double)f) {
            ++i;
        }
        ExperienceOrbEntity.spawn(world, pos, i);
    }


    //SCREEN

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FurnaceScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void setCustomName(Text customName){
        this.customName = customName;
    }

    @Override
    public Text getDisplayName() {
        return customName != null ? customName : getCachedState().getBlock().getName();
    }

    public ItemStack getRenderStack() {
        if(this.getStack(OUTPUT_SLOT).isEmpty()) {
            return this.getStack(INPUT_SLOT);
        } else {
            return this.getStack(OUTPUT_SLOT);
        }
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress(){
        progress++;
    }

    private void resetCraftProgress(){
        progress = 0;
    }

    private void increaseTimeFuel(){
        timeFuel++;
    }
    private void resetTimeFuel(){
        timeFuel = 0;
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<SmeltingRecipe>> recipe = getCurrentRecipe();
        boolean hasRecipe = recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null))
                && canInsertItemIntoOutputSlot(recipe.get().value().getResult(null).getItem());

        if(hasRecipe && !FurnaceBlock.isLit(getCachedState())) {
            FurnaceBlock.changeProperties(world, getPos(), getCachedState(), FurnaceBlock.getCurrentFuel(getCachedState()), true, 13);
        }else if(!hasRecipe && FurnaceBlock.isLit(getCachedState())){
            FurnaceBlock.changeProperties(world, getPos(), getCachedState(), FurnaceBlock.getCurrentFuel(getCachedState()), false, 13);
        }

        return hasRecipe;
    }

    private Optional<RecipeEntry<SmeltingRecipe>> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }

        return getWorld().getRecipeManager().getFirstMatch(RecipeType.SMELTING, inv, getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

    private void craftItem() {
        Optional<RecipeEntry<SmeltingRecipe>> recipe = getCurrentRecipe();

        this.removeStack(INPUT_SLOT, 1);

        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUT_SLOT).getCount() + recipe.get().value().getResult(null).getCount()));

        setLastRecipe(recipe.get());
    }

    public void setLastRecipe(@Nullable RecipeEntry<?> recipe) {
        if (recipe != null) {
            Identifier identifier = recipe.id();
            this.recipesUsed.addTo(identifier, 1);
        }
    }

    @Nullable
    public RecipeEntry<?> getLastRecipe() {
        return null;
    }


    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
