package com.instrumentalist.krs.hacks.features.movement;

import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.hacks.ModuleManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PlayerRideable;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import org.lwjgl.glfw.GLFW;

public class EntityControl extends Module {

    public EntityControl() {
        super("实体控制", ModuleCategory.Movement, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    public static LivingEntity hookControllingPassenger(LivingEntity original, Entity entity) {
        if (original != null || !shouldControl(entity))
            return original;

        return mc.player;
    }

    public static boolean shouldTreatAsSaddled(Mob entity) {
        return shouldControl(entity);
    }

    public static boolean shouldTreatAsTamed(Entity entity) {
        return shouldControl(entity);
    }

    private static boolean shouldControl(Entity entity) {
        var player = mc.player;
        return ModuleManager.getModuleState(EntityControl.class)
                && player != null
                && entity != null
                && entity.isAlive()
                && player.isPassenger()
                && player.getVehicle() == entity
                && isSupported(entity);
    }

    private static boolean isSupported(Entity entity) {
        return entity instanceof PlayerRideable
                || entity instanceof ItemSteerable
                || entity instanceof HappyGhast;
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }
}
