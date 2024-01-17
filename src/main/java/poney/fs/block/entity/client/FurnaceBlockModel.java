package poney.fs.block.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import poney.fs.FancySmelting;
import poney.fs.block.custom.FurnaceBlock;
import poney.fs.block.custom.FurnaceFuel;
import poney.fs.block.entity.FurnaceEntity;
import software.bernie.geckolib.model.GeoModel;

public class FurnaceBlockModel extends GeoModel<FurnaceEntity> {
    @Override
    public Identifier getModelResource(FurnaceEntity animatable) {
        return new Identifier(FancySmelting.ID,"geo/block/furnace_fs.geo.json");
    }

    @Override
    public Identifier getTextureResource(FurnaceEntity animatable) {
        boolean lit = FurnaceBlock.isLit(animatable.getCachedState());
        FurnaceFuel fuel = FurnaceBlock.getCurrentFuel(animatable.getCachedState());
        return getTexture(lit,fuel);
    }

    @Override
    public Identifier getAnimationResource(FurnaceEntity animatable) {
        return null;
    }

    private Identifier getTexture(boolean lit,FurnaceFuel fuel){
        String id = FancySmelting.ID;
        switch (fuel) {
            case LAVA -> {
                return new Identifier(id, lit ? "textures/block/furnace_fs_lava_on.png" : "textures/block/furnace_fs_lava.png" );
            }
            case MAGMA -> {
                return new Identifier(id, lit ? "textures/block/furnace_fs_magma_on.png" : "textures/block/furnace_fs_magma.png");
            }
            default -> {
                return  new Identifier(id,"textures/block/furnace_fs.png");
            }
        }
    }

}
