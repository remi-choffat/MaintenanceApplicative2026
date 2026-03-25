package mycalendar;

/**
 * Représente un événement spécifique de type webinaire.
 * Hérite des propriétés de base de Event.
 */
public class Webinaire extends Event {
	LieuEvenement lien;

	public Webinaire(EventId id, TitreEvenement title, Utilisateur proprietaire, DateDebut dateDebut, DureeEvenement duree, LieuEvenement lien) {
		super(id, title, proprietaire, dateDebut, duree);
		this.lien = lien;
	}

	protected Webinaire() { super(); }

	@Override
	public String description() {
		return "Webinaire : " + title + " en ligne sur " + lien;
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