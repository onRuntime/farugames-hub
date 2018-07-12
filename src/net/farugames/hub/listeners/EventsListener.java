package net.farugames.hub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.farugames.api.spigot.player.FaruPlayer;
import net.farugames.api.spigot.player.chat.Chat;
import net.farugames.api.spigot.player.languages.Lang;
import net.farugames.api.spigot.player.rank.Rank;
import net.farugames.api.tools.locations.Locations;
import net.farugames.api.tools.player.UUIDManager;
import net.farugames.hub.Main;

public class EventsListener implements Listener {

	// player
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		FaruPlayer faruPlayer = FaruPlayer.getPlayer(UUIDManager.getUUID(p.getName()));
		Main.getInstance().getPlayer(UUIDManager.getUUID(faruPlayer.getPlayer().getName()));
		e.setJoinMessage(null);

		p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 2F, 1F);
		p.teleport(Locations.getLocation(Locations.HUB, Bukkit.getWorld("world")));
		p.setGameMode(GameMode.ADVENTURE);
		p.setMaxHealth(2.0);
		p.setHealth(2.0);
		p.setFoodLevel(20);
		p.setExp(0);
		if (faruPlayer.getRank().getPower() >= Rank.ADMINISTRATOR.getPower())
			faruPlayer.getPlayer().setOp(true);
		if (faruPlayer.getRank().getPower() >= Rank.YOUTUBER.getPower()) {
			p.setAllowFlight(true);
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.sendMessage(Lang.JOIN_MESSAGE.in(faruPlayer.getLanguage())
						.replaceAll("%player_rank_prefix%",
								faruPlayer.getRank().getColor() + faruPlayer.getRank().getPrefix())
						.replaceAll("%player%", faruPlayer.getPlayer().getName()));
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent event) {
		net.farugames.data.spigot.Main.iFaruPlayer.remove(event.getPlayer().getUniqueId());
		Player p = event.getPlayer();

		if (ScoreboardManager.boards.containsKey(p)) {
			ScoreboardManager.boards.get(p).destroy();
		}
	}

	@EventHandler
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
		Chat.customMessage(e, "", "");
	}

}
