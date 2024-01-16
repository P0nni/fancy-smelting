package poney.fs.block.entity.client;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import poney.fs.block.entity.FurnaceEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;


public class FurnaceBlockRenderer extends GeoBlockRenderer<FurnaceEntity> {
    public FurnaceBlockRenderer(BlockEntityRendererFactory.Context ctx) {
        super(new FurnaceBlockModel());
    }

    @Override
    public void renderFinal(MatrixStack poseStack, FurnaceEntity animatable, BakedGeoModel model, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = animatable.getRenderStack();
        poseStack.push();
        poseStack.translate(0.5f, 0.75f, 0.5f);
        poseStack.scale(0.35f, 0.35f, 0.35f);
        poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(270));

        itemRenderer.renderItem(stack, ModelTransformationMode.GUI, getLightLevel(animatable.getWorld(),
                animatable.getPos()), OverlayTexture.DEFAULT_UV, poseStack, bufferSource, animatable.getWorld(), 1);
        poseStack.pop();

        super.renderFinal(poseStack, animatable, model, bufferSource, buffer, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
