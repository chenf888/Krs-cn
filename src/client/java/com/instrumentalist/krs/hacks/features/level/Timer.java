package com.instrumentalist.krs.hacks.features.level;

import com.instrumentalist.krs.events.features.TickEvent;
import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.utils.math.TimerUtil;
import com.instrumentalist.krs.utils.move.MovementUtil;
import com.instrumentalist.krs.utils.value.BooleanValue;
import com.instrumentalist.krs.utils.value.FloatValue;
import com.instrumentalist.krs.utils.value.ListValue;
import org.lwjgl.glfw.GLFW;

import java.util.Locale;

public class Timer extends Module {

    @Setting
    public static final ListValue mode = new ListValue("模式", new String[]{"Vanilla", "Rounding Error"}, "Vanilla");

    @Setting
    private static final FloatValue speed = new FloatValue("速度", 1.5f, 0.1f, 10f, () -> mode.get().equalsIgnoreCase("vanilla"));

    @Setting
    private static final BooleanValue moveOnly = new BooleanValue("仅移动", false, () -> mode.get().equalsIgnoreCase("vanilla"));

    public Timer() {
        super("计时器", ModuleCategory.Level, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    @Override
    public void onDisable() {
        TimerUtil.reset();
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onTick(TickEvent event) {
        if (mode.get().toLowerCase(Locale.ROOT).equals("vanilla")) {
            if (moveOnly.get() && !MovementUtil.isMoving())
                TimerUtil.reset();
            else TimerUtil.timerSpeed = speed.get();
        }

        if (mode.get().toLowerCase(Locale.ROOT).equals("rounding error")) {
            TimerUtil.timerSpeed = 1.0075f;
        }
    }
}
