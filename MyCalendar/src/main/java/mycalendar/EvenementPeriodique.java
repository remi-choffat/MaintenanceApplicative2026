package mycalendar;

import java.time.LocalDateTime;

public class EvenementPeriodique extends Event {
	public int frequenceJours;

	public EvenementPeriodique(TitreEvenement title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, int frequenceJours) {
		super(title, proprietaire, dateDebut, dureeMinutes);
		this.frequenceJours = frequenceJours;
	}

	@Override
	public String description() {
		return "Événement périodique : " + title + " tous les " + frequenceJours + " jours";
	}

	@Override
	public boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin) {
		LocalDateTime temp = dateDebut;
		while (temp.isBefore(fin)) {
			if (!temp.isBefore(debut)) {
				return true;
			}
			temp = temp.plusDays(frequenceJours);
		}
		return false;
	}
}