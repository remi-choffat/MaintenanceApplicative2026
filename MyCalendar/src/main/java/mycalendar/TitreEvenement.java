package mycalendar;

/**
 * Value Object pour le titre.
 */
public record TitreEvenement(String valeur) {
	@Override
	public String toString() {
		return valeur;
	}
}