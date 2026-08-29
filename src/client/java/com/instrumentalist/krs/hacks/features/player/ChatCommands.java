package com.instrumentalist.krs.hacks.features.player;

import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.utils.value.TextValue;
import org.lwjgl.glfw.GLFW;

public class ChatCommands extends Module {

    @Setting
    public static final TextValue prefix = new TextValue("前缀", ".");

    public ChatCommands() {
        super("聊天命令", ModuleCategory.Player, GLFW.GLFW_KEY_UNKNOWN, true, false);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }

}
