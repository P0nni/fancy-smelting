package poney.fs;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poney.fs.block.FsBlocks;
import poney.fs.block.FsBlockEntities;
import poney.fs.item.FsItems;
import poney.fs.gui.FsScreens;

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