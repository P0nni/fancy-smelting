package poney.fs;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import poney.fs.datagen.FsBlockTagProvider;
import poney.fs.datagen.FsLootTableProvider;
import poney.fs.datagen.FsRecipeProvider;

public class FancySmeltingDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(FsBlockTagProvider::new);
		pack.addProvider(FsRecipeProvider::new);
		pack.addProvider(FsLootTableProvider::new);
	}
}
