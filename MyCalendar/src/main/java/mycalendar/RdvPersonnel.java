package mycalendar;

import java.time.LocalDateTime;

public class RdvPersonnel extends Event {
	public RdvPersonnel(EventId id, TitreEvenement title, Utilisateur proprietaire, DateDebut dateDebut, DureeEvenement dureeMinutes) {
		super(id, title, proprietaire, dateDebut, dureeMinutes);
	}

	@Override
	public String description() {
		return "RDV : " + title + " à " + dateDebut.toString();
	}

	@Override
	public boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin) {
		return !dateDebut.valeur().isBefore(debut) && !dateDebut.valeur().isAfter(fin);
	}

	@Override
	public boolean estEnConflitAvec(Event autre) {
		return this.dateDebut.valeur().isBefore(autre.calculerFin()) &&
				autre.dateDebut.valeur().isBefore(this.calculerFin());
	}
}