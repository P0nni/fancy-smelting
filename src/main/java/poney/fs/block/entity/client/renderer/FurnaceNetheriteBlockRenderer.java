package poney.fs.block.entity.client.renderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import poney.fs.block.entity.client.model.FurnaceFsBlockModel;


public class FurnaceNetheriteBlockRenderer extends AbstractFurnaceFsBlockRenderer {
    public FurnaceNetheriteBlockRenderer(BlockEntityRendererFactory.Context ctx) {
        super(new FurnaceFsBlockModel());
    }
}
