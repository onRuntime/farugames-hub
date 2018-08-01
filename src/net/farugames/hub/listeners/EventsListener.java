package net.farugames.hub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.farugames.api.core.lang.I18n;
import net.farugames.api.core.rank.Rank;
import net.farugames.api.spigot.FaruPlayer;
import net.farugames.api.spigot.SpigotFaruGamesAPI;
import net.farugames.api.spigot.managers.ChatManager;
import net.farugames.api.tools.locations.Locations;
import net.farugames.api.tools.player.UUIDFetcher;
import net.farugames.hub.FaruGamesHub;
import net.farugames.hub.boards.ScoreboardManager;
import net.farugames.hub.utils.Item;

public class EventsListener implements Listener {

	// player
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		FaruPlayer faruPlayer = FaruPlayer.getPlayer(UUIDFetcher.getUUID(player.getName()));
		FaruGamesHub.getInstance().getPlayer(UUIDFetcher.getUUID(faruPlayer.getPlayer().getName()));
		e.setJoinMessage(null);
		
		for(Item item : Item.values()) { 
			if(!item.getType().equals("hub")) continue;
			if(item.getAccessRank().getPower() > faruPlayer.getRank().getPower()) continue;
			if(item.getItem() == null) { player.getInventory().setItem(item.getSlot(), CraftItemStack.asBukkitCopy(faruPlayer.getItemStackHead())); continue; }
			player.getInventory().setItem(item.getSlot(), item.getItem());
		}
		
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 2F, 1F);
		player.teleport(Locations.getLocation(Locations.HUB, Bukkit.getWorld("world")));
		player.setGameMode(GameMode.ADVENTURE);
		player.setMaxHealth(2.0);
		player.setHealth(2.0);
		player.setFoodLevel(20);
		player.setExp(0);
		
		if (faruPlayer.getRank().getPower() >= Rank.ADMIN.getPower())
			faruPlayer.getPlayer().setOp(true);
		if (faruPlayer.getRank().getPower() >= Rank.YOUTUBER.getPower()) {
			player.setAllowFlight(true);
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.sendMessage(I18n.tl(faruPlayer.getLanguage(), "server.message.join", faruPlayer.getRank().getColor() + faruPlayer.getRank().getPrefix(), faruPlayer.getPlayer().getName()));
				return;
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent event) {
		SpigotFaruGamesAPI.iFaruPlayer.remove(event.getPlayer().getUniqueId());
		Player p = event.getPlayer();

		if (ScoreboardManager.boards.containsKey(p)) {
			ScoreboardManager.boards.get(p).destroy();
		}
	}

	@EventHandler
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
		ChatManager.customMessage(e, "", "");
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
