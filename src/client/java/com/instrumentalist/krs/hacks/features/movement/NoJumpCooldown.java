package com.instrumentalist.krs.hacks.features.movement;



import com.instrumentalist.krs.events.features.UpdateEvent;
import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import org.lwjgl.glfw.GLFW;

public class NoJumpCooldown extends Module {

    public NoJumpCooldown() {
        super("无跳跃冷却", ModuleCategory.Movement, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onUpdate(UpdateEvent event) {
        if (mc.player == null || !mc.player.onGround()) return;

        mc.player.noJumpDelay = 0;
    }
}
