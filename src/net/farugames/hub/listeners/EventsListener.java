package net.farugames.hub.listeners;

import net.farugames.api.spigot.SpigotFaruAPI;
import net.farugames.api.spigot.player.FaruPlayer;
import net.farugames.api.spigot.player.chat.Chat;
import net.farugames.api.spigot.player.languages.Lang;
import net.farugames.api.spigot.player.rank.Rank;
import net.farugames.api.tools.locations.Locations;
import net.farugames.api.tools.player.UUIDManager;
import net.farugames.hub.Main;
import net.farugames.hub.boards.ScoreboardManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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
		SpigotFaruAPI.iFaruPlayer.remove(event.getPlayer().getUniqueId());
		Player p = event.getPlayer();

		if (ScoreboardManager.boards.containsKey(p)) {
			ScoreboardManager.boards.get(p).destroy();
		}
	}

	@EventHandler
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
		Chat.customMessage(e, "", "");
	}
	
	@EventHandler
	public void onPlayerParkour(PlayerMoveEvent event) {
		/*FaruHubPlayer fhp = Main.getInstance().getPlayer(event.getPlayer().getUniqueId());
		Player player = event.getPlayer();
		if (event.getPlayer().getLocation().getBlock().getType() == Material.IRON_PLATE) {
			switch (fhp.parkourPlayerState.getStatus()) {
			case LOBBY:
				fhp.parkourPlayerState.setStatus(ParkourPlayerState.PARKOUR);
				fhp.parkourPlayerState.setLoc(event.getFrom());
				player.sendMessage("parkour_start");
			case PARKOUR:
				player.sendMessage("parkour_finished");
			}
		} else if (event.getPlayer().getLocation().getBlock().getType() == Material.GOLD_PLATE) {
			fhp.parkourPlayerState.setLoc(event.getFrom());
		}
		if(fhp.parkourPlayerState.getStatus() == ParkourPlayerState.PARKOUR && circle(event.getTo(),8)) {
			player.teleport(fhp.parkourPlayerState.getLoc());
		}*/

	}

	public Boolean circle(Location loc, int radius) {
		int cx = loc.getBlockX();
		int cy = loc.getBlockY();
		int cz = loc.getBlockZ();

		for (int x = cx - radius; x <= cx + radius; x++) {
			for (int z = cz - radius; z <= cz + radius; z++) {
				for (int y = (cy - radius); y < (cy + radius); y++) {
					double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + ((cy - y) * (cy - y));

					if (dist < radius * radius) {
						Location l = new Location(loc.getWorld(), x, y + 2, z);
						if (l.getBlock().getType() == Material.HOPPER) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
