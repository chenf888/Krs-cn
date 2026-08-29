package com.instrumentalist.krs.hacks.features.player;

import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.hacks.ModuleManager;
import com.instrumentalist.krs.utils.ChatUtil;
import com.instrumentalist.krs.utils.value.FloatValue;
import org.lwjgl.glfw.GLFW;

public class FastBreak extends Module {

    public FastBreak() {
        super("快速挖掘", ModuleCategory.Player, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    @Setting
    private static final FloatValue multiplier = new FloatValue("倍率", 1.4f, 0.1f, 3f);

    public static float hookFastBreak(float original) {
        if (ModuleManager.getModuleState(FastBreak.class) && original <= 18) {
            return Math.min(18, original * multiplier.get());
        }

        return original;
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }
}