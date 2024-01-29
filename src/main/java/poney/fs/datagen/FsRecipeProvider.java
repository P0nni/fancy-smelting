package poney.fs.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import poney.fs.item.FsItems;

public class FsRecipeProvider extends FabricRecipeProvider {
    public FsRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        ShapedRecipeJsonBuilder
                .create(RecipeCategory.MISC, FsItems.FURNACE_STONE_ITEM,1)
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
                .offerTo(exporter,new Identifier(getRecipeName(FsItems.FURNACE_STONE_ITEM)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FsItems.FURNACE_UPGRADE_COPPER_TEMPLATE,2)
                .pattern("OOO").pattern("OXO").pattern("OOO")
                .input('O', Items.COPPER_INGOT).input('X',FsItems.FURNACE_UPGRADE_COPPER_TEMPLATE)
                .criterion(hasItem(FsItems.FURNACE_UPGRADE_COPPER_TEMPLATE),conditionsFromItem(FsItems.FURNACE_UPGRADE_COPPER_TEMPLATE))
                .criterion(hasItem(Items.COPPER_INGOT),conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter,new Identifier(getRecipeName(FsItems.FURNACE_UPGRADE_COPPER_TEMPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FsItems.FURNACE_UPGRADE_IRON_TEMPLATE,2)
                .pattern("OOO").pattern("OXO").pattern("OOO")
                .input('O', Items.IRON_INGOT).input('X',FsItems.FURNACE_UPGRADE_IRON_TEMPLATE)
                .criterion(hasItem(FsItems.FURNACE_UPGRADE_IRON_TEMPLATE),conditionsFromItem(FsItems.FURNACE_UPGRADE_IRON_TEMPLATE))
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter,new Identifier(getRecipeName(FsItems.FURNACE_UPGRADE_IRON_TEMPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FsItems.FURNACE_UPGRADE_GOLD_TEMPLATE,2)
                .pattern("OOO").pattern("OXO").pattern("OOO")
                .input('O', Items.GOLD_INGOT).input('X',FsItems.FURNACE_UPGRADE_GOLD_TEMPLATE)
                .criterion(hasItem(FsItems.FURNACE_UPGRADE_GOLD_TEMPLATE),conditionsFromItem(FsItems.FURNACE_UPGRADE_GOLD_TEMPLATE))
                .criterion(hasItem(Items.GOLD_INGOT),conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter,new Identifier(getRecipeName(FsItems.FURNACE_UPGRADE_GOLD_TEMPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FsItems.FURNACE_UPGRADE_DIAMOND_TEMPLATE,2)
                .pattern("OOO").pattern("OXO").pattern("OOO")
                .input('O', Items.DIAMOND).input('X',FsItems.FURNACE_UPGRADE_DIAMOND_TEMPLATE)
                .criterion(hasItem(FsItems.FURNACE_UPGRADE_DIAMOND_TEMPLATE),conditionsFromItem(FsItems.FURNACE_UPGRADE_DIAMOND_TEMPLATE))
                .criterion(hasItem(Items.DIAMOND),conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter,new Identifier(getRecipeName(FsItems.FURNACE_UPGRADE_DIAMOND_TEMPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FsItems.FURNACE_UPGRADE_EMERALD_TEMPLATE,2)
                .pattern("OOO").pattern("OXO").pattern("OOO")
                .input('O', Items.EMERALD).input('X',FsItems.FURNACE_UPGRADE_EMERALD_TEMPLATE)
                .criterion(hasItem(FsItems.FURNACE_UPGRADE_EMERALD_TEMPLATE),conditionsFromItem(FsItems.FURNACE_UPGRADE_EMERALD_TEMPLATE))
                .criterion(hasItem(Items.EMERALD),conditionsFromItem(Items.EMERALD))
                .offerTo(exporter,new Identifier(getRecipeName(FsItems.FURNACE_UPGRADE_EMERALD_TEMPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FsItems.FURNACE_UPGRADE_NETHERITE_TEMPLATE,2)
                .pattern("OOO").pattern("OXO").pattern("OOO")
                .input('O', Items.NETHERITE_INGOT).input('X',FsItems.FURNACE_UPGRADE_NETHERITE_TEMPLATE)
                .criterion(hasItem(FsItems.FURNACE_UPGRADE_NETHERITE_TEMPLATE),conditionsFromItem(FsItems.FURNACE_UPGRADE_NETHERITE_TEMPLATE))
                .criterion(hasItem(Items.NETHERITE_INGOT),conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter,new Identifier(getRecipeName(FsItems.FURNACE_UPGRADE_NETHERITE_TEMPLATE)));

        /* ----------------------------- TEMPLATES -------------------- */

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,FsItems.FURNACE_UPGRADE_TEMPLATE,1)
                .pattern("OYO").pattern("YXY").pattern("OYO")
                .input('O',Items.COPPER_INGOT).input('Y',Items.LEATHER).input('X',Items.PAPER)
                .criterion(hasItem(Items.COPPER_INGOT),conditionsFromItem(Items.COPPER_INGOT))
                .criterion(hasItem(Items.LEATHER),conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.PAPER),conditionsFromItem(Items.PAPER))
                .offerTo(exporter,new Identifier(getRecipeName(FsItems.FURNACE_UPGRADE_TEMPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FsItems.FURNACE_UPGRADE_COPPER_TEMPLATE,1)
                .pattern("OOO").pattern("OXO").pattern("OOO")
                .input('O', Items.COPPER_INGOT).input('X',FsItems.FURNACE_UPGRADE_TEMPLATE)
                .criterion(hasItem(FsItems.FURNACE_UPGRADE_TEMPLATE),conditionsFromItem(FsItems.FURNACE_UPGRADE_TEMPLATE))
                .criterion(hasItem(Items.COPPER_INGOT),conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter,new Identifier(getRecipeName(FsItems.FURNACE_UPGRADE_COPPER_TEMPLATE)+"_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FsItems.FURNACE_UPGRADE_IRON_TEMPLATE,1)
                .pattern("OOO").pattern("OXO").pattern("OOO")
                .input('O', Items.IRON_INGOT).input('X',FsItems.FURNACE_UPGRADE_TEMPLATE)
                .criterion(hasItem(FsItems.FURNACE_UPGRADE_TEMPLATE),conditionsFromItem(FsItems.FURNACE_UPGRADE_TEMPLATE))
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter,new Identifier(getRecipeName(FsItems.FURNACE_UPGRADE_IRON_TEMPLATE)+"_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FsItems.FURNACE_UPGRADE_GOLD_TEMPLATE,1)
                .pattern("OOO").pattern("OXO").pattern("OOO")
                .input('O', Items.GOLD_INGOT).input('X',FsItems.FURNACE_UPGRADE_TEMPLATE)
                .criterion(hasItem(FsItems.FURNACE_UPGRADE_TEMPLATE),conditionsFromItem(FsItems.FURNACE_UPGRADE_TEMPLATE))
                .criterion(hasItem(Items.GOLD_INGOT),conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter,new Identifier(getRecipeName(FsItems.FURNACE_UPGRADE_GOLD_TEMPLATE)+"_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FsItems.FURNACE_UPGRADE_DIAMOND_TEMPLATE,1)
                .pattern("OOO").pattern("OXO").pattern("OOO")
                .input('O', Items.DIAMOND).input('X',FsItems.FURNACE_UPGRADE_TEMPLATE)
                .criterion(hasItem(FsItems.FURNACE_UPGRADE_TEMPLATE),conditionsFromItem(FsItems.FURNACE_UPGRADE_TEMPLATE))
                .criterion(hasItem(Items.DIAMOND),conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter,new Identifier(getRecipeName(FsItems.FURNACE_UPGRADE_DIAMOND_TEMPLATE)+"_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FsItems.FURNACE_UPGRADE_EMERALD_TEMPLATE,1)
                .pattern("OOO").pattern("OXO").pattern("OOO")
                .input('O', Items.EMERALD).input('X',FsItems.FURNACE_UPGRADE_TEMPLATE)
                .criterion(hasItem(FsItems.FURNACE_UPGRADE_TEMPLATE),conditionsFromItem(FsItems.FURNACE_UPGRADE_TEMPLATE))
                .criterion(hasItem(Items.EMERALD),conditionsFromItem(Items.EMERALD))
                .offerTo(exporter,new Identifier(getRecipeName(FsItems.FURNACE_UPGRADE_EMERALD_TEMPLATE)+"_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, FsItems.FURNACE_UPGRADE_NETHERITE_TEMPLATE,1)
                .pattern("OOO").pattern("OXO").pattern("OOO")
                .input('O', Items.NETHERITE_INGOT).input('X',FsItems.FURNACE_UPGRADE_TEMPLATE)
                .criterion(hasItem(FsItems.FURNACE_UPGRADE_TEMPLATE),conditionsFromItem(FsItems.FURNACE_UPGRADE_TEMPLATE))
                .criterion(hasItem(Items.NETHERITE_INGOT),conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter,new Identifier(getRecipeName(FsItems.FURNACE_UPGRADE_NETHERITE_TEMPLATE)+"_2"));

    }
}
