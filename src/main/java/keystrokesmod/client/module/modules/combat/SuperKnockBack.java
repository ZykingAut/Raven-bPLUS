package keystrokesmod.client.module.modules.combat;

import keystrokesmod.client.module.Module;
import keystrokesmod.client.module.modules.world.AntiBot;
import keystrokesmod.client.utils.Utils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import org.lwjgl.input.Mouse;

import static net.minecraft.network.play.client.C0BPacketEntityAction.Action.*;

public class SuperKnockBack extends Module {

    public SuperKnockBack() {
        super("SuperKnockBack", ModuleCategory.combat);
    }

    public void update() {

        if (!Utils.Client.currentScreenMinecraft()) return;
        if (!Utils.Player.isPlayerInGame()) return;

        if (mc.objectMouseOver != null && mc.objectMouseOver.entityHit instanceof Entity && Mouse.isButtonDown(0)) {
            Entity target = mc.objectMouseOver.entityHit;
            if (target.isDead) {
                return;
            }
            if (AntiBot.bot(target)) {
                return;
            }
            EntityPlayerSP p = mc.thePlayer;
            if (p.isSprinting()) {
                mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(p, STOP_SPRINTING));
            }

            if (p.isSneaking()) {
                mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(p, STOP_SNEAKING));
            }

            mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(p, START_SPRINTING));
            mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(p, STOP_SPRINTING));
            mc.getNetHandler().addToSendQueue(new C0BPacketEntityAction(p, START_SPRINTING));

            p.setSprinting(true);
        }
    }
}
