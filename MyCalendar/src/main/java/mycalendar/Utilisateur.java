package mycalendar;

/**
 * Value Object pour le propriétaire de l'événement.
 */
public record Utilisateur(String valeur) {
	@Override
	public String toString() {
		return valeur;
	}
}