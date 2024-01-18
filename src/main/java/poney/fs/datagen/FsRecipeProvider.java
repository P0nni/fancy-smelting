package poney.fs.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import poney.fs.block.FsBlocks;
import poney.fs.block.FsItems;

public class FsRecipeProvider extends FabricRecipeProvider {
    public FsRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        ShapedRecipeJsonBuilder
                .create(RecipeCategory.MISC, FsItems.FURNACE_ITEM,1)
                .pattern(" F ")
                .pattern("S S")
                .pattern("BYB")
                .input('S', Blocks.BLACKSTONE_STAIRS)
                .input('F',Blocks.BLACKSTONE_SLAB)
                .input('B',Blocks.BLACKSTONE)
                .input('Y',Blocks.IRON_BARS)
                .criterion(hasItem(Blocks.BLACKSTONE_STAIRS),conditionsFromItem(Blocks.BLACKSTONE_STAIRS))
                .criterion(hasItem(Blocks.BLACKSTONE_SLAB),conditionsFromItem(Blocks.BLACKSTONE_SLAB))
                .criterion(hasItem(Blocks.BLACKSTONE),conditionsFromItem(Blocks.BLACKSTONE))
                .criterion(hasItem(Blocks.IRON_BARS),conditionsFromItem(Blocks.IRON_BARS))
                .offerTo(exporter,new Identifier(getRecipeName(FsItems.FURNACE_ITEM)));

    }
}
