package poney.fs.block.entity.client.model;

import net.minecraft.util.Identifier;
import poney.fs.FancySmelting;
import poney.fs.block.custom.AbstractFurnaceFsBlock;
import poney.fs.block.entity.AbstractFurnaceFsEntity;
import poney.fs.util.enums.FurnaceFuel;
import poney.fs.util.enums.FurnaceLevel;
import software.bernie.geckolib.model.GeoModel;

public abstract class AbstractFurnaceFsBlockModel extends GeoModel<AbstractFurnaceFsEntity> {
    @Override
    public Identifier getModelResource(AbstractFurnaceFsEntity animatable) {
        return new Identifier(FancySmelting.ID,"geo/block/furnace_fs.geo.json");
    }

    @Override
    public Identifier getTextureResource(AbstractFurnaceFsEntity animatable) {
        return new Identifier(FancySmelting.ID, "textures/block/furnace_" + getLevel().asString() + ".png");
    }

    @Override
    public Identifier getAnimationResource(AbstractFurnaceFsEntity animatable) {
        return null;
    }

    public FurnaceLevel getLevel(){
        return FurnaceLevel.STONE;
    }

}
