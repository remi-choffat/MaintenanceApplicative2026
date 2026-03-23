package mycalendar;

import java.time.LocalDateTime;

public abstract class Event {
	public TitreEvenement title;
	public Utilisateur proprietaire;
	public DateDebut dateDebut;
	public DureeEvenement duree;

	public Event(TitreEvenement title, Utilisateur proprietaire, DateDebut dateDebut, DureeEvenement duree) {
		this.title = title;
		this.proprietaire = proprietaire;
		this.dateDebut = dateDebut;
		this.duree = duree;
	}

	public LocalDateTime calculerFin() {
		return dateDebut.valeur().plusMinutes(duree.minutes());
	}

	public abstract String description();

	public abstract boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin);

	public abstract boolean estEnConflitAvec(Event autre);
}