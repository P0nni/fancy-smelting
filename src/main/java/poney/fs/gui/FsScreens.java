package poney.fs.gui;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.resource.featuretoggle.ToggleableFeature;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import poney.fs.FancySmelting;

public class FsScreens {

    public static final ScreenHandlerType<FurnaceScreenHandler> FURNACE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(FancySmelting.ID, "furnace_fs"),
                    new ExtendedScreenHandlerType<>(FurnaceScreenHandler::new));


    public static void Initialize(){
        FancySmelting.LOGGER.info(FancySmelting.ID, "Initialized Screens!!");
    }

}
