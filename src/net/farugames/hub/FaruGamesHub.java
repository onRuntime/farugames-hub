package net.farugames.hub;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.farugames.api.spigot.SpigotFaruGamesAPI;
import net.farugames.api.spigot.listeners.Listeners;
import net.farugames.hub.listeners.EventsListener;
import net.farugames.hub.runnables.MenuRunnable;
import net.farugames.hub.runnables.ScoreboardAnimationsRunnable;
import net.farugames.hub.runnables.ScoreboardUpdateRunnable;

public class FaruGamesHub extends JavaPlugin {

	private static FaruGamesHub instance;
	private Map<UUID, FaruHubPlayer> playerHubMap = new HashMap<UUID, FaruHubPlayer>();

	public FaruGamesHub() {
		instance = this;
	}

	@Override
	public void onLoad() {

		super.onLoad();
	}

	public void onEnable() {
		SpigotFaruGamesAPI.getInstance().disableEvent(Listeners.FOOD_LEVEL_CHANGE_LISTENER.getListener());
		SpigotFaruGamesAPI.getInstance().disableEvent(Listeners.WEATHER_CHANGE_LISTENER.getListener());
		Bukkit.getPluginManager().registerEvents(new EventsListener(), instance);

		new MenuRunnable().runTaskTimer(instance, 0L, 40L);
		new ScoreboardAnimationsRunnable().runTaskTimer(instance, 0L, 2L);
		new ScoreboardUpdateRunnable().runTaskTimer(instance, 0L, 10L);

		super.onEnable();
	}

	public void onDisable() {
		super.onDisable();
	}

	public static FaruGamesHub getInstance() {
		return instance;
	}

	public FaruHubPlayer getPlayer(final UUID uuid) {
		if (!this.playerHubMap.containsKey(uuid))
			new FaruHubPlayer(uuid);
		return this.playerHubMap.get(uuid);
	}

}
