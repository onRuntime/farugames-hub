package net.farugames.hub.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.faru.api.player.FaruPlayer;
import net.faru.api.player.rank.Rank;
import net.faru.api.tools.player.UUIDManager;
import net.farugames.hub.FaruHub;
import net.farugames.hub.FaruHubPlayer;

public class PlayerJoinListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		FaruPlayer faruPlayer = FaruPlayer.getPlayer(UUIDManager.getUUID(player.getName()));
		FaruHubPlayer faruHubPlayer = FaruHub.getInstance().getPlayer(UUIDManager.getUUID(player.getName()));
		event.setJoinMessage(null);
		
		if(faruPlayer.getRank().getPower() >= 950) player.setOp(true);
		if(faruPlayer.getRank().getPower() >= Rank.YOUTUBER.getPower()) 
			Bukkit.broadcastMessage("");
	}
}
