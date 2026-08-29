package com.instrumentalist.krs.hacks.features.render;



import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.hacks.ModuleManager;
import com.instrumentalist.krs.utils.value.BooleanValue;
import com.instrumentalist.krs.utils.value.ListValue;
import org.lwjgl.glfw.GLFW;

import java.util.Locale;

public class WorldTime extends Module {

    public WorldTime() {
        super("世界时间", ModuleCategory.Render, GLFW.GLFW_KEY_UNKNOWN, false, false);
    }

    @Setting
    private static final ListValue time = new ListValue("时间段",
            new String[]{"Day", "Night", "Midnight", "Sunrise"},
            "Midnight"
    );

    @Setting
    public static final BooleanValue clearWeather = new BooleanValue("晴朗天气", true);

    public static Long getActiveTimeTicks() {
        if (!ModuleManager.getModuleState(WorldTime.class) || mc.level == null)
            return null;

        return switch (time.get().toLowerCase(Locale.ROOT)) {
            case "day" -> 1000L;
            case "night" -> 13000L;
            case "midnight" -> 18000L;
            case "sunrise" -> 0L;
            default -> null;
        };
    }

    public static boolean shouldClearWeather() {
        return ModuleManager.getModuleState(WorldTime.class) && clearWeather.get();
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }
}
