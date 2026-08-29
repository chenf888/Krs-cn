package com.instrumentalist.krs.hacks.features.player;



import com.instrumentalist.krs.Client;
import com.instrumentalist.krs.events.features.UpdateEvent;
import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.utils.value.FloatValue;
import org.lwjgl.glfw.GLFW;

public class SpinBot extends Module {

    public SpinBot() {
        super("旋转机器人", ModuleCategory.Player, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    @Setting
    private final FloatValue spinSpeed = new FloatValue("旋转速度",
            30f,
            -40f,
            40f
    );

    @Setting
    private final FloatValue pitch = new FloatValue("俯仰角",
            90f,
            -90f,
            90f
    );

    private static float spinYaw = 0f;

    @Override
    public void onDisable() {
        if (mc.player == null) return;

        spinYaw = 0f;
        Client.rotationManager.stopRotation();
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onUpdate(UpdateEvent event) {
        if (mc.player == null) return;

        if (spinYaw >= 360f || spinYaw <= -360f)
            spinYaw = 0f;

        spinYaw += spinSpeed.get();

        Client.rotationManager.startRotation(spinYaw, pitch.get(), 180f);
    }
}
