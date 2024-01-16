package poney.fs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import poney.fs.block.FsBlockEntities;
import poney.fs.block.entity.client.FurnaceBlockRenderer;
import poney.fs.gui.FsScreens;
import poney.fs.gui.FurnaceScreen;
import poney.fs.gui.FurnaceScreenHandler;

public class FancySmeltingClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		FancySmelting.LOGGER.info(FancySmelting.ID,"Initialized MOD Client!!");

		BlockEntityRendererFactories.register(FsBlockEntities.FURNACE_BLOCK_ENTITY, FurnaceBlockRenderer::new);
		HandledScreens.register(FsScreens.FURNACE_SCREEN_HANDLER, FurnaceScreen::new);

	}
}