package com.instrumentalist.krs.hacks.features.movement;

import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.hacks.ModuleManager;
import org.lwjgl.glfw.GLFW;

public class PerfectHorseJump extends Module {

    public PerfectHorseJump() {
        super("完美马跳", ModuleCategory.Movement, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    public static float modifiedHorseJump(float original) {
        if (ModuleManager.getModuleState(PerfectHorseJump.class))
            return 1f;

        return original;
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }
}
