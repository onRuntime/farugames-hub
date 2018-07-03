package net.farugames.hub.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.farugames.api.spigot.player.FaruPlayer;
import net.farugames.api.spigot.player.languages.Lang;
import net.farugames.api.spigot.player.rank.Rank;
import net.farugames.api.tools.locations.Locations;
import net.farugames.api.tools.player.UUIDManager;
import net.farugames.hub.Main;

public class PlayerJoinListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		FaruPlayer faruPlayer = FaruPlayer.getPlayer(UUIDManager.getUUID(event.getPlayer().getName()));
		Main.getInstance().getPlayer(UUIDManager.getUUID(faruPlayer.getPlayer().getName()));
		event.setJoinMessage(null);
		
		event.getPlayer().teleport(Locations.getLocation(Locations.HUB, Bukkit.getWorld("world")));
		event.getPlayer().setGameMode(GameMode.ADVENTURE);
		event.getPlayer().setMaxHealth(2.0);
		event.getPlayer().setHealth(2.0);
		event.getPlayer().setFoodLevel(20);
		event.getPlayer().setExp(0);
		if(faruPlayer.getRank().getPower() >= Rank.ADMINISTRATOR.getPower()) faruPlayer.getPlayer().setOp(true);
		if(faruPlayer.getRank().getPower() >= Rank.YOUTUBER.getPower()) {
			event.getPlayer().setAllowFlight(true);
			for(Player players : Bukkit.getOnlinePlayers()) {
				players.sendMessage(Lang.JOIN_MESSAGE.in(faruPlayer.getLanguage()).replaceAll("%player_rank_prefix%", faruPlayer.getRank().getColor() +
						faruPlayer.getRank().getPrefix()).replaceAll("%player%", faruPlayer.getPlayer().getName()));
			}
		}
	}
}
