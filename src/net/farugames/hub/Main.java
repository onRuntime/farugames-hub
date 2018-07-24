package net.farugames.hub;

import net.farugames.api.spigot.SpigotFaruAPI;
import net.farugames.api.spigot.listeners.Listeners;
import net.farugames.api.spigot.player.FaruPlayer;
import net.farugames.api.spigot.player.currency.Currency;
import net.farugames.api.tools.board.ScoreboardSign;
import net.farugames.hub.boards.ScoreboardManager;
import net.farugames.hub.listeners.EventsListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

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
        SpigotFaruAPI.getInstance().disableEvent(Listeners.FOOD_LEVEL_CHANGE_LISTENER.getListener());
        SpigotFaruAPI.getInstance().disableEvent(Listeners.WEATHER_CHANGE_LISTENER.getListener());
        Bukkit.getPluginManager().registerEvents(new EventsListener(), instance);

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(instance, new Runnable() {
            public void run() {
                int gonlineplayers = Integer.valueOf(getGlobalOnlinePlayers());
                for (Entry<Player, ScoreboardSign> boards : ScoreboardManager.boards.entrySet()) {
                    FaruPlayer faruPlayer = FaruPlayer.getPlayer(boards.getKey().getUniqueId());
                    boards.getValue().setLine(4, "§7Coins: §e" + ScoreboardManager.format(faruPlayer.getCoins(Currency.COINS)));
                    boards.getValue().setLine(5, "§7Credits: §d" + ScoreboardManager.format(faruPlayer.getCoins(Currency.CREDITS)));
                    boards.getValue().setLine(6, "§7Level: §6" + faruPlayer.getExperience() + " §f(" + "0" + "§f%)");
                    ;
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

    public static String getGlobalOnlinePlayers() {
        try {
            URLConnection con = new URL("https://minecraft-api.com/api/ping/playeronline.php?ip=play.farugames.net&port=25565").openConnection();
            InputStream in = con.getInputStream();

            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            String body = new String(baos.toByteArray(), encoding);
            return body;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "0";
    }

    public static Main getInstance() {
        return instance;
    }

    public FaruHubPlayer getPlayer(final UUID uuid) {
        if (!this.playerHubMap.containsKey(uuid)) new FaruHubPlayer(uuid);
        return this.playerHubMap.get(uuid);
    }

}
