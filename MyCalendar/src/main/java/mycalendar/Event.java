package mycalendar;

import java.time.LocalDateTime;

public abstract class Event {
	public EventId id;
	public TitreEvenement title;
	public Utilisateur proprietaire;
	public DateDebut dateDebut;
	public DureeEvenement duree;

	public Event(EventId id, TitreEvenement title, Utilisateur proprietaire, DateDebut dateDebut, DureeEvenement duree) {
		this.id = id;
		this.title = title;
		this.proprietaire = proprietaire;
		this.dateDebut = dateDebut;
		this.duree = duree;
	}

	public LocalDateTime calculerFin() {
		return dateDebut.valeur().plusMinutes(duree.minutes());
	}

	public abstract String description();

	public abstract boolean estDansPeriode(Periode periode);

	public abstract boolean estEnConflitAvec(Event autre);
}