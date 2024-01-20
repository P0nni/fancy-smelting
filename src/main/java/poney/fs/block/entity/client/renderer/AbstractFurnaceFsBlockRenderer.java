package poney.fs.block.entity.client.renderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;
import poney.fs.block.custom.AbstractFurnaceFsBlock;
import poney.fs.block.entity.AbstractFurnaceFsEntity;
import poney.fs.block.entity.client.model.AbstractFurnaceFsBlockModel;
import poney.fs.block.entity.client.renderer.layer.FurnaceLitRenderLayer;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;


public abstract class AbstractFurnaceFsBlockRenderer extends GeoBlockRenderer<AbstractFurnaceFsEntity> {

    public AbstractFurnaceFsBlockRenderer(AbstractFurnaceFsBlockModel furnaceModel) {
        super(furnaceModel);
        addRenderLayer(new FurnaceLitRenderLayer(this));
    }

    @Override
    public void renderFinal(MatrixStack poseStack, AbstractFurnaceFsEntity animatable, BakedGeoModel model, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        Item item = animatable.getRenderStack().getItem();
        if (item != Items.AIR) {
            this.renderResultItem(AbstractFurnaceFsBlock.getDirection(this.animatable.getCachedState()),
                    new ItemStack(item),
                    animatable.ticks + partialTick,
                    animatable.getWorld(),
                    poseStack,
                    bufferSource,
                    packedLight,
                    packedOverlay);
        }

        super.renderFinal(poseStack, animatable, model, bufferSource, buffer, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void renderResultItem(Direction blockDirection, ItemStack stack, float time, World world, MatrixStack poseStack, VertexConsumerProvider bufferIn, int combinedLightIn, int combinedOverlayIn) {

        poseStack.push();
        poseTranslate(poseStack,blockDirection,0.5f,1.02f,-0.15f);

        BakedModel model = MinecraftClient.getInstance().getItemRenderer().getModel(stack, world,null, 0);
        float hoverOffset = MathHelper.sin(time / 10.0F) * 0.04F + 0.1F;
        float modelYScale = model.getTransformation().ground.scale.y();
        poseStack.translate(0.0, hoverOffset + 0.25F * modelYScale, 0.0);
        poseStack.multiply(RotationAxis.POSITIVE_Y.rotation(time / 20.0F));
        poseStack.scale(0.75F, 0.75F, 0.75F);

        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.GROUND, false, poseStack, bufferIn, combinedLightIn, combinedOverlayIn, model);
        poseStack.pop();
    }

    private void poseTranslate(MatrixStack poseStack,Direction direction, float offsetX,float offsetY, float offsetZ){
        switch (direction) {
            case NORTH:
                poseStack.translate(offsetX, offsetY, -offsetZ);
                poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
                break;
            case SOUTH:
                poseStack.translate(offsetX, offsetY, offsetZ + 1);
                break;
            case WEST:
                poseStack.translate(-offsetZ, offsetY, offsetX);
                poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));
                break;
            case EAST:
                poseStack.translate(offsetZ + 1, offsetY, offsetX);
                poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
                break;
        }
    }

}
