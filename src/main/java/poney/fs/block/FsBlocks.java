package poney.fs.block;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import poney.fs.FancySmelting;
import poney.fs.block.custom.*;

public class FsBlocks {

    public static final Block FURNACE_STONE_BLOCK = registerBlock("furnace_stone",new FurnaceStoneBlock(AbstractFurnaceFsBlock.createFurnaceSettings()));
    public static final Block FURNACE_DIAMOND_BLOCK = registerBlock("furnace_diamond",new FurnaceDiamondBlock(AbstractFurnaceFsBlock.createFurnaceSettings()));
    public static final Block FURNACE_COPPER_BLOCK = registerBlock("furnace_copper",new FurnaceCopperBlock(AbstractFurnaceFsBlock.createFurnaceSettings()));
    public static final Block FURNACE_IRON_BLOCK = registerBlock("furnace_iron",new FurnaceIronBlock(AbstractFurnaceFsBlock.createFurnaceSettings()));
    public static final Block FURNACE_GOLD_BLOCK = registerBlock("furnace_gold",new FurnaceGoldBlock(AbstractFurnaceFsBlock.createFurnaceSettings()));
    public static final Block FURNACE_EMERALD_BLOCK = registerBlock("furnace_emerald",new FurnaceEmeraldBlock(AbstractFurnaceFsBlock.createFurnaceSettings()));
    public static final Block FURNACE_NETHERITE_BLOCK = registerBlock("furnace_netherite",new FurnaceNetheriteBlock(AbstractFurnaceFsBlock.createFurnaceSettings()));

    private static Block registerBlock(String name, Block block){
        return Registry.register(Registries.BLOCK, new Identifier(FancySmelting.ID,name),block);
    }

    public static void Initialize(){
        FancySmelting.LOGGER.info(FancySmelting.ID, "Initialized Blocks!!");
    }

}
