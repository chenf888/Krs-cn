package com.instrumentalist.krs.hacks.features.level;

import com.instrumentalist.krs.events.features.UpdateEvent;
import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.hacks.ModuleManager;
import com.instrumentalist.krs.hacks.features.player.ChatCommands;
import com.instrumentalist.krs.utils.math.MSTimer;
import com.instrumentalist.krs.utils.math.RandomUtil;
import com.instrumentalist.krs.utils.value.BooleanValue;
import com.instrumentalist.krs.utils.value.IntValue;
import com.instrumentalist.krs.utils.value.ListValue;
import com.instrumentalist.krs.utils.value.TextValue;
import org.lwjgl.glfw.GLFW;
import xyz.breadloaf.imguimc.customwindow.ModuleRenderable;

public class Spammer extends Module {
    @Setting
    private final ListValue mode = new ListValue("模式", new String[]{"Normal"}, "Normal");

    @Setting
    private final TextValue message = new TextValue("消息", "cocaine", () -> mode.get().equalsIgnoreCase("normal"));

    @Setting
    private final IntValue delay = new IntValue("延迟", 1010, 0, 3000);

    @Setting
    private final TextValue firstChar = new TextValue("首字符", "");

    @Setting
    private final BooleanValue randomSuffix = new BooleanValue("随机后缀", true);

    @Setting
    private final BooleanValue partyChatMode = new BooleanValue("队伍聊天模式", false);

    private final MSTimer delayTimer = new MSTimer();

    public Spammer() {
        super("刷屏器", ModuleCategory.Level, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }

    @Override
    public String tag() {
        return mode.get();
    }

    private void doFunctions() {
        if (mode.get().equalsIgnoreCase("normal")) {
            sendChat(message.get());
        }
    }

    private void sendChat(String message) {
        if (message == null) return;

        var player = mc.player;
        if (player == null) return;
        var connection = player.connection;
        String prefix = firstChar.get();
        boolean randomize = randomSuffix.get();

        if (partyChatMode.get()) {
            if (randomize)
                connection.sendCommand("pc " + prefix + message + " " + RandomUtil.randomString(3));
            else connection.sendCommand("pc " + prefix + message);
        } else {
            if (ModuleManager.getModuleState(ChatCommands.class) && message.startsWith(ChatCommands.prefix.get())) {
                String clearMessage = message.substring(ChatCommands.prefix.get().length());
                ModuleRenderable.executeCommand(clearMessage, true);
            } else if (message.startsWith("/")) {
                String clearMessage = message.substring(1);
                if (randomize)
                    connection.sendCommand(prefix + clearMessage + " " + RandomUtil.randomString(3));
                else connection.sendCommand(prefix + clearMessage);
            } else {
                if (randomize)
                    connection.sendChat(prefix + message + " " + RandomUtil.randomString(3));
                else connection.sendChat(prefix + message);
            }
        }
    }

    @Override
    public void onUpdate(UpdateEvent event) {
        if (mc.player == null) return;

        if (delayTimer.hasTimePassed(delay.get())) {
            doFunctions();
            delayTimer.reset();
        }
    }
}
