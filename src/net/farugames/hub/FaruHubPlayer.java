package net.farugames.hub;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.farugames.api.spigot.FaruPlayer;
import net.farugames.api.tools.board.ScoreboardSign;
import net.farugames.api.tools.board.TeamsTagsManager;
import net.farugames.hub.boards.ScoreboardManager;

public class FaruHubPlayer {

	public Player player;
	public FaruPlayer faruPlayer;
	
	public ScoreboardSign scoreboard;
	public ParkourPlayerState parkourPlayerState;
	
	public FaruHubPlayer(final UUID uuid) {
		this.player = Bukkit.getPlayer(uuid);
		this.faruPlayer = FaruPlayer.getPlayer(uuid);
		
		this.scoreboard = new ScoreboardSign(this.player, "§7Loading...");
		this.sendTablist();
		ScoreboardManager.loadScoreboard(FaruPlayer.getPlayer(uuid));
		ParkourPlayerState.setStatus(ParkourPlayerState.LOBBY);
	}
	
	public void sendTablist() {
		TeamsTagsManager.setNameTag(player,
				faruPlayer.getRank().getOrder(),
				faruPlayer.getRank().getColor() + faruPlayer.getRank().getPrefix());
	}
}