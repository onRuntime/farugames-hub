package net.farugames.hub;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.farugames.api.spigot.listeners.Listeners;
import net.farugames.api.spigot.player.FaruPlayer;
import net.farugames.api.spigot.player.currency.Currency;
import net.farugames.api.tools.board.ScoreboardSign;
import net.farugames.data.database.servers.IServer;
import net.farugames.hub.boards.ScoreboardManager;
import net.farugames.hub.listeners.ListenersManager;

public class Main extends JavaPlugin {

	private static Main instance;
	private Map<UUID, FaruHubPlayer> playerHubMap = new HashMap<UUID, FaruHubPlayer>();

	public Main() {
		instance = this;
	}

	@Override
	public void onLoad() {
		super.onLoad();
	}
	
	@SuppressWarnings("deprecation")
	public void onEnable() {
		net.farugames.api.spigot.Main.getInstance().disableEvent(Listeners.FOOD_LEVEL_CHANGE_LISTENER.getListener());
		net.farugames.api.spigot.Main.getInstance().disableEvent(Listeners.WEATHER_CHANGE_LISTENER.getListener());
		new ListenersManager(this).registerListeners();
		
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(instance, new Runnable() {
			public void run() {
				int gonlineplayers = IServer.getGlobalOnlinePlayers();
				for (Entry<Player, ScoreboardSign> boards : ScoreboardManager.boards.entrySet()) {
					FaruPlayer faruPlayer = FaruPlayer.getPlayer(boards.getKey().getUniqueId());
					boards.getValue().setLine(4, "§7Coins: §e" + ScoreboardManager.format(faruPlayer.getCoins(Currency.COINS)));
					boards.getValue().setLine(5, "§7Credits: §d" + ScoreboardManager.format(faruPlayer.getCoins(Currency.CREDITS)));
					boards.getValue().setLine(6, "§7Level: §6" + faruPlayer.getExperience() + " §f(" + "0" + "§f%)");;
					boards.getValue().setLine(9, "§7Players: " + "§b" + gonlineplayers);
				}
				
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.setLevel(gonlineplayers);
				}
			}
		}, 0, 5 * 20);
		
		super.onEnable();
	}
	
	public void onDisable() {
		super.onDisable();
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public FaruHubPlayer getPlayer(final UUID uuid) {
		if(!this.playerHubMap.containsKey(uuid)) new FaruHubPlayer(uuid);
		return this.playerHubMap.get(uuid);
	}
	
}
