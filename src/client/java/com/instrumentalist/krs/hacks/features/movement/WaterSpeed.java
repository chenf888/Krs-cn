package com.instrumentalist.krs.hacks.features.movement;

import com.instrumentalist.krs.events.features.UpdateEvent;
import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.utils.move.MovementUtil;
import com.instrumentalist.krs.utils.value.BooleanValue;
import com.instrumentalist.krs.utils.value.FloatValue;
import com.instrumentalist.krs.utils.value.ListValue;
import org.lwjgl.glfw.GLFW;

public class WaterSpeed extends Module {
    @Setting
    private final ListValue mode = new ListValue("模式", new String[]{"Scale", "Strafe", "Boost"}, "Scale");

    @Setting
    private final FloatValue multiplier = new FloatValue("倍率", 1.2f, 1.0f, 3.0f);

    @Setting
    private final FloatValue maxSpeed = new FloatValue("最大速度", 0.5f, 0.1f, 2.0f);

    @Setting
    private final BooleanValue lava = new BooleanValue("岩浆", false);

    public WaterSpeed() {
        super("水中加速", ModuleCategory.Movement, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public String tag() {
        return mode.get();
    }

    @Override
    public void onUpdate(UpdateEvent event) {
        var player = mc.player;
        if (player == null || !MovementUtil.isMoving() || !player.isInWater() && (!lava.get() || !player.isInLava()))
            return;

        if (mode.get().equalsIgnoreCase("strafe"))
            MovementUtil.strafe(maxSpeed.get());
        else if (mode.get().equalsIgnoreCase("boost"))
            MovementUtil.boost((multiplier.get() - 1.0f) * 0.08f, maxSpeed.get());
        else
            MovementUtil.scaleVelocityXZ(multiplier.get());
        MovementUtil.limitSpeed(maxSpeed.get());
    }
}
