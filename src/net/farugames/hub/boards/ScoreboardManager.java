package net.farugames.hub.boards;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.farugames.api.spigot.player.FaruPlayer;
import net.farugames.api.spigot.player.currency.Currency;
import net.farugames.hub.PluginMethods;

public class ScoreboardManager implements Listener {

	public static Map<Player, ScoreboardSign> boards = new HashMap<>();

	public static void loadScoreboard(FaruPlayer faruPlayer) {
		Player fp = faruPlayer.getPlayer();
		ScoreboardSign sc = new ScoreboardSign(fp, PluginMethods.getPrefixScoreboard());
		
		
		sc.create();
		
		sc.setLine(0, "§1");
		sc.setLine(1, "§7Pseudo: " + "§a" + fp.getName());
		sc.setLine(2, "§7Rank: " + faruPlayer.getRank().getColor() + faruPlayer.getRank().getPrefix());
		sc.setLine(3, "§2");
		sc.setLine(4, "§7Coins: §e" + faruPlayer.getCoins(Currency.COINS));
		sc.setLine(5, "§7Credits: §d" + faruPlayer.getCoins(Currency.CREDITS));
		sc.setLine(6, "§7Level: §6" + "0" + " §f(" + "0" + "§f%)");
		sc.setLine(7, "§3");
		sc.setLine(8, "§7Hub: §3" + Bukkit.getServerName());
		sc.setLine(9, "§7Players: §b" + "int");
		sc.setLine(10, "§4");
		sc.setLine(11, "§eplay.farugames.net");
		sc.setLine(12, "§8§m+---------------+");

		boards.put(fp, sc);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		if (boards.containsKey(p)) {
			boards.get(p).destroy();
		}

	}
	
	

}
