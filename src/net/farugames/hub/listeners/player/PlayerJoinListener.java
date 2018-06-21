package net.farugames.hub.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.faru.api.player.FaruPlayer;
import net.faru.api.player.languages.Lang;
import net.faru.api.player.rank.Rank;
import net.faru.api.tools.player.UUIDManager;
import net.farugames.hub.FaruHub;

public class PlayerJoinListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		FaruPlayer faruPlayer = FaruPlayer.getPlayer(UUIDManager.getUUID(event.getPlayer().getName()));
		FaruHub.getInstance().getPlayer(UUIDManager.getUUID(faruPlayer.getPlayer().getName()));
		event.setJoinMessage(null);
		
		if(faruPlayer.getRank().getPower() >= Rank.ADMINISTRATOR.getPower()) faruPlayer.getPlayer().setOp(true);
		if(faruPlayer.getRank().getPower() >= Rank.YOUTUBER.getPower()) 
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(Lang.JOIN_MESSAGE.in(faruPlayer.getLanguage()).replaceAll("%player_rank_prefix%", faruPlayer.getRank().getColor() +
					faruPlayer.getRank().getPrefix()).replaceAll("%player%", faruPlayer.getPlayer().getName()));
		}
	}
}
