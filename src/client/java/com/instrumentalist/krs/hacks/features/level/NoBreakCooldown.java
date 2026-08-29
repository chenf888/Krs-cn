package com.instrumentalist.krs.hacks.features.level;

import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import org.lwjgl.glfw.GLFW;

public class NoBreakCooldown extends Module {

    public NoBreakCooldown() {
        super("无挖掘冷却", ModuleCategory.Level, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }
}
