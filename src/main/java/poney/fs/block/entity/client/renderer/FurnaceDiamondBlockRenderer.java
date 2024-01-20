package poney.fs.block.entity.client.renderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import poney.fs.block.entity.client.model.FurnaceDiamondBlockModel;


public class FurnaceDiamondBlockRenderer extends AbstractFurnaceFsBlockRenderer {
    public FurnaceDiamondBlockRenderer(BlockEntityRendererFactory.Context ctx) {
        super(new FurnaceDiamondBlockModel());
    }
}
