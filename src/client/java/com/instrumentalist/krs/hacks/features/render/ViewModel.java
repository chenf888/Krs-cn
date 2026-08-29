package com.instrumentalist.krs.hacks.features.render;

import com.instrumentalist.krs.Client;
import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.hacks.ModuleManager;
import com.instrumentalist.krs.utils.packet.PacketUtil;
import com.instrumentalist.krs.utils.value.BooleanValue;
import com.instrumentalist.krs.utils.value.FloatValue;
import com.instrumentalist.krs.utils.value.IntValue;
import com.instrumentalist.krs.utils.value.ListValue;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundSwingPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import org.lwjgl.glfw.GLFW;

import java.util.Locale;

public class ViewModel extends Module {

    @Setting
    public static final FloatValue itemSize = new FloatValue("物品大小", 0f, -2f, 2f);

    @Setting
    public static final FloatValue itemFov = new FloatValue("物品视野", 0f, -2f, 2f);

    @Setting
    public static final FloatValue itemPositionX = new FloatValue("物品位置X", 0f, -2f, 2f);

    @Setting
    public static final FloatValue itemPositionY = new FloatValue("物品位置Y", 0f, -2f, 2f);

    @Setting
    public static final FloatValue itemPositionZ = new FloatValue("物品位置Z", 0f, -2f, 2f);

    @Setting
    public static final BooleanValue noEquipAnimation = new BooleanValue("无装备动画", false);

    @Setting
    public static final BooleanValue fluxSwing = new BooleanValue("Flux挥动", false);

    @Setting
    public static final BooleanValue oldEatSwing = new BooleanValue("旧版进食挥动", false);

    @Setting
    public static final BooleanValue oldItemPosition = new BooleanValue("旧版物品位置", false);

    @Setting
    public static final BooleanValue punchingABlockWhileDoingStuff = new BooleanValue("做动作时攻击方块", false);

    @Setting
    private static final BooleanValue slowSwing = new BooleanValue("慢速挥动", false);

    @Setting
    private static final IntValue setSwingSpeed = new IntValue("设置挥动速度", 5, -5, 20, slowSwing::get);

    public ViewModel() {
        super("视角模型", ModuleCategory.Render, GLFW.GLFW_KEY_UNKNOWN, true, false);
    }

    @Override
    public void onDisable() {
        if (Client.loaded)
            this.toggle();
    }

    @Override
    public void onEnable() {
    }

    public static int hookSwingSpeed(int original, Entity entity) {
        if (ModuleManager.getModuleState(ViewModel.class) && slowSwing.get() && entity instanceof LocalPlayer)
            return 6 + setSwingSpeed.get();

        return original;
    }

}
