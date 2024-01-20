package poney.fs.block.entity.client.renderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import poney.fs.block.entity.client.model.FurnaceCopperBlockModel;
import poney.fs.block.entity.client.model.FurnaceDiamondBlockModel;


public class FurnaceCopperBlockRenderer extends AbstractFurnaceFsBlockRenderer {
    public FurnaceCopperBlockRenderer(BlockEntityRendererFactory.Context ctx) {
        super(new FurnaceCopperBlockModel());
    }
}
