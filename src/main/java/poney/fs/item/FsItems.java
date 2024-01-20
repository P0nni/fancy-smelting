package poney.fs.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import poney.fs.FancySmelting;
import poney.fs.block.FsBlocks;
import poney.fs.item.custom.*;

public class FsItems {

    public static final BlockItem FURNACE_STONE_ITEM = registerItem("furnace_stone_item", ItemGroups.FUNCTIONAL, new FurnaceStoneItem(FsBlocks.FURNACE_STONE_BLOCK,new FabricItemSettings()));
    public static final BlockItem FURNACE_DIAMOND_ITEM = registerItem("furnace_diamond_item", ItemGroups.FUNCTIONAL, new FurnaceDiamondItem(FsBlocks.FURNACE_DIAMOND_BLOCK,new FabricItemSettings()));
    public static final BlockItem FURNACE_COPPER_ITEM = registerItem("furnace_copper_item", ItemGroups.FUNCTIONAL, new FurnaceCopperItem(FsBlocks.FURNACE_COPPER_BLOCK,new FabricItemSettings()));
    public static final BlockItem FURNACE_IRON_ITEM = registerItem("furnace_iron_item", ItemGroups.FUNCTIONAL, new FurnaceIronItem(FsBlocks.FURNACE_IRON_BLOCK,new FabricItemSettings()));
    public static final BlockItem FURNACE_GOLD_ITEM = registerItem("furnace_gold_item", ItemGroups.FUNCTIONAL, new FurnaceGoldItem(FsBlocks.FURNACE_GOLD_BLOCK,new FabricItemSettings()));
    public static final BlockItem FURNACE_EMERALD_ITEM = registerItem("furnace_emerald_item", ItemGroups.FUNCTIONAL, new FurnaceEmeraldItem(FsBlocks.FURNACE_EMERALD_BLOCK,new FabricItemSettings()));
    public static final BlockItem FURNACE_NETHERITE_ITEM = registerItem("furnace_netherite_item", ItemGroups.FUNCTIONAL, new FurnaceNetheriteItem(FsBlocks.FURNACE_NETHERITE_BLOCK,new FabricItemSettings()));

    private static <I extends Item> I registerItem(String name, RegistryKey<ItemGroup> group, I item) {
        Identifier itemId = new Identifier(FancySmelting.ID, name);

        // Registra o item no registro de itens
        I registeredItem = Registry.register(Registries.ITEM, itemId, item);

        // Adiciona o item ao grupo especificado
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(registeredItem));

        return registeredItem;
    }

    public static void Initialize(){
        FancySmelting.LOGGER.info(FancySmelting.ID, "Initialized Items!!");
    }

}
