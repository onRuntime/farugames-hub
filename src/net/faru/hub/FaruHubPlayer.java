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
	
	public ScoreboardSign scoreboardSign;
	
	public FaruHubPlayer(final UUID uuid) {
		this.player = Bukkit.getPlayer(uuid);
		this.faruPlayer = FaruPlayer.getPlayer(uuid);
		
		this.scoreboardSign = new ScoreboardSign(player, player.getName());
		this.sendTablist();
		//this.sendScoreboard();
	}
	
	public void sendTablist() {
		TeamsTagsManager.setNameTag(player,
				"§" + faruPlayer.getRank().getOrder() + faruPlayer.getRank().getColor()
						+ faruPlayer.getRank().getName(),
						faruPlayer.getRank().getColor() + faruPlayer.getRank().getPrefix() + " ");
	}
	
	public void sendScoreboard() {
		this.scoreboardSign.setObjectiveName("");
		this.scoreboardSign.create();
		this.scoreboardSign.setLine(10, "§a");
		this.scoreboardSign.setLine(9, "§b");
		this.scoreboardSign.setLine(8, "§c");
		this.scoreboardSign.setLine(7, "§d");
		this.scoreboardSign.setLine(6, "§e");
		this.scoreboardSign.setLine(5, "§f");
		this.scoreboardSign.setLine(4, "§1");
		this.scoreboardSign.setLine(3, "§2");
		this.scoreboardSign.setLine(2, "§3");
		this.scoreboardSign.setLine(1, "§4");
		this.scoreboardSign.setLine(0, "§5");
	}
}
