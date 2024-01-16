package poney.fs.gui;

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
        // Verifique se o item pode ser utilizado em uma receita de fusão
        boolean validForSmelting = this.world != null && this.world.getRecipeManager().getFirstMatch(RecipeType.SMELTING, inventory, this.world).isPresent();

        // Permita a inserção se for válido para fusão, caso contrário, bloqueie
        return validForSmelting;
    }
}