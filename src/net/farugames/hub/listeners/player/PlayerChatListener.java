package net.farugames.hub.listeners.player;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.farugames.api.spigot.player.FaruPlayer;
import net.farugames.api.spigot.player.data.DataType;
import net.farugames.api.spigot.player.rank.Rank;
import net.farugames.hub.utils.TitleManager;

public class PlayerChatListener implements Listener {

	@EventHandler
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
		Player sender = e.getPlayer();
		FaruPlayer faruSender = FaruPlayer.getPlayer(sender.getUniqueId());
		Player fs = faruSender.getPlayer();
		Rank r = faruSender.getRank();
		String msg = e.getMessage();
		
		e.setCancelled(true);
		
		if ((boolean) faruSender.getData(DataType.ALLOW_CHAT) == true) {
			for (Player op : e.getRecipients()) {
				FaruPlayer faruOp = FaruPlayer.getPlayer(op.getUniqueId());
				Player fOp = faruOp.getPlayer();
				if (msg.contains(fOp.getName())) {
					if ((boolean) faruOp.getData(DataType.ALLOW_CHAT_MENTIONS).equals(true)) {
						fOp.playSound(fOp.getLocation(), Sound.BLOCK_NOTE_PLING, 10, 1);
						TitleManager.sendActionBar(fOp, "§a" + fs.getName() + "§d vous a mentionné dans le chat !");
						msg = msg.replaceAll("@", "");
						msg = msg.replaceAll(fOp.getName(), "§e@" + fOp.getName() + r.getChatColor());
					}
				}
			}
			for (Player op : e.getRecipients()) {
				FaruPlayer faruOp = FaruPlayer.getPlayer(op.getUniqueId());
				if ((boolean) faruOp.getData(DataType.ALLOW_CHAT).equals(true)) {
					op.sendMessage(r.getColor() + r.getPrefix() + " " + fs.getName() + " §8»§r "
							+ r.getChatColor() + msg);
				}
			}
		} else {
			// errormsg
		}
	}

}
