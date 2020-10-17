package me.ionar.salhack.module.movement;

import me.ionar.salhack.events.player.EventPlayerUpdate;
import me.ionar.salhack.module.Module;
import me.ionar.salhack.module.Value;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.item.EntityBoat;

public class BoatFlyModule extends Module {

    public BoatFlyModule() {
        super("BoatFly", new String[] {""}, "Fly with boats.", "NONE", 0xffffff, ModuleType.MOVEMENT);
    }

    public static final Value<Double> Height = new Value<Double>("Height", new String[] {""}, "Height for boat fly", 1.0,1.0,2.0,0.1);

    @EventHandler
    private final Listener<EventPlayerUpdate> listener = new Listener<>(event -> {
        if (mc.player.getRidingEntity() instanceof EntityBoat) {
            EntityBoat entityBoat = (EntityBoat) mc.player.getRidingEntity();
            if (mc.gameSettings.keyBindJump.isPressed()) {
                entityBoat.motionY = Height.getValue();
            }
        }
    });

}
