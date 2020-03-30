package com.davizin.anticheat.listeners;

import com.davizin.anticheat.packets.PacketHandler;
import com.davizin.anticheat.models.cache.PlayerDataCache;
import com.davizin.anticheat.models.data.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class PlayerListenerEvents implements Listener {

    public PlayerListenerEvents(Plugin plugin, PluginManager manager) {
        manager.registerEvents(this, plugin);
    }

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event) {
        PlayerData playerData = new PlayerData(event.getPlayer().getUniqueId());
        PlayerDataCache.playerData.put(event.getPlayer().getUniqueId().toString(), playerData);
        new PacketHandler().inject(playerData);
    }

    @EventHandler
    public void playerLeaveEvent(PlayerQuitEvent event) {
        PlayerData playerData = PlayerDataCache.playerData.get(event.getPlayer().getUniqueId().toString());
        new PacketHandler().remove(playerData);
        PlayerDataCache.playerData.remove(event.getPlayer().getUniqueId().toString());
    }

}
