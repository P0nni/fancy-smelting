package poney.fs.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import poney.fs.FancySmelting;
import poney.fs.block.entity.*;

public class FsBlockEntities {

    public static BlockEntityType<FurnaceStoneEntity> FURNACE_STONE_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(FancySmelting.ID, "furnace_stone_entity"),
            FabricBlockEntityTypeBuilder.create(FurnaceStoneEntity::new,FsBlocks.FURNACE_STONE_BLOCK
                    ).build());

    public static BlockEntityType<FurnaceCopperEntity> FURNACE_COPPER_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(FancySmelting.ID, "furnace_copper_entity"),
            FabricBlockEntityTypeBuilder.create(FurnaceCopperEntity::new,FsBlocks.FURNACE_COPPER_BLOCK
            ).build());

    public static BlockEntityType<FurnaceIronEntity> FURNACE_IRON_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(FancySmelting.ID, "furnace_iron_entity"),
            FabricBlockEntityTypeBuilder.create(FurnaceIronEntity::new,FsBlocks.FURNACE_IRON_BLOCK
            ).build());

    public static BlockEntityType<FurnaceGoldEntity> FURNACE_GOLD_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(FancySmelting.ID, "furnace_gold_entity"),
            FabricBlockEntityTypeBuilder.create(FurnaceGoldEntity::new,FsBlocks.FURNACE_GOLD_BLOCK
            ).build());

    public static BlockEntityType<FurnaceDiamondEntity> FURNACE_DIAMOND_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(FancySmelting.ID, "furnace_diamond_entity"),
            FabricBlockEntityTypeBuilder.create(FurnaceDiamondEntity::new,FsBlocks.FURNACE_DIAMOND_BLOCK
            ).build());

    public static BlockEntityType<FurnaceEmeraldEntity> FURNACE_EMERALD_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(FancySmelting.ID, "furnace_emerald_entity"),
            FabricBlockEntityTypeBuilder.create(FurnaceEmeraldEntity::new,FsBlocks.FURNACE_EMERALD_BLOCK
            ).build());

    public static BlockEntityType<FurnaceNetheriteEntity> FURNACE_NETHERITE_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(FancySmelting.ID, "furnace_netherite_entity"),
            FabricBlockEntityTypeBuilder.create(FurnaceNetheriteEntity::new,FsBlocks.FURNACE_NETHERITE_BLOCK
            ).build());

    public static void Initialize(){
        FancySmelting.LOGGER.info(FancySmelting.ID, "Initialized Block Entities!!");
    }
}
