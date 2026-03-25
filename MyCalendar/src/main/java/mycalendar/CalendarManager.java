package mycalendar;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestionnaire principal du calendrier.
 * Responsable du cycle de vie des événements (ajout, suppression)
 * et des opérations de recherche ou d'analyse (conflits, filtrage par période).
 */
public class CalendarManager {
	public List<Event> events;

	public CalendarManager() {
		this.events = new ArrayList<>();
	}

	/**
	 * Ajoute un nouvel événement au calendrier en déléguant sa création au type fourni.
	 * * @param type Le type d'événement (Enum déterminant la stratégie de création).
	 *
	 * @param title        Le titre de l'événement.
	 * @param proprietaire Le créateur ou propriétaire.
	 * @param dateDebut    La date de démarrage.
	 * @param duree        La durée prévue.
	 * @param lieu         Le lieu de la rencontre (si applicable).
	 * @param participants La liste des invités (si applicable).
	 * @param frequence    La récurrence en jours (si applicable).
	 */
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

	/**
	 * Recherche et retourne tous les événements qui ont lieu pendant une période donnée.
	 *
	 * @param periode La fenêtre de temps pour la recherche.
	 * @return Une liste contenant les événements trouvés (peut être vide).
	 */
	public List<Event> eventsDansPeriode(Periode periode) {
		return events.stream()
				.filter(e -> e.estDansPeriode(periode))
				.toList();
	}

	/**
	 * Vérifie si deux événements se chevauchent temporellement.
	 *
	 * @param e1 Le premier événement.
	 * @param e2 Le second événement.
	 * @return true si un conflit d'horaire est détecté, false sinon.
	 */
	public boolean conflit(Event e1, Event e2) {
		return e1.estEnConflitAvec(e2);
	}

	/**
	 * Supprime un événement du calendrier grâce à son identifiant métier.
	 *
	 * @param id L'identifiant unique de l'événement à retirer.
	 */
	public void supprimerEvent(EventId id) {
		events.removeIf(e -> e.id.equals(id));
	}
}