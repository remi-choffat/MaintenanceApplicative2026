package mycalendar;

import java.util.UUID;

/**
 * Value Object pour l'identifiant de l'événement.
 */
public record EventId(String valeur) {

	/**
	 * Génère automatiquement un nouvel identifiant unique basé sur un UUID.
	 *
	 * @return Une nouvelle instance de EventId.
	 */
	public static EventId generer() {
		return new EventId(UUID.randomUUID().toString());
	}
}