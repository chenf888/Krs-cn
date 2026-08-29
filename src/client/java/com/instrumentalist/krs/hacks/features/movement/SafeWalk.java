package com.instrumentalist.krs.hacks.features.movement;

import com.instrumentalist.krs.events.features.BlockEdgeEvent;
import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.utils.move.MovementUtil;
import com.instrumentalist.krs.utils.value.BooleanValue;
import org.lwjgl.glfw.GLFW;

public class SafeWalk extends Module {
    @Setting
    private final BooleanValue onlyMoving = new BooleanValue("仅移动", false);

    @Setting
    private final BooleanValue ignoreSneak = new BooleanValue("忽略潜行", false);

    public SafeWalk() {
        super("安全行走", ModuleCategory.Movement, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onBlockEdge(BlockEdgeEvent event) {
        var player = mc.player;
        if (player == null || player.isFallFlying() || player.getAbilities().flying)
            return;

        if (onlyMoving.get() && !MovementUtil.isMoving())
            return;

        if (!ignoreSneak.get() && player.isShiftKeyDown())
            return;

        event.cancel();
    }
}
