package poney.fs.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import poney.fs.FancySmelting;
import poney.fs.block.FsBlocks;
import poney.fs.item.custom.*;

public class FsItems {

    public static final BlockItem FURNACE_STONE_ITEM = registerItem("furnace_stone_item", new FurnaceStoneItem(FsBlocks.FURNACE_STONE_BLOCK,new FabricItemSettings()));
    public static final BlockItem FURNACE_DIAMOND_ITEM = registerItem("furnace_diamond_item", new FurnaceDiamondItem(FsBlocks.FURNACE_DIAMOND_BLOCK,new FabricItemSettings()));
    public static final BlockItem FURNACE_COPPER_ITEM = registerItem("furnace_copper_item", new FurnaceCopperItem(FsBlocks.FURNACE_COPPER_BLOCK,new FabricItemSettings()));
    public static final BlockItem FURNACE_IRON_ITEM = registerItem("furnace_iron_item", new FurnaceIronItem(FsBlocks.FURNACE_IRON_BLOCK,new FabricItemSettings()));
    public static final BlockItem FURNACE_GOLD_ITEM = registerItem("furnace_gold_item", new FurnaceGoldItem(FsBlocks.FURNACE_GOLD_BLOCK,new FabricItemSettings()));
    public static final BlockItem FURNACE_EMERALD_ITEM = registerItem("furnace_emerald_item", new FurnaceEmeraldItem(FsBlocks.FURNACE_EMERALD_BLOCK,new FabricItemSettings()));
    public static final BlockItem FURNACE_NETHERITE_ITEM = registerItem("furnace_netherite_item", new FurnaceNetheriteItem(FsBlocks.FURNACE_NETHERITE_BLOCK,new FabricItemSettings()));

    public static final Item FURNACE_UPGRADE_COPPER_TEMPLATE = registerItem("furnace_upgrade_copper_template", new Item(new FabricItemSettings()));
    public static final Item FURNACE_UPGRADE_IRON_TEMPLATE = registerItem("furnace_upgrade_iron_template", new Item(new FabricItemSettings()));
    public static final Item FURNACE_UPGRADE_GOLD_TEMPLATE = registerItem("furnace_upgrade_gold_template", new Item(new FabricItemSettings()));
    public static final Item FURNACE_UPGRADE_DIAMOND_TEMPLATE = registerItem("furnace_upgrade_diamond_template", new Item(new FabricItemSettings()));
    public static final Item FURNACE_UPGRADE_EMERALD_TEMPLATE = registerItem("furnace_upgrade_emerald_template", new Item(new FabricItemSettings()));
    public static final Item FURNACE_UPGRADE_NETHERITE_TEMPLATE = registerItem("furnace_upgrade_netherite_template", new Item(new FabricItemSettings()));
    public static final Item FURNACE_UPGRADE_TEMPLATE = registerItem("furnace_upgrade_template", new Item(new FabricItemSettings()));

    private static <I extends Item> I registerItem(String name, I item) {
        Identifier itemId = new Identifier(FancySmelting.ID, name);

        // Registra o item no registro de itens
        I registeredItem = Registry.register(Registries.ITEM, itemId, item);

        return registeredItem;
    }

    public static final ItemGroup FS_GROUP = Registry.register(Registries.ITEM_GROUP,new Identifier(FancySmelting.ID,"fancy_smelting"), FabricItemGroup.builder()
            .displayName(Text.translatable("itemgroup.fs"))
            .icon(() -> new ItemStack(FsItems.FURNACE_STONE_ITEM)).entries(((displayContext, entries) -> {
                entries.add(FURNACE_STONE_ITEM);
                entries.add(FURNACE_COPPER_ITEM);
                entries.add(FURNACE_IRON_ITEM);
                entries.add(FURNACE_GOLD_ITEM);
                entries.add(FURNACE_DIAMOND_ITEM);
                entries.add(FURNACE_EMERALD_ITEM);
                entries.add(FURNACE_NETHERITE_ITEM);
                entries.add(FURNACE_UPGRADE_TEMPLATE);
                entries.add(FURNACE_UPGRADE_COPPER_TEMPLATE);
                entries.add(FURNACE_UPGRADE_IRON_TEMPLATE);
                entries.add(FURNACE_UPGRADE_GOLD_TEMPLATE);
                entries.add(FURNACE_UPGRADE_DIAMOND_TEMPLATE);
                entries.add(FURNACE_UPGRADE_EMERALD_TEMPLATE);
                entries.add(FURNACE_UPGRADE_NETHERITE_TEMPLATE);
            })).build());

    public static void Initialize(){
        FancySmelting.LOGGER.info(FancySmelting.ID, "Initialized Items!!");
    }

}
