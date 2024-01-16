package poney.fs.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import poney.fs.FancySmelting;
import poney.fs.block.custom.FurnaceBlock;

public class FsBlocks {

    public static final Block FURNACE_BLOCK = registerBlock("furnace_fs",new FurnaceBlock(FurnaceBlock.createFurnaceSettings()));

    private static Block registerBlock(String name, Block block){
        return Registry.register(Registries.BLOCK, new Identifier(FancySmelting.ID,name),block);
    }

    public static void Initialize(){
        FancySmelting.LOGGER.info(FancySmelting.ID, "Initialized Blocks!!");
    }

}
