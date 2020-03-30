package com.davizin.anticheat.models.data;

import com.davizin.anticheat.core.Check;
import com.davizin.anticheat.core.infractions.BadPackets1;
import com.google.common.collect.Sets;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

@Getter
public class PlayerData {

    public UUID id;

    public Set<Check> checks;

    public PlayerData(UUID id) {
        checks = Sets.newHashSet();

        checks.add(new BadPackets1());

        this.id = id;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(id);
    }

}
