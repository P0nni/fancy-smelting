package poney.fs.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import poney.fs.FancySmelting;
import poney.fs.block.custom.FurnaceItem;

public class FsItems {

    public static final BlockItem FURNACE_ITEM = registerItem("furnace_fs_item", ItemGroups.FUNCTIONAL, new FurnaceItem(FsBlocks.FURNACE_BLOCK,new FabricItemSettings()));

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
