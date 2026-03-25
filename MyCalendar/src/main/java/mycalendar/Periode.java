package mycalendar;

import java.time.LocalDateTime;

/**
 * Value Object représentant une période de temps avec une date de début et une date de fin.
 *
 * @param debut Date de début
 * @param fin   Date de fin
 */
public record Periode(LocalDateTime debut, LocalDateTime fin) {

	/**
	 * Vérifie si une date est comprise dans la période sans utiliser de "if" externe.
	 *
	 * @param date La date à vérifier.
	 * @return true si la date est comprise dans la période, false sinon.
	 */
	public boolean contient(DateDebut date) {
		return !date.valeur().isBefore(debut) && !date.valeur().isAfter(fin);
	}
}