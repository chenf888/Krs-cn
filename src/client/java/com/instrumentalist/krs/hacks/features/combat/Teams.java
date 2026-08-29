package com.instrumentalist.krs.hacks.features.combat;

import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.utils.entity.EntityExtension;
import com.instrumentalist.krs.utils.value.BooleanValue;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.lwjgl.glfw.GLFW;

public class Teams extends Module {

    @Setting
    private static final BooleanValue scoreboardTeam = new BooleanValue("计分板队伍", true);

    @Setting
    private static final BooleanValue nameColor = new BooleanValue("名字颜色", false);

    @Setting
    private static final BooleanValue prefix = new BooleanValue("前缀", false);

    @Setting
    private static final BooleanValue armorColor = new BooleanValue("盔甲颜色", false);

    @Setting
    private static final BooleanValue helmet = new BooleanValue("头盔", true, armorColor::get);

    @Setting
    private static final BooleanValue chestPlate = new BooleanValue("胸甲", true, armorColor::get);

    @Setting
    private static final BooleanValue leggings = new BooleanValue("护腿", true, armorColor::get);

    @Setting
    private static final BooleanValue boots = new BooleanValue("靴子", true, armorColor::get);

    public Teams() {
        super("队伍识别", ModuleCategory.Combat, GLFW.GLFW_KEY_UNKNOWN, false, false);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }

    public static boolean isInClientPlayersTeam(LivingEntity entity) {
        var player = mc.player;
        if (player == null) return false;

        if (scoreboardTeam.get() && player.isAlliedTo(entity))
            return true;

        Component clientDisplayName = player.getDisplayName();
        Component targetDisplayName = entity.getDisplayName();

        return nameColor.get() && checkName(clientDisplayName, targetDisplayName)
                || prefix.get() && checkPrefix(targetDisplayName, clientDisplayName)
                || armorColor.get() && checkArmor(player, entity);
    }

    private static boolean checkName(Component clientDisplayName, Component targetDisplayName) {
        var targetColor = clientDisplayName.getStyle().getColor();
        var clientColor = targetDisplayName.getStyle().getColor();
        return targetColor != null && clientColor != null && targetColor.equals(clientColor);
    }

    private static boolean checkPrefix(Component targetDisplayName, Component clientDisplayName) {
        String targetName = EntityExtension.stripMinecraftColorCodes(targetDisplayName.getString());
        String clientName = EntityExtension.stripMinecraftColorCodes(clientDisplayName.getString());
        int targetSeparator = targetName.indexOf(' ');
        int clientSeparator = clientName.indexOf(' ');

        return targetSeparator > 0
                && clientSeparator > 0
                && targetName.regionMatches(0, clientName, 0, targetSeparator)
                && targetSeparator == clientSeparator;
    }

    private static boolean checkArmor(Player ownPlayer, LivingEntity entity) {
        if (!(entity instanceof Player player)) return false;

        return helmet.get() && matchesArmorColor(ownPlayer, player, EquipmentSlot.HEAD)
                || chestPlate.get() && matchesArmorColor(ownPlayer, player, EquipmentSlot.CHEST)
                || leggings.get() && matchesArmorColor(ownPlayer, player, EquipmentSlot.LEGS)
                || boots.get() && matchesArmorColor(ownPlayer, player, EquipmentSlot.FEET);
    }

    private static boolean matchesArmorColor(Player ownPlayer, Player player, EquipmentSlot slot) {
        Integer ownColor = EntityExtension.getArmorColor(ownPlayer.getItemBySlot(slot));
        if (ownColor == null) return false;
        Integer otherColor = EntityExtension.getArmorColor(player.getItemBySlot(slot));
        if (otherColor == null) return false;
        return ownColor.equals(otherColor);
    }

}
