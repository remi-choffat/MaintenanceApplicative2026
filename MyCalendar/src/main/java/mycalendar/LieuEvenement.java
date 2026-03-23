package mycalendar;

/**
 * Value Object pour le lieu de la réunion.
 */
public record LieuEvenement(String valeur) {

	public final static LieuEvenement NON_RENSEIGNE = new LieuEvenement("");

	@Override
	public String toString() {
		return valeur;
	}
}