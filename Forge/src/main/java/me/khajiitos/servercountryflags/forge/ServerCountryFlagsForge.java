package me.khajiitos.servercountryflags.forge;

import me.khajiitos.servercountryflags.common.ServerCountryFlags;
import me.khajiitos.servercountryflags.common.config.ClothConfigCheck;
import me.khajiitos.servercountryflags.common.config.ClothConfigScreenMaker;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;

import java.util.function.Function;

@Mod(ServerCountryFlags.MOD_ID)
public class ServerCountryFlagsForge {

    public ServerCountryFlagsForge() {
        if (FMLLoader.getDist() == Dist.CLIENT) {
            ServerCountryFlags.init();

            if (ClothConfigCheck.isInstalled()) {
                ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((Function<Screen, Screen>) ClothConfigScreenMaker::create));
            }
        }
    }
}