package poney.fs.block.entity.client.renderer.layer;

import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.util.Identifier;
import poney.fs.FancySmelting;
import poney.fs.block.entity.AbstractFurnaceFsEntity;
import poney.fs.block.entity.client.model.AbstractFurnaceFsBlockModel;
import poney.fs.util.enums.FurnaceFuel;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.texture.AnimatableTexture;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class FurnaceLitRenderLayer extends GeoRenderLayer<AbstractFurnaceFsEntity> {

    public FurnaceLitRenderLayer(GeoRenderer<AbstractFurnaceFsEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    protected Identifier getTextureResource(AbstractFurnaceFsEntity animatable) {
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

    protected RenderLayer getRenderType(AbstractFurnaceFsEntity animatable) {
        return RenderLayer.getEntityTranslucentEmissive(getTextureResource(animatable));
    }

    @Override
    public void render(MatrixStack poseStack, AbstractFurnaceFsEntity animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderLayer layer = getRenderType(animatable);
        getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, layer,
                bufferSource.getBuffer(layer), partialTick, 15728640, OverlayTexture.DEFAULT_UV,
                1, 1, 1, 1);
    }
}
