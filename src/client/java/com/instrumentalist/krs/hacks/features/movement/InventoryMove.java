package com.instrumentalist.krs.hacks.features.movement;



import com.instrumentalist.krs.hacks.Module;
import com.instrumentalist.krs.hacks.ModuleCategory;
import com.instrumentalist.krs.hacks.ModuleManager;
import com.instrumentalist.krs.utils.value.BooleanValue;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import org.lwjgl.glfw.GLFW;
import xyz.breadloaf.imguimc.screen.EmptyScreen;

public class InventoryMove extends Module {

    public InventoryMove() {
        super("背包移动", ModuleCategory.Movement, GLFW.GLFW_KEY_UNKNOWN, false, true);
    }

    @Setting
    private static final BooleanValue inventoryOnly = new BooleanValue("仅背包", false);

    public static void moveFreely() {
        if (canMoveFreely() && mc.gui.screen() != null) {
            KeyMapping.setAll();
            KeyMapping sneakKey = mc.options.keyShift;
            sneakKey.setDown(false);
        }
    }

    public static boolean canMoveFreely() {
        return canMoveFreely(mc.gui.screen());
    }

    public static boolean canMoveFreely(Screen screen) {
        return screen == null
                || screen instanceof EmptyScreen
                || ModuleManager.getModuleState(InventoryMove.class)
                && (!inventoryOnly.get() || screen instanceof InventoryScreen)
                && !(screen instanceof ChatScreen);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }
}
