package poney.fs.gui;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import poney.fs.block.entity.FurnaceEntity;

public class FurnaceScreenHandler extends ScreenHandler {

    private final Inventory furnaceInventory;
    private final PropertyDelegate propertyDelegate;
    public final FurnaceEntity blockEntity;

    public FurnaceScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(4));
    }

    public FurnaceScreenHandler(int syncId, PlayerInventory playerInventory,
                                     BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(FsScreens.FURNACE_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), 2);
        this.furnaceInventory = ((Inventory) blockEntity);
        furnaceInventory.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = ((FurnaceEntity) blockEntity);

        this.addSlot(new FurnaceInputSlot(furnaceInventory, 0, 56, 35,blockEntity.getWorld()));
        this.addSlot(new FurnaceOutputSlot(playerInventory.player, furnaceInventory, 1, 116,35));


        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(arrayPropertyDelegate);
    }



    protected boolean isSmeltable(ItemStack itemStack) {
        return blockEntity.getWorld().getRecipeManager().getFirstMatch(RecipeType.SMELTING, new SimpleInventory(itemStack), this.blockEntity.getWorld()).isPresent();
    }


    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }



    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowSize = 22; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getScaledFuel() {
        int time = this.propertyDelegate.get(2);
        int maxTime = this.propertyDelegate.get(3);  // Max Time
        int fuelSize = 58; // Esta é a largura em pixels do seu combustível

        return maxTime != 0 && time != 0 ? (maxTime - time) * fuelSize / maxTime : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.furnaceInventory.size()) {
                if (!this.insertItem(originalStack, this.furnaceInventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.furnaceInventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        dropInventory(player,furnaceInventory);
        super.onClosed(player);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.furnaceInventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    public Text getTitle(){
        return blockEntity.getDisplayName();
    }


    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
