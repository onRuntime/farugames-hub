package net.faru.hub.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.faru.api.spigot.player.FaruPlayer;
import net.faru.api.spigot.player.languages.Lang;
import net.faru.api.spigot.player.rank.Rank;
import net.faru.api.tools.locations.Locations;
import net.faru.api.tools.player.PlayerUtils;
import net.faru.api.tools.player.UUIDManager;
import net.faru.hub.FaruHub;

public class PlayerJoinListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		FaruPlayer faruPlayer = FaruPlayer.getPlayer(UUIDManager.getUUID(event.getPlayer().getName()));
		FaruHub.getInstance().getPlayer(UUIDManager.getUUID(faruPlayer.getPlayer().getName()));
		event.setJoinMessage(null);
		
		PlayerUtils.teleport(faruPlayer.getPlayer(), Locations.getLocation(Locations.HUB, Bukkit.getWorld("world")));
		PlayerUtils.setGamemode(faruPlayer.getPlayer(), GameMode.ADVENTURE);
		faruPlayer.getPlayer().setMaxHealth(2.0);
		faruPlayer.getPlayer().setHealth(2.0);
		faruPlayer.getPlayer().setFoodLevel(20);
		faruPlayer.getPlayer().setExp(0);
		if(faruPlayer.getRank().getPower() >= Rank.ADMINISTRATOR.getPower()) faruPlayer.getPlayer().setOp(true);
		if(faruPlayer.getRank().getPower() >= Rank.YOUTUBER.getPower()) {
			faruPlayer.getPlayer().setAllowFlight(true);
			for(Player players : Bukkit.getOnlinePlayers()) {
				players.sendMessage(Lang.JOIN_MESSAGE.in(faruPlayer.getLanguage()).replaceAll("%player_rank_prefix%", faruPlayer.getRank().getColor() +
						faruPlayer.getRank().getPrefix()).replaceAll("%player%", faruPlayer.getPlayer().getName()));
			}
		}
	}
}
