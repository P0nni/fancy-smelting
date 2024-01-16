package poney.fs.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import poney.fs.FancySmelting;
import poney.fs.block.entity.FurnaceEntity;

public class FsBlockEntities {

    public static BlockEntityType<FurnaceEntity> FURNACE_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(FancySmelting.ID, "furnace_block_entity"),
            FabricBlockEntityTypeBuilder.create(FurnaceEntity::new,FsBlocks.FURNACE_BLOCK
                    ).build());


    public static void Initialize(){
        FancySmelting.LOGGER.info(FancySmelting.ID, "Initialized Block Entities!!");
    }
}
