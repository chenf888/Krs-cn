package com.instrumentalist.krs.hacks.features.movement;

import com.instrumentalist.krs.events.features.UpdateEvent;
import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.utils.move.MovementUtil;
import com.instrumentalist.krs.utils.value.FloatValue;
import com.instrumentalist.krs.utils.value.ListValue;
import org.lwjgl.glfw.GLFW;

public class FastFall extends Module {
    @Setting
    private final ListValue mode = new ListValue("模式", new String[]{"Shift", "Always"}, "Shift");

    @Setting
    private final FloatValue motion = new FloatValue("运动", 0.8f, 0.1f, 4.0f);

    public FastFall() {
        super("快速下落", ModuleCategory.Movement, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    @Override
    public String tag() {
        return mode.get();
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onUpdate(UpdateEvent event) {
        var player = mc.player;
        if (player == null || player.onGround() || player.isFallFlying() || player.isInWater() || player.isInLava())
            return;

        if (mode.get().equalsIgnoreCase("shift") && !mc.options.keyShift.isDown())
            return;

        if (player.getDeltaMovement().y < 0.0)
            MovementUtil.setVelocityY(-motion.get());
    }
}
