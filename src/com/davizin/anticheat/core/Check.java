package com.davizin.anticheat.core;

import com.davizin.anticheat.models.annotations.CheckHandler;
import com.davizin.anticheat.models.data.PlayerData;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Bukkit;

@Getter
public abstract class Check {

    private String name, description;
    private CheckHandler.CheckType check;
    private int violations;
    private int maxViolations;

    public Check() {
        if (this.getClass().isAnnotationPresent(CheckHandler.class)) {
            CheckHandler type = this.getClass().getAnnotation(CheckHandler.class);
            this.name = type.name();
            this.check = type.check();
            this.maxViolations = type.maxViolations();
            this.description = type.description();
        }
    }

    public void flag(PlayerData data) {
        if (violations++ >= maxViolations) {
           Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + Bukkit.getPlayer(data.id).getName() + " " + name);
       }
    }

    public abstract void check(PlayerData playerData, Packet<?> packet);

}
