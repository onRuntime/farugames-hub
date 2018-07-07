package net.farugames.hub;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.farugames.api.spigot.player.FaruPlayer;
import net.farugames.api.tools.board.TeamsTagsManager;
import net.farugames.hub.boards.ScoreboardManager;
import net.farugames.hub.boards.ScoreboardSign;

public class FaruHubPlayer {

	public Player player;
	public FaruPlayer faruPlayer;
	
	public ScoreboardSign scoreboard;
	
	public FaruHubPlayer(final UUID uuid) {
		this.player = Bukkit.getPlayer(uuid);
		this.faruPlayer = FaruPlayer.getPlayer(uuid);
		
		this.scoreboard = new ScoreboardSign(this.player, "�7Loading...");
		this.sendTablist();
		ScoreboardManager.loadScoreboard(FaruPlayer.getPlayer(uuid));
	}
	
	public void sendTablist() {
		TeamsTagsManager.setNameTag(player,
				faruPlayer.getRank().getOrder(),
				faruPlayer.getRank().getColor() + faruPlayer.getRank().getPrefix() + " ");
	}
	
	/*
	public void sendScoreboard() {
		this.scoreboard.create();
		this.scoreboard.setObjectiveName("�c�lF�e�la�a�lr�b�lu�d�lGames");
		
		this.scoreboard.setLine(0, "�1");
		this.scoreboard.setLine(1, "�7Pseudo: " + "�a" + faruPlayer.getPlayer().getName());
		this.scoreboard.setLine(2, "�7Rank: " + faruPlayer.getRank().getColor() + faruPlayer.getRank().getPrefix());
		this.scoreboard.setLine(3, "�2");
		this.scoreboard.setLine(4, "�7Coins: �e" + faruPlayer.getCoins(Currency.COINS));
		this.scoreboard.setLine(5, "�7Credits: �d" + faruPlayer.getCoins(Currency.CREDITS));
		this.scoreboard.setLine(6, "�3");
		this.scoreboard.setLine(7, "�7Hub: �3" + Bukkit.getServerName());
		this.scoreboard.setLine(8, "�7Players: �b" + "int");
		this.scoreboard.setLine(9, "�4");
		this.scoreboard.setLine(10, "�eplay.farugames.net");
		this.scoreboard.setLine(11, "�8�m+---------------+");
	}
	*/
}