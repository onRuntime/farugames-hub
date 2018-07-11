package net.farugames.hub.boards;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.farugames.api.spigot.player.FaruPlayer;
import net.farugames.api.spigot.player.currency.Currency;
import net.farugames.api.tools.board.ScoreboardSign;
import net.farugames.data.database.servers.IServer;
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
		sc.setLine(4, "§7Coins: §e" + format(faruPlayer.getCoins(Currency.COINS)));
		sc.setLine(5, "§7Credits: §d" + format(faruPlayer.getCoins(Currency.CREDITS)));
		sc.setLine(6, "§7Level: §6" + faruPlayer.getExperience() + " §f(" + "0" + "§f%)");
		sc.setLine(7, "§3");
		sc.setLine(8, "§7Hub: §3" + Bukkit.getServerName());
		sc.setLine(9, "§7Players: §b" + IServer.getGlobalOnlinePlayers());
		sc.setLine(10, "§4");
		sc.setLine(11, "§eplay.farugames.net");
		sc.setLine(12, "§8§m+---------------+");

		boards.put(fp, sc);
	}

	private static final NavigableMap<Long, String> suffixes = new TreeMap<>();
	static {
		suffixes.put(1_000L, "k");
		suffixes.put(1_000_000L, "M");
		suffixes.put(1_000_000_000L, "G");
		suffixes.put(1_000_000_000_000L, "T");
		suffixes.put(1_000_000_000_000_000L, "P");
		suffixes.put(1_000_000_000_000_000_000L, "E");
	}

	public static String format(long value) {
		// Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
		if (value == Long.MIN_VALUE)
			return format(Long.MIN_VALUE + 1);
		if (value < 0)
			return "-" + format(-value);
		if (value < 1000)
			return Long.toString(value); // deal with easy case

		Entry<Long, String> e = suffixes.floorEntry(value);
		Long divideBy = e.getKey();
		String suffix = e.getValue();

		long truncated = value / (divideBy / 10); // the number part of the
													// output times 10
		boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
		return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		if (boards.containsKey(p)) {
			boards.get(p).destroy();
		}

	}

}
