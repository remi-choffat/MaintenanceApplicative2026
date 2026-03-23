package mycalendar;

import java.util.UUID;

/**
 * Value Object pour l'identifiant de l'événement.
 */
public record EventId(String valeur) {
	public static EventId generer() {
		return new EventId(UUID.randomUUID().toString());
	}
}