package net.farugames.hub.managers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import net.farugames.hub.FaruHub;
import net.farugames.hub.listeners.player.PlayerJoinListener;

public class ListenerManager {

	public Plugin plugin;
	public PluginManager pluginManager;
    
    public ListenerManager() {
        this.plugin = FaruHub.getInstance();
        this.pluginManager = Bukkit.getPluginManager();
    }
    
    public void register() {
    	pluginManager.registerEvents(new PlayerJoinListener(), this.plugin);
    }
}
