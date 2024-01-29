package poney.fs;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import poney.fs.block.FsBlockEntities;
import poney.fs.block.entity.FurnaceIronEntity;
import poney.fs.block.entity.client.renderer.*;
import poney.fs.gui.FsScreens;
import poney.fs.gui.FurnaceScreen;

public class FancySmeltingClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		FancySmelting.LOGGER.info(FancySmelting.ID,"Initialized MOD Client!!");

		BlockEntityRendererFactories.register(FsBlockEntities.FURNACE_STONE_BLOCK_ENTITY, FurnaceStoneBlockRenderer::new);
		BlockEntityRendererFactories.register(FsBlockEntities.FURNACE_DIAMOND_BLOCK_ENTITY, FurnaceDiamondBlockRenderer::new);
		BlockEntityRendererFactories.register(FsBlockEntities.FURNACE_COPPER_BLOCK_ENTITY, FurnaceCopperBlockRenderer::new);
		BlockEntityRendererFactories.register(FsBlockEntities.FURNACE_IRON_BLOCK_ENTITY, FurnaceIronBlockRenderer::new);
		BlockEntityRendererFactories.register(FsBlockEntities.FURNACE_GOLD_BLOCK_ENTITY, FurnaceGoldBlockRenderer::new);
		BlockEntityRendererFactories.register(FsBlockEntities.FURNACE_EMERALD_BLOCK_ENTITY, FurnaceEmeraldBlockRenderer::new);
		BlockEntityRendererFactories.register(FsBlockEntities.FURNACE_NETHERITE_BLOCK_ENTITY, FurnaceNetheriteBlockRenderer::new);

		HandledScreens.register(FsScreens.FURNACE_SCREEN_HANDLER, FurnaceScreen::new);

	}
}