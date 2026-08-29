package com.instrumentalist.krs.hacks.features.level;



import com.instrumentalist.krs.events.features.KeyboardEvent;
import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.utils.value.KeyBindValue;
import com.instrumentalist.krs.utils.value.TextValue;
import org.lwjgl.glfw.GLFW;

public class QuickMacro extends Module {

    public QuickMacro() {
        super("快捷宏", ModuleCategory.Level, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    @Setting
    private final KeyBindValue macroKey = new KeyBindValue("宏按键", GLFW.GLFW_KEY_X);

    @Setting
    private final TextValue messageOrCommand = new TextValue("消息 / 命令", "");

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onKey(KeyboardEvent event) {
        if (mc.player == null) return;

        if (event.action == GLFW.GLFW_PRESS && event.key == macroKey.get()) {
            boolean commandMode = messageOrCommand.get().startsWith("/");
            if (commandMode) {
                mc.player.connection.sendCommand(messageOrCommand.get().substring(1));
            } else {
                mc.player.connection.sendChat(messageOrCommand.get());
            }
        }
    }
}
