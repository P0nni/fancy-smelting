package poney.fs.block.entity.client.renderer;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import poney.fs.block.entity.client.model.FurnaceFsBlockModel;

public class FurnaceStoneBlockRenderer extends AbstractFurnaceFsBlockRenderer {
    public FurnaceStoneBlockRenderer(BlockEntityRendererFactory.Context ctx) {
        super(new FurnaceFsBlockModel());
    }
}
