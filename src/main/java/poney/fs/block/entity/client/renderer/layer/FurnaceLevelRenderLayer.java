package poney.fs.block.entity.client.renderer.layer;

import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import poney.fs.FancySmelting;
import poney.fs.block.entity.AbstractFurnaceFsEntity;
import poney.fs.util.enums.FurnaceFuel;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class FurnaceLevelRenderLayer extends GeoRenderLayer<AbstractFurnaceFsEntity> {

    public FurnaceLevelRenderLayer(GeoRenderer<AbstractFurnaceFsEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    protected Identifier getTextureResource(AbstractFurnaceFsEntity animatable) {
            return  new Identifier(FancySmelting.ID, "textures/block/furnace_" + animatable.getFurnaceLevel().asString() + ".png");
    }

    protected RenderLayer getRenderType(AbstractFurnaceFsEntity animatable) {
        return RenderLayer.getEntityCutoutNoCull(getTextureResource(animatable));
    }

    @Override
    public void render(MatrixStack poseStack, AbstractFurnaceFsEntity animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderLayer layer = getRenderType(animatable);
        getRenderer().reRender(bakedModel,poseStack,bufferSource,animatable,layer,bufferSource.getBuffer(layer),partialTick,packedLight,packedOverlay,1,1,1,1);
    }
}
