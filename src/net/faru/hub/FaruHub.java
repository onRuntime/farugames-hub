package net.faru.hub;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import net.faru.api.spigot.SpigotFaruAPI;
import net.faru.api.spigot.listeners.Listeners;
import net.faru.hub.managers.ListenerManager;

public class FaruHub extends JavaPlugin {

	private Map<UUID, FaruHubPlayer> playerHubMap = new HashMap<UUID, FaruHubPlayer>();
	private static FaruHub instance;
	
	public void onLoad() {
		instance = this;
		
		super.onLoad();
	}
	
	public void onEnable() {
		SpigotFaruAPI.getInstance().disableEvent(Listeners.FOOD_LEVEL_CHANGE_LISTENER.getListener());
		SpigotFaruAPI.getInstance().disableEvent(Listeners.WEATHER_CHANGE_LISTENER.getListener());
		new ListenerManager().register();
		
		super.onEnable();
	}
	
	public void onDisable() {
		super.onDisable();
	}
	
	public FaruHubPlayer getPlayer(final UUID uuid) {
		if(!this.playerHubMap.containsKey(uuid)) new FaruHubPlayer(uuid);
		return this.playerHubMap.get(uuid);
	}
	
	public static FaruHub getInstance() {
		return instance;
	}
}
