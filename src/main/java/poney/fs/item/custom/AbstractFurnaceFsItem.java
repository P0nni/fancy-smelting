package poney.fs.item.custom;

import net.minecraft.block.Block;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.item.BlockItem;
import poney.fs.item.entity.client.FurnaceBlockItemModel;
import poney.fs.util.enums.FurnaceLevel;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractFurnaceFsItem extends BlockItem implements GeoItem {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public AbstractFurnaceFsItem(Block block, Settings settings) {
        super(block, settings);
    }

    public FurnaceLevel getFurnaceLevel(){
        return FurnaceLevel.STONE;
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private GeoItemRenderer<AbstractFurnaceFsItem> renderer = null;
            @Override
            public BuiltinModelItemRenderer getCustomRenderer() {
                if(this.renderer == null)
                    this.renderer = new GeoItemRenderer<>(new FurnaceBlockItemModel(getFurnaceLevel()));

                return this.renderer;
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return this.renderProvider;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }
}
