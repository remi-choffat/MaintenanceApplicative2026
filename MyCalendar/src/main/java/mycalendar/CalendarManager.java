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
	                         LieuEvenement lieu, ParticipantsReunion participants, FrequenceEvenement frequenceJours) {
		// Génération automatique de l'ID métier
		EventId id = EventId.generer();
		switch (type) {
			case "RDV_PERSONNEL" -> events.add(new RdvPersonnel(id, title, proprietaire, dateDebut, dureeMinutes));
			case "REUNION" -> events.add(new Reunion(id, title, proprietaire, dateDebut, dureeMinutes, lieu, participants));
			case "PERIODIQUE" ->
					events.add(new EvenementPeriodique(id, title, proprietaire, dateDebut, dureeMinutes, frequenceJours));
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
		return e1.estEnConflitAvec(e2);
	}

	public void afficherEvenements() {
		for (Event e : events) {
			System.out.println(e.description());
		}
	}

	public void supprimerEvent(EventId id) {
		events.removeIf(e -> e.id.equals(id));
	}
}