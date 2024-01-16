package poney.fs;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poney.fs.block.FsBlocks;
import poney.fs.block.FsBlockEntities;
import poney.fs.block.FsItems;
import poney.fs.gui.FsScreens;
import poney.fs.gui.FurnaceScreenHandler;

public class FancySmelting implements ModInitializer {
	public static final String ID = "poney-fs";
    public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize() {
		LOGGER.info(ID,"Initialized MOD!!");

		FsBlocks.Initialize();
		FsBlockEntities.Initialize();
		FsItems.Initialize();
		FsScreens.Initialize();

	}
}