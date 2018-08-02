package net.farugames.hub.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.farugames.hub.utils.Item;

public class MenuRunnable extends BukkitRunnable {
	
	@Override
	public void run() {
		for(Player onlinePlayers : Bukkit.getOnlinePlayers()) {
			if(onlinePlayers.getInventory().getItem(4).equals(Item.MENU_COSMETICS_OPEN.getItemBuilder().build())) {
				onlinePlayers.getInventory().setItem(4, Item.MENU_COSMETICS_CLOSE.getItemBuilder().build());
			} else {
				onlinePlayers.getInventory().setItem(4, Item.MENU_COSMETICS_OPEN.getItemBuilder().build());
			}
		}
	}

}
