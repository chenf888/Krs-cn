package com.instrumentalist.krs.hacks.features.render;

import com.instrumentalist.krs.Client;
import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.hacks.ModuleManager;
import org.lwjgl.glfw.GLFW;

public class ClickGui extends Module {

    public ClickGui() {
        super("点击界面", ModuleCategory.Render, GLFW.GLFW_KEY_RIGHT_SHIFT, true, false);
    }

    public static int getOpenGuiKey() {
        ClickGui clickGui = ModuleManager.getModule(ClickGui.class);
        return clickGui != null ? clickGui.key : GLFW.GLFW_KEY_UNKNOWN;
    }

    @Override
    public String description() {
        return "344 is RIGHT_SHIFT";
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        if (Client.loaded)
            this.toggle();
    }
}
