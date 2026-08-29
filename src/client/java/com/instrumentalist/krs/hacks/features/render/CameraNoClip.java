package com.instrumentalist.krs.hacks.features.render;

import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.utils.value.FloatValue;
import org.lwjgl.glfw.GLFW;

public class CameraNoClip extends Module {

    @Setting
    public static final FloatValue distance = new FloatValue("距离", 0f, -4f, 100f);

    public CameraNoClip() {
        super("相机穿墙", ModuleCategory.Render, GLFW.GLFW_KEY_UNKNOWN, false, false);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }

}
