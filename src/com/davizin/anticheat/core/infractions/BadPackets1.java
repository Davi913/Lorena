package com.davizin.anticheat.core.infractions;

import com.davizin.anticheat.core.Check;
import com.davizin.anticheat.models.annotations.CheckHandler;
import com.davizin.anticheat.models.data.PlayerData;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.Bukkit;

@CheckHandler(name = "Badpackets 1", check = CheckHandler.CheckType.BETA,
        maxViolations = 1, description = "Checar se o player não virou um exorcista")
public class BadPackets1 extends Check {

    @Override
    public void check(PlayerData playerData, Packet<?> packet) {
        if (packet instanceof PacketPlayInFlying.PacketPlayInLook || packet instanceof PacketPlayInFlying.PacketPlayInPositionLook) {
            if (Math.abs(Bukkit.getPlayer(playerData.id).getLocation().getYaw()) > 90) {

            }
        }
    }
}