package mycalendar;

import java.time.LocalDateTime;

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
		LocalDateTime temp = dateDebut.valeur();
		while (temp.isBefore(periode.fin())) {
			if (!temp.isBefore(periode.debut())) {
				return true;
			}
			temp = temp.plusDays(frequence.jours());
		}
		return false;
	}

	@Override
	public boolean estEnConflitAvec(Event autre) {
		return false;
	}
}