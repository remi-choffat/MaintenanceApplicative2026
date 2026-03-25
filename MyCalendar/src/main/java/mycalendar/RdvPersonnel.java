package mycalendar;

public class RdvPersonnel extends Event {
	public RdvPersonnel(EventId id, TitreEvenement title, Utilisateur proprietaire, DateDebut dateDebut, DureeEvenement dureeMinutes) {
		super(id, title, proprietaire, dateDebut, dureeMinutes);
	}

	@Override
	public String description() {
		return "RDV : " + title + " le " + dateDebut.toString();
	}

	@Override
	public boolean estDansPeriode(Periode periode) {
		return periode.contient(this.dateDebut);
	}

	@Override
	public boolean estEnConflitAvec(Event autre) {
		return this.dateDebut.valeur().isBefore(autre.calculerFin()) &&
				autre.dateDebut.valeur().isBefore(this.calculerFin());
	}
}