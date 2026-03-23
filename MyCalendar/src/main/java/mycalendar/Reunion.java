package mycalendar;

import java.time.LocalDateTime;

public class Reunion extends Event {
	public LieuEvenement lieu;
	public String participants;

	public Reunion(TitreEvenement title, Utilisateur proprietaire, DateDebut dateDebut, DureeEvenement dureeMinutes, LieuEvenement lieu, String participants) {
		super(title, proprietaire, dateDebut, dureeMinutes);
		this.lieu = lieu;
		this.participants = participants;
	}

	@Override
	public String description() {
		return "Réunion : " + title + " à " + lieu + " avec " + participants;
	}

	@Override
	public boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin) {
		return !dateDebut.valeur().isBefore(debut) && !dateDebut.valeur().isAfter(fin);
	}
}