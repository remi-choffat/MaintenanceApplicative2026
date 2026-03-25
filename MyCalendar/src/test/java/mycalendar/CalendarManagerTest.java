package mycalendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static mycalendar.EventType.*;
import static org.junit.jupiter.api.Assertions.*;

class CalendarManagerTest {

	@Test
	@DisplayName("Test de conflit entre deux rendez-vous qui se chevauchent")
	void testConflitEntreDeuxRdv() {
		CalendarManager manager = new CalendarManager();

		// Rdv 1 : 10h00 - 11h00
		Event rdv1 = new RdvPersonnel(
				new EventId("EVT-001"),
				new TitreEvenement("Réunion A"), new Utilisateur("Alice"),
				new DateDebut(LocalDateTime.of(2026, 3, 24, 10, 0)), new DureeEvenement(60));

		// Rdv 2 : 10h30 - 11h30
		Event rdv2 = new RdvPersonnel(
				new EventId("EVT-002"),
				new TitreEvenement("Réunion B"), new Utilisateur("Bob"),
				new DateDebut(LocalDateTime.of(2026, 3, 24, 10, 30)), new DureeEvenement(60));

		assertTrue(manager.conflit(rdv1, rdv2), "Un conflit devrait être détecté");
	}

	@Test
	@DisplayName("Test de suppression d'un événement par son ID")
	void testSuppressionEvenementParId() {
		CalendarManager manager = new CalendarManager();
		EventId id = new EventId("EVT-001");
		Event rdv = new RdvPersonnel(
				id,
				new TitreEvenement("Réunion Test"),
				new Utilisateur("Alice"),
				new DateDebut(LocalDateTime.of(2026, 3, 24, 10, 0)),
				new DureeEvenement(60)
		);
		manager.events.add(rdv);
		manager.supprimerEvent(id);
		assertEquals(0, manager.events.size(), "L'événement devrait avoir été supprimé");
	}

	@Test
	@DisplayName("Test de génération automatique d'ID unique pour les événements")
	void testGenerationAutomatiqueIdUnique() {
		CalendarManager manager = new CalendarManager();

		manager.ajouterEvent(RDV_PERSONNEL, new TitreEvenement("Réunion A"), new Utilisateur("Alice"),
				new DateDebut(LocalDateTime.of(2026, 3, 24, 10, 0)), new DureeEvenement(60),
				LieuEvenement.NON_RENSEIGNE, ParticipantsReunion.AUCUN, new FrequenceEvenement(0));

		manager.ajouterEvent(RDV_PERSONNEL, new TitreEvenement("Réunion B"), new Utilisateur("Bob"),
				new DateDebut(LocalDateTime.of(2026, 3, 24, 11, 0)), new DureeEvenement(60),
				LieuEvenement.NON_RENSEIGNE, ParticipantsReunion.AUCUN, new FrequenceEvenement(0));

		Event e1 = manager.events.get(0);
		Event e2 = manager.events.get(1);

		assertNotNull(e1.id);
		assertNotNull(e2.id);
		assertNotEquals(e1.id, e2.id, "Chaque événement doit avoir un ID unique");
	}

	@Test
	@DisplayName("Test de recherche d'événements dans une période donnée")
	void testRechercheDansPeriode() {
		CalendarManager manager = new CalendarManager();
		manager.ajouterEvent(EventType.RDV_PERSONNEL, new TitreEvenement("Test"), new Utilisateur("Alice"),
				new DateDebut(LocalDateTime.of(2026, 3, 24, 10, 0)), new DureeEvenement(30),
				null, null, null);

		Periode periodeRecherche = new Periode(
				LocalDateTime.of(2026, 3, 24, 0, 0),
				LocalDateTime.of(2026, 3, 24, 23, 59)
		);

		List<Event> resultats = manager.eventsDansPeriode(periodeRecherche);

		assertEquals(1, resultats.size(), "L'événement devrait être trouvé dans la période");
	}

	@Test
	@DisplayName("Test d'ajout d'un nouveau type d'événement : Webinaire")
	void testAjoutNouveauTypeWebinaire() {
		CalendarManager manager = new CalendarManager();

		manager.ajouterEvent(
				EventType.WEBINAIRE,
				new TitreEvenement("Présentation TDD"),
				new Utilisateur("Alice"),
				new DateDebut(LocalDateTime.of(2026, 3, 25, 10, 0)),
				new DureeEvenement(45),
				new LieuEvenement("https://meet.google.com/abc"),
				null,
				null
		);

		assertEquals(1, manager.events.size(), "Le webinaire doit être ajouté");
		assertEquals("Webinaire : Présentation TDD en ligne sur https://meet.google.com/abc",
				manager.events.getFirst().description(),
				"La description du webinaire doit être correcte");
	}

	@Test
	@DisplayName("Test de conflit entre un événement périodique et un rendez-vous simple")
	void testConflitEvenementPeriodique() {
		CalendarManager manager = new CalendarManager();

		// Événement périodique : Tous les 7 jours à partir du 1er mars 10h00 (durée 60 min)
		Event periodique = EventType.PERIODIQUE.creer(
				EventId.generer(), new TitreEvenement("Sport"), new Utilisateur("Alice"),
				new DateDebut(LocalDateTime.of(2026, 3, 1, 10, 0)), new DureeEvenement(60),
				null, null, new FrequenceEvenement(7)
		);

		// RDV simple : Le 15 mars à 10h30 (durée 30 min)
		Event rdv = EventType.RDV_PERSONNEL.creer(
				EventId.generer(), new TitreEvenement("Dentiste"), new Utilisateur("Alice"),
				new DateDebut(LocalDateTime.of(2026, 3, 15, 10, 30)), new DureeEvenement(30),
				null, null, null
		);

		assertTrue(manager.conflit(periodique, rdv), "Un conflit doit être détecté sur l'occurrence du 15 mars");
	}
}