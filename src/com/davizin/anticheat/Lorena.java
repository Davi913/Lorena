package com.davizin.anticheat;

import com.davizin.anticheat.listeners.PlayerListenerEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Lorena extends JavaPlugin {

    @Override
    public void onEnable() {
        registerListeners();
    }

    private void registerListeners() {
        PluginManager manager = Bukkit.getPluginManager();
        new PlayerListenerEvents(this, manager);

    }


    public static Lorena getInstance() {
        return JavaPlugin.getPlugin(Lorena.class);
    }

}
