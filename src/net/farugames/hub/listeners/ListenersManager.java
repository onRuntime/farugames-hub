package net.farugames.hub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import net.farugames.hub.listeners.player.PlayerJoinListener;
import net.farugames.hub.listeners.player.PlayerQuitListener;

public class ListenersManager {
	
	public Plugin plugin;
	public PluginManager pluginManager;
	
	public ListenersManager(Plugin plugin) {
		this.plugin = plugin;
		this.pluginManager = Bukkit.getPluginManager();
	}
	
	public void registerListeners() {
		pluginManager.registerEvents(new PlayerJoinListener(), plugin);
		pluginManager.registerEvents(new PlayerQuitListener(), plugin);
	}

}
