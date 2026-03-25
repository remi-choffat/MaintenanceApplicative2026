package mycalendar;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;

/**
 * Représente le concept abstrait d'un événement dans le calendrier.
 * Cette classe définit les propriétés communes et les méthodes
 * que tous les types d'événements spécifiques doivent implémenter.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = RdvPersonnel.class, name = "RDV_PERSONNEL"),
		@JsonSubTypes.Type(value = Reunion.class, name = "REUNION"),
		@JsonSubTypes.Type(value = EvenementPeriodique.class, name = "PERIODIQUE"),
		@JsonSubTypes.Type(value = Webinaire.class, name = "WEBINAIRE")
})
public abstract class Event {
	EventId id;
	TitreEvenement title;
	Utilisateur proprietaire;
	DateDebut dateDebut;
	DureeEvenement duree;

	public Event(EventId id, TitreEvenement title, Utilisateur proprietaire, DateDebut dateDebut, DureeEvenement duree) {
		this.id = id;
		this.title = title;
		this.proprietaire = proprietaire;
		this.dateDebut = dateDebut;
		this.duree = duree;
	}

	protected Event() {}

	/**
	 * Calcule la date et l'heure de fin de l'événement en fonction de son début et de sa durée.
	 *
	 * @return La date de fin calculée.
	 */
	public LocalDateTime calculerFin() {
		return dateDebut.valeur().plusMinutes(duree.minutes());
	}

	/**
	 * Fournit une description textuelle formatée spécifique au type de l'événement.
	 *
	 * @return La description complète de l'événement.
	 */
	public abstract String description();

	/**
	 * Détermine si l'événement a lieu, en tout ou en partie, durant la période spécifiée.
	 *
	 * @param periode L'intervalle de temps à tester.
	 * @return true si l'événement chevauche la période, false sinon.
	 */
	public abstract boolean estDansPeriode(Periode periode);

	/**
	 * Vérifie si cet événement se superpose temporellement avec un autre événement.
	 * Implémentation du pattern Double Dispatch pour éviter les conditionnels.
	 *
	 * @param autre L'événement avec lequel tester le conflit.
	 * @return true si les horaires se chevauchent, false sinon.
	 */
	public abstract boolean estEnConflitAvec(Event autre);
}