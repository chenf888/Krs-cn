package com.instrumentalist.krs.hacks.features.player;

import com.instrumentalist.krs.events.features.UpdateEvent;
import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.utils.math.TimerUtil;
import com.instrumentalist.krs.utils.move.MovementUtil;
import com.instrumentalist.krs.utils.value.BooleanValue;
import com.instrumentalist.krs.utils.value.FloatValue;
import com.instrumentalist.krs.utils.value.ListValue;
import org.lwjgl.glfw.GLFW;

import java.util.Locale;

public class FastLadder extends Module {
    @Setting
    private final ListValue mode = new ListValue("模式", new String[]{"Vanilla", "Timer"}, "Vanilla");

    @Setting
    private final BooleanValue resetY = new BooleanValue("重置Y", true, () -> mode.get().equalsIgnoreCase("vanilla"));

    @Setting
    private final FloatValue speed = new FloatValue("速度", 0.4f, 0.2f, 1f, () -> mode.get().equalsIgnoreCase("vanilla"));

    @Setting
    private final FloatValue timerSpeed = new FloatValue("计时器速度", 2f, 1.1f, 3f, () -> mode.get().equalsIgnoreCase("timer"));

    private boolean wasClimbing = false;

    public FastLadder() {
        super("快速爬梯", ModuleCategory.Player, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    @Override
    public void onDisable() {
        if (mc.player == null) return;
        wasClimbing = false;
        TimerUtil.reset();
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onUpdate(UpdateEvent event) {
        var player = mc.player;
        if (player == null) return;

        boolean climbing = player.onClimbable() && (player.horizontalCollision || mc.options.keyJump.isDown());
        switch (mode.get().toLowerCase(Locale.ROOT)) {
            case "vanilla" -> {
                if (climbing) {
                    MovementUtil.setVelocityY(speed.get().doubleValue());
                    wasClimbing = true;
                } else if (wasClimbing) {
                    if (resetY.get())
                        MovementUtil.setVelocityY(-0.1);
                    wasClimbing = false;
                }
            }
            case "timer" -> {
                if (climbing) {
                    TimerUtil.timerSpeed = timerSpeed.get();
                    wasClimbing = true;
                } else if (wasClimbing) {
                    TimerUtil.reset();
                    wasClimbing = false;
                }
            }
        }
    }
}
