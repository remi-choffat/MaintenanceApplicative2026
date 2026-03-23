package mycalendar;

import java.time.LocalDateTime;

public record Periode(LocalDateTime debut, LocalDateTime fin) {

	/**
	 * Vérifie si une date est comprise dans la période sans utiliser de "if" externe.
	 */
	public boolean contient(DateDebut date) {
		return !date.valeur().isBefore(debut) && !date.valeur().isAfter(fin);
	}
}