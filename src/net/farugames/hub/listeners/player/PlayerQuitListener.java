package net.farugames.hub.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.farugames.data.spigot.Main;

public class PlayerQuitListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent event) {
		if(Main.iFaruPlayer.containsKey(event.getPlayer().getUniqueId())) {
			Main.iFaruPlayer.remove(event.getPlayer().getUniqueId()); }
	}
}
