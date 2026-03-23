package mycalendar;

import java.time.LocalDateTime;

public class RdvPersonnel extends Event {
	public RdvPersonnel(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
		super(title, proprietaire, dateDebut, dureeMinutes);
	}

	@Override
	public String description() {
		return "RDV : " + title + " à " + dateDebut.toString();
	}

	@Override
	public boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin) {
		return !dateDebut.isBefore(debut) && !dateDebut.isAfter(fin);
	}
}