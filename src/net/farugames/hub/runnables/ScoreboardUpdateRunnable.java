package net.farugames.hub.runnables;

import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.farugames.api.core.currency.Currency;
import net.farugames.api.spigot.FaruPlayer;
import net.farugames.api.tools.board.ScoreboardSign;
import net.farugames.hub.boards.ScoreboardManager;

public class ScoreboardUpdateRunnable extends BukkitRunnable {

	public static int timer = 0;

	public ScoreboardUpdateRunnable() {
	}

	@Override
	public void run() {
		for (Entry<Player, ScoreboardSign> boards : ScoreboardManager.boards.entrySet()) {
			// int gonlineplayers = IServer.getGlobalOnlinePlayers();
			FaruPlayer faruPlayer = FaruPlayer.getPlayer(boards.getKey().getUniqueId());
			switch (timer) {
			case 4:
				boards.getValue().setLine(4,
						"§7Coins: §e" + ScoreboardManager.format(faruPlayer.getCoins(Currency.COINS)));
				break;
			case 3:
				boards.getValue().setLine(5,
						"§7Credits: §d" + ScoreboardManager.format(faruPlayer.getCoins(Currency.CREDITS)));
				break;
			case 2:
				boards.getValue().setLine(6, "§7Level: §6" + faruPlayer.getExperience() + " §f(" + "0" + "§f%)");
				break;
			case 1:
				boards.getValue().setLine(9, "§7Players: " + "§b" + "0");
				break;
			}
			// faruPlayer.getPlayer().setLevel(gonlineplayers);
		}
		if (timer <= 0) { timer = 4; }
		timer = timer - 1;
	}

}