package mycalendar;

/**
 * Value Object pour la durée.
 */
public record DureeEvenement(int minutes) {
	// Durée 0
	public final static DureeEvenement EMPTY = new DureeEvenement(0);

	@Override
	public String toString() {
		return String.valueOf(minutes);
	}
}