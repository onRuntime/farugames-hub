package net.farugames.hub.listeners;

import org.bukkit.event.Listener;

import net.faru.api.spigot.listeners.player.PlayerJoinListener;
import net.farugames.hub.listeners.player.PlayerQuitListener;

public enum Listeners {
	
	PLAYER_JOIN_LISTENER(new PlayerJoinListener()),
	PLAYER_QUIT_LISTENER(new PlayerQuitListener());
	
	private Listener listener;
	
	Listeners(Listener listener) {
		this.listener = listener;
	}
	
	public Listener getListener() {
		return this.listener;
	}
}
