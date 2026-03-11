package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests unitaires pour la classe GildedRose.
 */
class GildedRoseTest {

	@Test
	@DisplayName("Item normal et avant la date de vente")
	void testNormalItemBeforeSellDate() {
		Item[] items = new Item[]{new Item("foo", 10, 20)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(9, app.items()[0].sellIn);
		assertEquals(19, app.items()[0].quality);
	}

	@Test
	@DisplayName("Item normal à la date de vente")
	void testNormalItemOnSellDate() {
		Item[] items = new Item[]{new Item("foo", 0, 20)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(-1, app.items()[0].sellIn);
		assertEquals(18, app.items()[0].quality);
	}

	@Test
	@DisplayName("Item normal après la date de vente")
	void testNormalItemAfterSellDate() {
		Item[] items = new Item[]{new Item("foo", -1, 20)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(-2, app.items()[0].sellIn);
		assertEquals(18, app.items()[0].quality);
	}

	@Test
	@DisplayName("La qualité d'un item normal ne peut pas être négative")
	void testNormalItemQualityCannotBeNegative() {
		Item[] items = new Item[]{new Item("foo", 10, 0)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(9, app.items()[0].sellIn);
		assertEquals(0, app.items()[0].quality);
	}

	@Test
	@DisplayName("La qualité d'un item normal ne peut pas être négative après la date de vente")
	void testNormalItemQualityCannotBeNegativeAfterSellDate() {
		Item[] items = new Item[]{new Item("foo", 0, 0)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(-1, app.items()[0].sellIn);
		assertEquals(0, app.items()[0].quality);
	}

	@Test
	@DisplayName("Aged Brie avant la date de vente")
	void testAgedBrieBeforeSellDate() {
		Item[] items = new Item[]{new Item("Aged Brie", 10, 20)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(9, app.items()[0].sellIn);
		assertEquals(21, app.items()[0].quality);
	}

	@Test
	@DisplayName("Aged Brie à la date de vente")
	void testAgedBrieOnSellDate() {
		Item[] items = new Item[]{new Item("Aged Brie", 0, 20)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(-1, app.items()[0].sellIn);
		assertEquals(22, app.items()[0].quality);
	}

	@Test
	@DisplayName("Aged Brie après la date de vente")
	void testAgedBrieQualityCannotExceed50() {
		Item[] items = new Item[]{new Item("Aged Brie", 10, 50)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(9, app.items()[0].sellIn);
		assertEquals(50, app.items()[0].quality);
	}

	@Test
	@DisplayName("Sulfuras ne change jamais")
	void testSulfurasNeverChanges() {
		Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 10, 80)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(10, app.items()[0].sellIn);
		assertEquals(80, app.items()[0].quality);
	}

	@Test
	@DisplayName("Sulfuras ne change jamais après la date de vente")
	void testSulfurasNeverChangesAfterSellDate() {
		Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", -1, 80)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(-1, app.items()[0].sellIn);
		assertEquals(80, app.items()[0].quality);
	}

	@Test
	@DisplayName("Backstage passes à plus de 10 jours de la date de vente")
	void testBackstagePassesMoreThan10Days() {
		Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(10, app.items()[0].sellIn);
		assertEquals(21, app.items()[0].quality);
	}

	@Test
	@DisplayName("Backstage passes à 10 jours ou moins de la date de vente")
	void testBackstagePasses10DaysOrLess() {
		Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(9, app.items()[0].sellIn);
		assertEquals(22, app.items()[0].quality);
	}

	@Test
	@DisplayName("Backstage passes à 5 jours ou moins de la date de vente")
	void testBackstagePasses5DaysOrLess() {
		Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(4, app.items()[0].sellIn);
		assertEquals(23, app.items()[0].quality);
	}

	@Test
	@DisplayName("Backstage passes après la date de vente")
	void testBackstagePassesAfterConcert() {
		Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(-1, app.items()[0].sellIn);
		assertEquals(0, app.items()[0].quality);
	}

	@Test
	@DisplayName("La qualité des Backstage passes ne peut pas dépasser 50")
	void testBackstagePassesQualityCannotExceed50() {
		Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(4, app.items()[0].sellIn);
		assertEquals(50, app.items()[0].quality);
	}

	@Test
	@DisplayName("toString de l'item")
	void testItemToString() {
		Item item = new Item("foo", 10, 20);
		assertEquals("foo, 10, 20", item.toString());
	}

	@Test
	@DisplayName("La qualité de Aged Brie ne peut pas dépasser 50 après la date de vente")
	void testAgedBrieQualityCannotExceed50AfterSellDate() {
		Item[] items = new Item[]{new Item("Aged Brie", 0, 50)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(-1, app.items()[0].sellIn);
		assertEquals(50, app.items()[0].quality);
	}

	@Test
	@DisplayName("Conjured item")
	void testConjuredItem() {
		Item[] items = new Item[]{new Item("Conjured Mana Cake", 10, 20)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(9, app.items()[0].sellIn);
		assertEquals(18, app.items()[0].quality);
	}

	@Test
	@DisplayName("Conjured item sellIn < 0")
	void testConjuredItemAfterSellDate() {
		Item[] items = new Item[]{new Item("Conjured Mana Cake", 0, 20)};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(-1, app.items()[0].sellIn);
		assertEquals(16, app.items()[0].quality);
	}
}