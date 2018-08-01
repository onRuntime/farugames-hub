package net.farugames.hub.utils;

import org.bukkit.inventory.ItemStack;

import net.farugames.api.core.rank.Rank;
import net.farugames.api.tools.builders.items.HeadBuilder;

public enum Item {

	MENU_GAME("hub", null, 0, new HeadBuilder().withCustom("http://textures.minecraft.net/texture/a6bc364673c34dc34a9578292f30aa9f2d4b9468115b86129e76786cff8299d").withName("§cGame Menu").build()),
	MENU_PROFILE("hub", null, 1, null),
	MENU_COSMETICS_OPEN("hub", null, 4, new HeadBuilder().withCustom("http://textures.minecraft.net/texture/b4cc193f6edd88a772b93eea3abd7520ce945d73864a62fec1ff5c2f94e25").withName("§bCosmetics").build()),
	MENU_COSMETICS_CLOSE("hub", null, 4, new HeadBuilder().withCustom("http://textures.minecraft.net/texture/b4fcaaea16eb7271067d8c8b3274de0ce762fa14f886a829dacf0457630eab").withName("§bCosmetics").build());
	
	private String type;
	private Rank accessRank;
	private Integer slot;
	private ItemStack item;
	
	private Item(String type, Rank accessRank, Integer slot, ItemStack item) {
		this.type = type;
		this.accessRank = accessRank;
		this.slot = slot;
		this.item = item;
	}
	
	public String getType() {
		return this.type;
	}
	
	public Rank getAccessRank() {
		return this.accessRank != null ? this.accessRank : Rank.PLAYER;
	}
	
	public Integer getSlot() {
		return this.slot;
	}
	
	public ItemStack getItem() {
		return this.item;
	}
}