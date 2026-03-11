package com.gildedrose;

/**
 * Classe principale de l'application GildedRose, qui gère la mise à jour de la qualité des items.
 *
 * @param items le tableau d'items à gérer
 */
record GildedRose(Item[] items) {
	public void updateQuality() {
		for (Item item : items) {
			ItemStrategy strategy = StrategyFactory.createStrategyFor(item.name);
			strategy.update(item);
		}
	}
}


/**
 * Factory pour créer la stratégie de mise à jour appropriée en fonction du nom de l'item.
 */
class StrategyFactory {

	private static final String AGED_BRIE = "Aged Brie";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	private static final String CONJURED = "Conjured";

	static ItemStrategy createStrategyFor(String name) {
		switch (name) {
			case AGED_BRIE -> {
				return new AgedBrieStrategy();
			}
			case BACKSTAGE_PASSES -> {
				return new BackstagePassesStrategy();
			}
			case SULFURAS -> {
				return new SulfurasStrategy();
			}
		}
		if (name.startsWith(CONJURED)) {
			return new ConjuredStrategy();
		}
		return new NormalItemStrategy();
	}
}


/**
 * Interface définissant la stratégie de mise à jour d'un item.
 * Chaque type d'item a sa propre implémentation de cette interface.
 */
interface ItemStrategy {
	void update(Item item);

	default void increaseQuality(Item item) {
		if (item.quality < 50) {
			item.quality++;
		}
	}

	default void decreaseQuality(Item item) {
		if (item.quality > 0) {
			item.quality--;
		}
	}
}


/**
 * Stratégie de mise à jour pour les items normaux.
 * La qualité diminue de 1 chaque jour, et de 2 après la date de vente.
 */
class NormalItemStrategy implements ItemStrategy {
	@Override
	public void update(Item item) {
		decreaseQuality(item);
		item.sellIn--;

		if (item.sellIn < 0) {
			decreaseQuality(item);
		}
	}
}


/**
 * Stratégie de mise à jour pour "Aged Brie".
 * La qualité augmente de 1 chaque jour, et de 2 après la date de vente
 */
class AgedBrieStrategy implements ItemStrategy {
	@Override
	public void update(Item item) {
		increaseQuality(item);
		item.sellIn--;

		if (item.sellIn < 0) {
			increaseQuality(item);
		}
	}
}


/**
 * Stratégie de mise à jour pour "Backstage passes".
 * La qualité augmente de 1 chaque jour, de 2 quand il reste 10 jours ou moins, et de 3 quand il reste 5 jours ou moins.
 * Après la date de vente, la qualité tombe à 0.
 */
class BackstagePassesStrategy implements ItemStrategy {
	@Override
	public void update(Item item) {
		increaseQuality(item);

		if (item.sellIn < 11) {
			increaseQuality(item);
		}
		if (item.sellIn < 6) {
			increaseQuality(item);
		}

		item.sellIn--;

		if (item.sellIn < 0) {
			item.quality = 0;
		}
	}
}


/**
 * Stratégie de mise à jour pour "Sulfuras".
 * Sulfuras est un item légendaire : ni son sellIn ni sa qualité ne changent jamais.
 */
class SulfurasStrategy implements ItemStrategy {
	@Override
	public void update(Item item) {
	}
}


/**
 * Stratégie de mise à jour pour les items "Conjured".
 * La qualité se dégrade deux fois plus vite que les items normaux.
 */
class ConjuredStrategy implements ItemStrategy {
	@Override
	public void update(Item item) {
		decreaseQuality(item);
		decreaseQuality(item);

		item.sellIn--;

		if (item.sellIn < 0) {
			decreaseQuality(item);
			decreaseQuality(item);
		}
	}
}