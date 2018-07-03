package net.farugames.hub;

public class PluginMethods {
	
	private static String PREFIX = "§c§lFaruGames§r";
	private static String PREFIX_SCOREBOARD = "§e✜ " + PREFIX + " §e✜§r";
	private static String PREFIX_CHAT = "§5SodiumMC §8» §r";
	
	public static String getPrefix() {
		return PREFIX;
	}
	
	public static String getPrefixScoreboard() {
		return PREFIX_SCOREBOARD;
	}
	
	public static String getPrefixChat() {
		return PREFIX_CHAT;
	}
}
