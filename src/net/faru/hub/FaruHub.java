package net.faru.hub;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import net.faru.hub.managers.ListenerManager;

public class FaruHub extends JavaPlugin {

	private Map<UUID, FaruHubPlayer> playerHubMap = new HashMap<UUID, FaruHubPlayer>();
	private static FaruHub instance;
	
	public void onLoad() {
		instance = this;
		
		super.onLoad();
	}
	
	public void onEnable() {
		new ListenerManager().register();
		
		super.onEnable();
	}
	
	public void onDisable() {
		super.onDisable();
	}
	
	public FaruHubPlayer getPlayer(final UUID uuid) {
		return this.playerHubMap.containsKey(uuid) ? this.playerHubMap.get(uuid) : null;
	}
	
	public static FaruHub getInstance() {
		return instance;
	}
}
