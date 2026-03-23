package mycalendar;

/**
 * Value Object pour la fréquence (en jours).
 */
public record FrequenceEvenement(int jours) {
	@Override
	public String toString() {
		return String.valueOf(jours);
	}
}