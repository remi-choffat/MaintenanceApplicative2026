package mycalendar;

import java.util.stream.Stream;

/**
 * Représente un événement spécifique de type périodique.
 * Hérite des propriétés de base de Event.
 */
public class EvenementPeriodique extends Event {
	public FrequenceEvenement frequence;

	public EvenementPeriodique(EventId id, TitreEvenement title, Utilisateur proprietaire, DateDebut dateDebut, DureeEvenement dureeMinutes, FrequenceEvenement frequence) {
		super(id, title, proprietaire, dateDebut, dureeMinutes);
		this.frequence = frequence;
	}

	@Override
	public String description() {
		return "Événement périodique : " + title + " tous les " + frequence + " jours";
	}

	@Override
	public boolean estDansPeriode(Periode periode) {
		return Stream.iterate(this.dateDebut.valeur(), d -> !d.isAfter(periode.fin()), d -> d.plusDays(this.frequence.jours()))
				.anyMatch(d -> !d.isBefore(periode.debut()));
	}

	@Override
	public boolean estEnConflitAvec(Event autre) {
		return Stream.iterate(this.dateDebut.valeur(), d -> d.isBefore(autre.calculerFin()), d -> d.plusDays(this.frequence.jours()))
				.anyMatch(d ->
						d.isBefore(autre.calculerFin()) &&
								autre.dateDebut.valeur().isBefore(d.plusMinutes(this.duree.minutes()))
				);
	}
}