package com.instrumentalist.krs.hacks.features.render;

import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.utils.value.BooleanValue;
import org.lwjgl.glfw.GLFW;

public class HUDHider extends Module {

    public HUDHider() {
        super("HUD隐藏", ModuleCategory.Render, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    @Override
    public String description() {
        return "隐藏 HUD 元素";
    }

    @Setting
    public static final BooleanValue board = new BooleanValue("计分板", true);

    @Setting
    public static final BooleanValue bos = new BooleanValue("老板条", true);

    @Setting
    public static final BooleanValue bar = new BooleanValue("操作栏", true);

    @Setting
    public static final BooleanValue titled = new BooleanValue("标题", true);

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }
}
