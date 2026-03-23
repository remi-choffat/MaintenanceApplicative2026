package mycalendar;

import java.time.LocalDateTime;

public abstract class Event {
	public TitreEvenement title;
	public Utilisateur proprietaire;
	public LocalDateTime dateDebut;
	public DureeEvenement duree;

	public Event(TitreEvenement title, Utilisateur proprietaire, LocalDateTime dateDebut, DureeEvenement duree) {
		this.title = title;
		this.proprietaire = proprietaire;
		this.dateDebut = dateDebut;
		this.duree = duree;
	}

	public abstract String description();

	public abstract boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin);
}