package poney.fs.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

public class FurnaceInputSlot extends Slot {
    private final World world;

    public FurnaceInputSlot(Inventory inventory, int index, int x, int y, World world) {
        super(inventory, index, x, y);
        this.world = world;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        SimpleInventory inv = new SimpleInventory(stack);
        boolean validForSmelting = world != null && world.getRecipeManager().getFirstMatch(RecipeType.SMELTING, inv, world).isPresent();
        return validForSmelting && super.canInsert(stack);
    }
}