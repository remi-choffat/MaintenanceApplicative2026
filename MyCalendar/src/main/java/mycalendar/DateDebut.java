package mycalendar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Value Object représentant la date et l'heure de début d'un événement.
 *
 * @param valeur L'objet LocalDateTime encapsulé.
 */
public record DateDebut(LocalDateTime valeur) {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy 'à' HH:mm", Locale.FRANCE);

	@Override
	public String toString() {
		return valeur.format(formatter);
	}
}