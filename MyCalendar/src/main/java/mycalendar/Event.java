package mycalendar;

import java.time.LocalDateTime;

public abstract class Event {
	public TitreEvenement title;
	public String proprietaire;
	public LocalDateTime dateDebut;
	public int dureeMinutes;

	public Event(TitreEvenement title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
		this.title = title;
		this.proprietaire = proprietaire;
		this.dateDebut = dateDebut;
		this.dureeMinutes = dureeMinutes;
	}

	public abstract String description();

	public abstract boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin);
}