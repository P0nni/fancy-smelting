package poney.fs.item.entity.client;

import net.minecraft.util.Identifier;
import poney.fs.FancySmelting;
import poney.fs.item.custom.AbstractFurnaceFsItem;
import poney.fs.util.enums.FurnaceLevel;
import software.bernie.geckolib.model.GeoModel;

public class FurnaceBlockItemModel extends GeoModel<AbstractFurnaceFsItem> {
    private FurnaceLevel level;
    public FurnaceBlockItemModel(FurnaceLevel furnaceLevel) {
        level = furnaceLevel;
    }

    @Override
    public Identifier getModelResource(AbstractFurnaceFsItem animatable) {
        return new Identifier(FancySmelting.ID,"geo/block/furnace_fs.geo.json");
    }

    @Override
    public Identifier getTextureResource(AbstractFurnaceFsItem animatable) {
        return  new Identifier(FancySmelting.ID,"textures/block/furnace_"+level.asString()+"_item.png");
    }

    @Override
    public Identifier getAnimationResource(AbstractFurnaceFsItem animatable) {
        return null;
    }
}
