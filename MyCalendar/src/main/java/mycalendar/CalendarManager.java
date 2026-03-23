package mycalendar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarManager {
	public List<Event> events;

	public CalendarManager() {
		this.events = new ArrayList<>();
	}

	public void ajouterEvent(String type, TitreEvenement title, Utilisateur proprietaire, DateDebut dateDebut, DureeEvenement dureeMinutes,
	                         String lieu, String participants, int frequenceJours) {
		switch (type) {
			case "RDV_PERSONNEL" -> events.add(new RdvPersonnel(title, proprietaire, dateDebut, dureeMinutes));
			case "REUNION" -> events.add(new Reunion(title, proprietaire, dateDebut, dureeMinutes, lieu, participants));
			case "PERIODIQUE" ->
					events.add(new EvenementPeriodique(title, proprietaire, dateDebut, dureeMinutes, frequenceJours));
		}
	}

	public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
		List<Event> result = new ArrayList<>();
		for (Event e : events) {
			if (e.estDansPeriode(debut, fin)) {
				result.add(e);
			}
		}
		return result;
	}

	public boolean conflit(Event e1, Event e2) {
		LocalDateTime fin1 = e1.dateDebut.valeur().plusMinutes(e1.duree.minutes());
		LocalDateTime fin2 = e2.dateDebut.valeur().plusMinutes(e2.duree.minutes());

		if (e1 instanceof EvenementPeriodique || e2 instanceof EvenementPeriodique) {
			return false; // Simplification abusive
		}

		if (e1.dateDebut.valeur().isBefore(fin2) && fin1.isAfter(e2.dateDebut.valeur())) {
			return true;
		}
		return false;
	}

	public void afficherEvenements() {
		for (Event e : events) {
			System.out.println(e.description());
		}
	}
}