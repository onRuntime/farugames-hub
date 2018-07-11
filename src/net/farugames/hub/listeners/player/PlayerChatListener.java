package net.farugames.hub.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.farugames.api.spigot.player.chat.Chat;

public class PlayerChatListener implements Listener {

	@EventHandler
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
		Chat.customMessage(e, "", "");
	}

}
