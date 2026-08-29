package com.instrumentalist.krs.hacks.features.render;

import com.instrumentalist.krs.Client;
import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.hacks.ModuleManager;
import com.instrumentalist.krs.utils.value.ListValue;
import org.lwjgl.glfw.GLFW;

public class ImGui extends Module {

    public ImGui() {
        super("ImGui界面", ModuleCategory.Render, GLFW.GLFW_KEY_UNKNOWN, true, false);
    }

    public static int getOpenGuiKey() {
        ImGui imGui = ModuleManager.getModule(ImGui.class);
        return imGui != null ? imGui.key : GLFW.GLFW_KEY_UNKNOWN;
    }

    @Setting
    public static final ListValue theme = new ListValue("主题",
            new String[]{"Light", "Classic", "Dark"},
            "Classic"
    );

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
