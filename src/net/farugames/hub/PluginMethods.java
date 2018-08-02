package net.farugames.hub;

public class PluginMethods {
	
	private static String PREFIX = "§cFaru§eG§aa§bm§de§9s§r";
	private static String PREFIX_SCOREBOARD = "§f▼ " + PREFIX + " §f▼";
	private static String PREFIX_CHAT = PREFIX + " §8» §r";
	
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