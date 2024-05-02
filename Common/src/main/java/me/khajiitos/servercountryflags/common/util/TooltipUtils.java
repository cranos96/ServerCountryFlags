package me.khajiitos.servercountryflags.common.util;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.List;

public class TooltipUtils {

    public static List<FormattedCharSequence> getTooltipOfScreen(@NotNull Screen screen) {
        // TODO: find a way not to use Reflection

        try {
            Field field = Screen.class.getDeclaredField("deferredTooltipRendering");
            field.setAccessible(true);
            Object deferredTooltipRendering = field.get(screen);

            if (deferredTooltipRendering != null) {
                Field tooltipField = deferredTooltipRendering.getClass().getDeclaredField("tooltip");
                tooltipField.setAccessible(true);
                return (List<FormattedCharSequence>) tooltipField.get(deferredTooltipRendering);
            }

        } catch (NoSuchFieldException | IllegalAccessException ignored) {}

        return null;
    }

    public static @NotNull List<FormattedCharSequence> getTooltipOfScreenOrEmpty(@NotNull Screen screen) {
        List<FormattedCharSequence> list = getTooltipOfScreen(screen);
        return list != null ? list : List.of();
    }
}
