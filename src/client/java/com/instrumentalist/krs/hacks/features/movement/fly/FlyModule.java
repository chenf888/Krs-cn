package com.instrumentalist.krs.hacks.features.movement.fly;

import com.instrumentalist.krs.events.features.*;
import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.hacks.features.movement.fly.features.*;
import com.instrumentalist.krs.utils.math.TimerUtil;
import com.instrumentalist.krs.utils.move.MovementUtil;
import com.instrumentalist.krs.utils.value.BooleanValue;
import com.instrumentalist.krs.utils.value.FloatValue;
import com.instrumentalist.krs.utils.value.IntValue;
import com.instrumentalist.krs.utils.value.ListValue;
import org.lwjgl.glfw.GLFW;

import net.minecraft.world.phys.Vec3;

public class FlyModule extends Module {

    public FlyModule() {
        super("飞行", ModuleCategory.Movement, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    @Setting
    private static final ListValue flyMode = new ListValue("飞行模式",
            new String[]{"Vanilla", "Creative", "Airwalk", "Jetpack", "Float", "NCP Boost", "Negative Packet", "2b2tJP Control Fast", "Modern MMC", "Vulcan Glide", "Verus Jetpack", "Matrix", "Grim 1.9-1.18.1"},
            "Vanilla"
    );

    private static final FlyModeManager flyModeManager = new FlyModeManager(flyMode);

    @Setting
    public static final BooleanValue airViewBobbing = new BooleanValue("空中视角摆动", false);

    // Vanilla
    @Setting
    public static final FloatValue vanillaHSpeed = new FloatValue("水平速度", 2f, 0.1f, 10f, () -> flyMode.get().equalsIgnoreCase("vanilla"));

    @Setting
    public static final FloatValue vanillaVSpeed = new FloatValue("垂直速度", 1.2f, 0.1f, 10f, () -> flyMode.get().equalsIgnoreCase("vanilla"));

    // Matrix
    @Setting
    public static final ListValue matrixMode = new ListValue("Matrix 模式", new String[]{"Normal", "High", "Damage"}, "Normal", () -> flyMode.get().equalsIgnoreCase("matrix"));

    @Setting
    public static final BooleanValue matrixHighSelfDamage = new BooleanValue("Matrix 高空自伤", false, () -> flyMode.get().equalsIgnoreCase("matrix") && matrixMode.get().equalsIgnoreCase("high"));

    @Setting
    public static final FloatValue matrixHighHeight = new FloatValue("Matrix 高度", 1f, 0.42f, 7f, () -> flyMode.get().equalsIgnoreCase("matrix") && matrixMode.get().equalsIgnoreCase("high"));

    @Setting
    public static final FloatValue matrixDamageTimerSpeed = new FloatValue("Matrix 计时器速度", 30f, 1f, 100f, () -> flyMode.get().equalsIgnoreCase("matrix") && matrixMode.get().equalsIgnoreCase("damage"));

    @Setting
    public static final FloatValue matrixDamageSpeed = new FloatValue("Matrix 速度", 0.07f, 0f, 1f, () -> flyMode.get().equalsIgnoreCase("matrix") && matrixMode.get().equalsIgnoreCase("damage"));

    @Setting
    public static final BooleanValue matrixDamageNoSpeed = new BooleanValue("Matrix 无速度", false, () -> flyMode.get().equalsIgnoreCase("matrix") && matrixMode.get().equalsIgnoreCase("damage"));

    @Setting
    public static final BooleanValue matrixDamageDetectDamage = new BooleanValue("Matrix 检测伤害", true, () -> flyMode.get().equalsIgnoreCase("matrix") && matrixMode.get().equalsIgnoreCase("damage"));

    @Setting
    public static final BooleanValue matrixDamageAutoDisable = new BooleanValue("Matrix 自动禁用", true, () -> flyMode.get().equalsIgnoreCase("matrix") && matrixMode.get().equalsIgnoreCase("damage"));

    @Setting
    public static final IntValue matrixDamageFlyTicks = new IntValue("Matrix 飞行刻", 1000, 0, 1600, () -> flyMode.get().equalsIgnoreCase("matrix") && matrixMode.get().equalsIgnoreCase("damage") && matrixDamageDetectDamage.get() && !matrixDamageAutoDisable.get());

    @Setting
    public static final BooleanValue matrixDamageSelfDamage = new BooleanValue("Matrix 伤害自伤", true, () -> flyMode.get().equalsIgnoreCase("matrix") && matrixMode.get().equalsIgnoreCase("damage"));

    @Setting
    public static final BooleanValue matrixDamageNewSelfDamage = new BooleanValue("Matrix 新自伤", false, () -> flyMode.get().equalsIgnoreCase("matrix") && matrixMode.get().equalsIgnoreCase("damage"));

    @Setting
    public static final BooleanValue matrixDamagePacket = new BooleanValue("Matrix 数据包", true, () -> flyMode.get().equalsIgnoreCase("matrix") && matrixMode.get().equalsIgnoreCase("damage"));

    @Setting
    public static final ListValue matrixDamageMotionY = new ListValue("Matrix 垂直运动", new String[]{"None", "Simple", "Multiply"}, "None", () -> flyMode.get().equalsIgnoreCase("matrix") && matrixMode.get().equalsIgnoreCase("damage"));

    @Setting
    public static final FloatValue matrixDamageMotion = new FloatValue("Matrix 运动", -0.01f, -0.3f, 0.3f, () -> flyMode.get().equalsIgnoreCase("matrix") && matrixMode.get().equalsIgnoreCase("damage") && !matrixDamageMotionY.get().equalsIgnoreCase("none"));

    // Grim 1.9-1.18.1
    @Setting
    public static final FloatValue grimSpeed = new FloatValue("Grim 速度", 0.275f, 0f, 0.32f, () -> flyMode.get().equalsIgnoreCase("grim 1.9-1.18.1"));

    @Setting
    public static final FloatValue grimTimerSpeed = new FloatValue("Grim 计时器速度", 1f, 0.1f, 10f, () -> flyMode.get().equalsIgnoreCase("grim 1.9-1.18.1"));

    @Setting
    public static final BooleanValue grimSlowFall = new BooleanValue("Grim 缓降", false, () -> flyMode.get().equalsIgnoreCase("grim 1.9-1.18.1"));

    // Verus Jetpack
    @Setting
    public static final BooleanValue verusJetpackJumpKeyOnly = new BooleanValue("Verus 仅跳跃键", false, () -> flyMode.get().equalsIgnoreCase("verus jetpack"));

    // NCP Boost
    @Setting
    public static final BooleanValue ncpDamageBoost = new BooleanValue("NCP 伤害加成", true, () -> flyMode.get().equalsIgnoreCase("ncp boost"));

    public static void onEnableFunctions() {
        if (flyModeManager.currentMode instanceof MatrixFly matrixFly) {
            matrixFly.reset();
        } else if (flyModeManager.currentMode instanceof Grim19To1181Fly grim19To1181Fly) {
            grim19To1181Fly.reset();
        }
    }

    public static void onDisableFunctions() {
        if (flyModeManager.currentMode instanceof VanillaFly) {
            if (mc.player != null) {
                MovementUtil.stopMoving();
                MovementUtil.setVelocityY(0.0);
            }
        } else if (flyModeManager.currentMode instanceof TwoBTwoTJPControlFastFly) {
            if (mc.player != null) {
                MovementUtil.stopMoving();
                MovementUtil.setVelocityY(0.0);
            }
            TimerUtil.reset();
            TwoBTwoTJPControlFastFly.tick = 0;
            TwoBTwoTJPControlFastFly.phasing = false;
            TwoBTwoTJPControlFastFly.boostSpeed = 1f;
        } else if (flyModeManager.currentMode instanceof ModernMMCFly) {
            MovementUtil.stopMoving();
            MovementUtil.setVelocityY(0.0);
            ModernMMCFly.tick = 0;
        } else if (flyModeManager.currentMode instanceof CreativeFly) {
            if (mc.player != null) {
                if (mc.player.isSpectator() || !mc.player.isCreative())
                    mc.player.getAbilities().mayfly = false;
                if (!mc.player.isCreative())
                    mc.player.getAbilities().flying = false;
            }
        } else if (flyModeManager.currentMode instanceof NegativePacketFly) {
            NegativePacketFly.motion = Vec3.ZERO;
        } else if (flyModeManager.currentMode instanceof MatrixFly matrixFly) {
            matrixFly.disable();
        } else if (flyModeManager.currentMode instanceof Grim19To1181Fly grim19To1181Fly) {
            grim19To1181Fly.disable();
        } else if (flyModeManager.currentMode instanceof VulcanGlideFly) {
            VulcanGlideFly.ticks = 0;
        } else if (flyModeManager.currentMode instanceof NCPBoostFly) {
            NCPBoostFly.moveSpeed = 0;
            if (NCPBoostFly.timer) {
                TimerUtil.reset();
                NCPBoostFly.timer = false;
            }
            if (mc.player != null) {
                MovementUtil.stopMoving();
            }
        }
    }

    @Override
    public String tag() {
        return flyMode.get();
    }

    @Override
    public void onDisable() {
        onDisableFunctions();
    }

    @Override
    public void onEnable() {
        onEnableFunctions();
    }

    @Override
    public void onUpdate(UpdateEvent event) {
        flyModeManager.onUpdate(event);
    }

    @Override
    public void onMotion(MotionEvent event) {
        flyModeManager.onMotion(event);
    }

    @Override
    public void onSendPacket(SendPacketEvent event) {
        flyModeManager.onSendPacket(event);
    }

    @Override
    public void onTick(TickEvent event) {
        flyModeManager.onTick(event);
    }

    @Override
    public void onReceivedPacket(ReceivedPacketEvent event) {
        flyModeManager.onReceivedPacket(event);
    }

    @Override
    public void onBlock(BlockEvent event) {
        flyModeManager.onBlock(event);
    }
}
