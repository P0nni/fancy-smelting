package poney.fs.block.entity.client;

import net.minecraft.util.Identifier;
import poney.fs.FancySmelting;
import poney.fs.block.custom.FurnaceItem;
import software.bernie.geckolib.model.GeoModel;

public class FurnaceBlockItemModel extends GeoModel<FurnaceItem> {
    @Override
    public Identifier getModelResource(FurnaceItem animatable) {
        return new Identifier(FancySmelting.ID,"geo/block/furnace_fs.geo.json");
    }

    @Override
    public Identifier getTextureResource(FurnaceItem animatable) {
        return  new Identifier(FancySmelting.ID,"textures/block/furnace_fs_block_item.png");
    }

    @Override
    public Identifier getAnimationResource(FurnaceItem animatable) {
        return null;
    }
}
