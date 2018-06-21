package net.farugames.hub;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.faru.api.player.FaruPlayer;
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
		this.sendScoreboard();
		this.sendTablist();
	}
	
	public void sendScoreboard() {
		this.scoreboardSign.setObjectiveName("");
		this.scoreboardSign.create();
		this.scoreboardSign.setLine(10, "");
		this.scoreboardSign.setLine(9, "");
		this.scoreboardSign.setLine(8, "");
		this.scoreboardSign.setLine(7, "");
		this.scoreboardSign.setLine(6, "");
		this.scoreboardSign.setLine(5, "");
		this.scoreboardSign.setLine(4, "");
		this.scoreboardSign.setLine(3, "");
		this.scoreboardSign.setLine(2, "");
		this.scoreboardSign.setLine(1, "");
		this.scoreboardSign.setLine(0, "");
	}
	
	public void sendTablist() {
		TeamsTagsManager.setNameTag(player,
				"§" + faruPlayer.getRank().getOrder() + faruPlayer.getRank().getChatColor()
						+ faruPlayer.getRank().getName(),
						faruPlayer.getRank().getChatColor() + faruPlayer.getRank().getPrefix());
	}
}
