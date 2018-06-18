package net.faru.hub.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.faru.data.spigot.SpigotFaruData;

public class PlayerQuiListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent event) {
		if(SpigotFaruData.iFaruPlayer.containsKey(event.getPlayer().getUniqueId())) {
			SpigotFaruData.iFaruPlayer.remove(event.getPlayer().getUniqueId()); }
	}
}
