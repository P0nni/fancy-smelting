package poney.fs.block.entity.client.model;

import net.minecraft.util.Identifier;
import poney.fs.FancySmelting;
import poney.fs.block.entity.AbstractFurnaceFsEntity;
import poney.fs.util.enums.FurnaceFuel;
import software.bernie.geckolib.model.GeoModel;

public class FurnaceFsBlockModel extends GeoModel<AbstractFurnaceFsEntity> {
    @Override
    public Identifier getModelResource(AbstractFurnaceFsEntity animatable) {
        return new Identifier(FancySmelting.ID,"geo/block/furnace_fs.geo.json");
    }

    @Override
    public Identifier getTextureResource(AbstractFurnaceFsEntity animatable) {
        boolean lit = animatable.isLit();
        FurnaceFuel fuel = animatable.getCurrentFuel();

        return getIdentifier(lit,fuel);
    }

    private Identifier getIdentifier(boolean lit, FurnaceFuel fuel){
        String id = FancySmelting.ID;

        StringBuilder builder = new StringBuilder();
        builder.append("textures/block/furnace_fuel").append("_").append(fuel.asString());;
        if(fuel != FurnaceFuel.EMPTY){
            if(lit) builder.append("_").append("on");
        }
        builder.append(".png");

        return  new Identifier(id,builder.toString());
    }

    @Override
    public Identifier getAnimationResource(AbstractFurnaceFsEntity animatable) {
        return null;
    }
}
