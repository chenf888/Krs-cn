package com.instrumentalist.krs.hacks.features.render;

import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.utils.value.BooleanValue;
import org.lwjgl.glfw.GLFW;

public class ClientCape extends Module {

    @Setting
    public static final BooleanValue customCape = new BooleanValue("自定义披风", true);

    @Setting
    public static final BooleanValue capeOverride = new BooleanValue("披风覆盖", true, customCape::get);

    @Setting
    public static final BooleanValue enchantmentGlint = new BooleanValue("附魔光泽", true);

    @Setting
    public static final BooleanValue oldCapeMovement = new BooleanValue("1.8披风摆动", true);

    public ClientCape() {
        super("客户端披风", ModuleCategory.Render, GLFW.GLFW_KEY_UNKNOWN, false, false);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }

}
