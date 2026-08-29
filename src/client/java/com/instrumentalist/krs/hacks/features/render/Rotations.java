package com.instrumentalist.krs.hacks.features.render;

import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.hacks.ModuleManager;
import com.instrumentalist.krs.utils.value.BooleanValue;
import org.lwjgl.glfw.GLFW;

public class Rotations extends Module {
    @Setting
    public static final BooleanValue vanilla = new BooleanValue("原版", true);

    public Rotations() {
        super("旋转", ModuleCategory.Render, GLFW.GLFW_KEY_UNKNOWN, true, false);
    }

    public static boolean shouldUseVanilla() {
        return ModuleManager.getModuleState(Rotations.class) && vanilla.get();
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }
}
