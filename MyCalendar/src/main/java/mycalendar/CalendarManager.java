package mycalendar;

import java.util.ArrayList;
import java.util.List;

public class CalendarManager {
	public List<Event> events;

	public CalendarManager() {
		this.events = new ArrayList<>();
	}

	public void ajouterEvent(EventType type, TitreEvenement title, Utilisateur proprietaire, DateDebut dateDebut, DureeEvenement duree,
	                         LieuEvenement lieu, ParticipantsReunion participants, FrequenceEvenement frequence) {
		Event nouvelEvent = type.creer(
				EventId.generer(),
				title,
				proprietaire,
				dateDebut,
				duree,
				lieu,
				participants,
				frequence
		);
		events.add(nouvelEvent);
	}

	public List<Event> eventsDansPeriode(Periode periode) {
		List<Event> result = new ArrayList<>();
		for (Event e : events) {
			if (e.estDansPeriode(periode)) {
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