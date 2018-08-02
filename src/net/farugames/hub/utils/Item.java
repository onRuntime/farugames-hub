package net.farugames.hub.utils;

import net.farugames.api.core.rank.Rank;
import net.farugames.api.tools.builders.items.ItemBuilder;
import net.farugames.api.tools.builders.items.ItemBuilder.ItemType;

public enum Item {

	MENU_GAME("hub", null, 0, new ItemBuilder(ItemType.CUSTOM_HEAD, "http://textures.minecraft.net/texture/a6bc364673c34dc34a9578292f30aa9f2d4b9468115b86129e76786cff8299d").withName("§CGame Menu")),
	MENU_PROFILE("hub", null, 1, null),
	MENU_COSMETICS_OPEN("hub", null, 4, new ItemBuilder(ItemType.CUSTOM_HEAD, "http://textures.minecraft.net/texture/b4cc193f6edd88a772b93eea3abd7520ce945d73864a62fec1ff5c2f94e25").withName("§bCosmectics")),
	MENU_COSMETICS_CLOSE("hub", null, 4, new ItemBuilder(ItemType.CUSTOM_HEAD, "http://textures.minecraft.net/texture/b4fcaaea16eb7271067d8c8b3274de0ce762fa14f886a829dacf0457630eab").withName("§bCosmectics"));
	
	private String type;
	private Rank accessRank;
	private Integer slot;
	private ItemBuilder item;
	
	private Item(String type, Rank accessRank, Integer slot, ItemBuilder item) {
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
	
	public ItemBuilder getItemBuilder() {
		return this.item;
	}
}