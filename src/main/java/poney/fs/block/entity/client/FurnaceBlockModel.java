package poney.fs.block.entity.client;

import net.minecraft.client.item.ClampedModelPredicateProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import poney.fs.FancySmelting;
import poney.fs.block.custom.FurnaceBlock;
import poney.fs.block.custom.FurnaceFuel;
import poney.fs.block.entity.FurnaceEntity;
import software.bernie.geckolib.model.GeoModel;

public class FurnaceBlockModel extends GeoModel<FurnaceEntity> implements ClampedModelPredicateProvider {
    @Override
    public Identifier getModelResource(FurnaceEntity animatable) {
        return new Identifier(FancySmelting.ID,"geo/block/furnace_fs.geo.json");
    }

    @Override
    public Identifier getTextureResource(FurnaceEntity animatable) {
        if(FurnaceBlock.isLit(animatable.getCachedState())) {
            switch (FurnaceBlock.getCurrentFuel(animatable.getCachedState())) {
                case LAVA -> {
                    return new Identifier(FancySmelting.ID, "textures/block/furnace_fs_lava_on.png");
                }
                case MAGMA -> {
                    return new Identifier(FancySmelting.ID, "textures/block/furnace_fs_magma_on.png");
                }
            }
        }else{
            switch (FurnaceBlock.getCurrentFuel(animatable.getCachedState())) {
                case LAVA -> {
                    return new Identifier(FancySmelting.ID, "textures/block/furnace_fs_lava.png");
                }
                case MAGMA -> {
                    return new Identifier(FancySmelting.ID, "textures/block/furnace_fs_magma.png");
                }
            }
        }
        return new Identifier(FancySmelting.ID, "textures/block/furnace_fs.png");
    }

    @Override
    public Identifier getAnimationResource(FurnaceEntity animatable) {
        return new Identifier(FancySmelting.ID,"animations/block/furnace_fs.json");
    }

    @Override
    public float unclampedCall(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity entity, int seed) {
        return 0;
    }

    @Override
    public RenderLayer getRenderType(FurnaceEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
