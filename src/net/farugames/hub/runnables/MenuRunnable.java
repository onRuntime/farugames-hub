package net.farugames.hub.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MenuRunnable extends BukkitRunnable {
	
	@Override
	public void run() {
		for(Player onlinePlayers : Bukkit.getOnlinePlayers()) {
<<<<<<< HEAD
			if(onlinePlayers.getInventory().getItem(4).equals(Item.MENU_COSMETICS_OPEN.getItemBuilder().build())) {
				onlinePlayers.getInventory().setItem(4, Item.MENU_COSMETICS_CLOSE.getItemBuilder().build());
=======
			/*
			if(onlinePlayers.getInventory().getItem(4).equals(Item.MENU_COSMETICS_OPEN.getItem())) {
				onlinePlayers.getInventory().setItem(4, Item.MENU_COSMETICS_CLOSE.getItem());
>>>>>>> branch 'master' of https://gitlab.com/FaruGamesDev/FaruGamesHub.git
			} else {
<<<<<<< HEAD
				onlinePlayers.getInventory().setItem(4, Item.MENU_COSMETICS_OPEN.getItemBuilder().build());
			}
=======
				onlinePlayers.getInventory().setItem(4, Item.MENU_COSMETICS_OPEN.getItem());
			}*/
>>>>>>> branch 'master' of https://gitlab.com/FaruGamesDev/FaruGamesHub.git
		}
		
	}

}
