package net.farugames.hub;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import net.farugames.api.spigot.listeners.Listeners;
import net.farugames.hub.listeners.ListenersManager;

public class Main extends JavaPlugin {

	private static Main instance;
	private Map<UUID, FaruHubPlayer> playerHubMap = new HashMap<UUID, FaruHubPlayer>();

	public Main() {
		instance = this;
	}

	@Override
	public void onLoad() {
		super.onLoad();
	}
	
	public void onEnable() {
		net.farugames.api.spigot.Main.getInstance().disableEvent(Listeners.FOOD_LEVEL_CHANGE_LISTENER.getListener());
		net.farugames.api.spigot.Main.getInstance().disableEvent(Listeners.WEATHER_CHANGE_LISTENER.getListener());
		new ListenersManager(this).registerListeners();
		
		super.onEnable();
	}
	
	public void onDisable() {
		super.onDisable();
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public FaruHubPlayer getPlayer(final UUID uuid) {
		if(!this.playerHubMap.containsKey(uuid)) new FaruHubPlayer(uuid);
		return this.playerHubMap.get(uuid);
	}
	
}
