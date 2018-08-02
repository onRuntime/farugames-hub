package net.farugames.hub.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MenuRunnable extends BukkitRunnable {
	
	@Override
	public void run() {
		for(Player onlinePlayers : Bukkit.getOnlinePlayers()) {
			/*
			if(onlinePlayers.getInventory().getItem(4).equals(Item.MENU_COSMETICS_OPEN.getItem())) {
				onlinePlayers.getInventory().setItem(4, Item.MENU_COSMETICS_CLOSE.getItem());
			} else {
				onlinePlayers.getInventory().setItem(4, Item.MENU_COSMETICS_OPEN.getItem());
			}*/
		}
		
	}

}
