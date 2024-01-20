package poney.fs;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import poney.fs.block.FsBlockEntities;
import poney.fs.block.entity.client.renderer.FurnaceCopperBlockRenderer;
import poney.fs.block.entity.client.renderer.FurnaceDiamondBlockRenderer;
import poney.fs.block.entity.client.renderer.FurnaceStoneBlockRenderer;
import poney.fs.gui.FsScreens;
import poney.fs.gui.FurnaceScreen;

public class FancySmeltingClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		FancySmelting.LOGGER.info(FancySmelting.ID,"Initialized MOD Client!!");

		BlockEntityRendererFactories.register(FsBlockEntities.FURNACE_STONE_BLOCK_ENTITY, FurnaceStoneBlockRenderer::new);
		BlockEntityRendererFactories.register(FsBlockEntities.FURNACE_DIAMOND_BLOCK_ENTITY, FurnaceDiamondBlockRenderer::new);
		BlockEntityRendererFactories.register(FsBlockEntities.FURNACE_COPPER_BLOCK_ENTITY, FurnaceCopperBlockRenderer::new);

		HandledScreens.register(FsScreens.FURNACE_SCREEN_HANDLER, FurnaceScreen::new);

	}
}