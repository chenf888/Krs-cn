package com.instrumentalist.krs.hacks.features.render;

import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.utils.value.BooleanValue;
import org.lwjgl.glfw.GLFW;

public class AntiBlind extends Module {

    @Setting
    public static final BooleanValue fire = new BooleanValue("火焰", true);

    @Setting
    public static final BooleanValue pumpkin = new BooleanValue("南瓜", true);

    @Setting
    public static final BooleanValue camera = new BooleanValue("相机", true);

    @Setting
    public static final BooleanValue effects = new BooleanValue("效果", true);

    public AntiBlind() {
        super("防失明", ModuleCategory.Render, GLFW.GLFW_KEY_UNKNOWN, false, false);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }

}
