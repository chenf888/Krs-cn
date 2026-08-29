package com.instrumentalist.krs.hacks.features.render;

import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import org.lwjgl.glfw.GLFW;

public class EntityYawFix extends Module {
    public EntityYawFix() {
        super("实体偏航修正", ModuleCategory.Render, GLFW.GLFW_KEY_UNKNOWN, false, false);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }
}
