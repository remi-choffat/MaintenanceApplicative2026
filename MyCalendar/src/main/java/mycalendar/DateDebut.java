package mycalendar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Value Object pour la date de début.
 */
public record DateDebut(LocalDateTime valeur) {

	public final static DateDebut NOW = new DateDebut(LocalDateTime.now());

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy 'à' HH:mm", Locale.FRANCE);

	@Override
	public String toString() {
		return valeur.format(formatter);
	}
}