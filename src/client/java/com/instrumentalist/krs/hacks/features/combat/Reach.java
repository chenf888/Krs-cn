package com.instrumentalist.krs.hacks.features.combat;

import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.hacks.ModuleManager;
import com.instrumentalist.krs.utils.value.BooleanValue;
import com.instrumentalist.krs.utils.value.FloatValue;
import org.lwjgl.glfw.GLFW;

public class Reach extends Module {

    public Reach() {
        super("攻击距离", ModuleCategory.Combat, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    @Setting
    private static final BooleanValue block = new BooleanValue("方块", true);

    @Setting
    private static final FloatValue blockReach = new FloatValue("方块攻击距离", 6f, 0.1f, 6f, block::get);

    @Setting
    private static final BooleanValue entity = new BooleanValue("实体", true);

    @Setting
    private static final FloatValue entityReach = new FloatValue("实体攻击距离", 6f, 0.1f, 6f, entity::get);

    public static double hookBlockReach(double original) {
        if (ModuleManager.getModuleState(Reach.class) && block.get())
            return blockReach.get();

        return original;
    }

    public static double hookEntityReach(double original) {
        if (ModuleManager.getModuleState(Reach.class) && entity.get())
            return entityReach.get();

        return original;
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }
}