package mycalendar;

/**
 * Value Object pour le propriétaire de l'événement.
 */
public record Utilisateur(String nom) {
	@Override
	public String toString() {
		return nom;
	}
}