package net.faru.hub;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.faru.api.spigot.player.FaruPlayer;
import net.faru.api.tools.board.ScoreboardSign;
import net.faru.api.tools.board.TeamsTagsManager;

public class FaruHubPlayer {

	public Player player;
	public FaruPlayer faruPlayer;
	
	public ScoreboardSign scoreboard;
	
	public FaruHubPlayer(final UUID uuid) {
		this.player = Bukkit.getPlayer(uuid);
		this.faruPlayer = FaruPlayer.getPlayer(uuid);
		
		this.scoreboard = new ScoreboardSign(this.player, "§7Loading...");
		this.sendTablist();
		this.sendScoreboard();
	}
	
	public void sendTablist() {
		TeamsTagsManager.setNameTag(player,
				faruPlayer.getRank().getOrder(),
				faruPlayer.getRank().getColor() + faruPlayer.getRank().getPrefix() + " ");
	}
	
	public void sendScoreboard() {
		this.scoreboard.create();
		this.scoreboard.setObjectiveName("§c§lF§e§la§a§lr§b§lu§d§lGames");
		
		this.scoreboard.setLine(10, "§a");
		this.scoreboard.setLine(9, "§b");
		this.scoreboard.setLine(8, "§c");
		this.scoreboard.setLine(7, "§d");
		this.scoreboard.setLine(6, "§e");
		this.scoreboard.setLine(5, "§f");
		this.scoreboard.setLine(4, "§1");
		this.scoreboard.setLine(3, "§2");
		this.scoreboard.setLine(2, "§3");
		this.scoreboard.setLine(1, "§4");
		this.scoreboard.setLine(0, "§5");
	}
}
