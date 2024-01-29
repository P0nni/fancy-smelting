package poney.fs.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;
import poney.fs.item.FsItems;

import java.util.Optional;

public class FsModelProvider extends FabricModelProvider {

    public FsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(FsItems.FURNACE_UPGRADE_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(FsItems.FURNACE_UPGRADE_COPPER_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(FsItems.FURNACE_UPGRADE_IRON_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(FsItems.FURNACE_UPGRADE_GOLD_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(FsItems.FURNACE_UPGRADE_DIAMOND_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(FsItems.FURNACE_UPGRADE_EMERALD_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(FsItems.FURNACE_UPGRADE_NETHERITE_TEMPLATE, Models.GENERATED);

    }
}
