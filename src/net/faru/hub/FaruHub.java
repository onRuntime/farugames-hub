package net.faru.hub;

import org.bukkit.plugin.java.JavaPlugin;

import net.faru.data.mysql.MySQLManager;

public class FaruHub extends JavaPlugin {

	private static FaruHub instance;
	
	protected String sqlUrl;
	protected String sqlBase;
	protected String sqlUser;
	protected String sqlPass;
	
	public void onLoad() {
		instance = this;
		new MySQLManager(sqlBase, "3306", sqlUrl, sqlUser, sqlPass).connection();
		
		super.onLoad();
	}
	
	public void onEnable() {
		super.onEnable();
	}
	
	public void onDisable() {
		super.onDisable();
	}
	
	public static FaruHub getInstance() {
		return instance;
	}
}
