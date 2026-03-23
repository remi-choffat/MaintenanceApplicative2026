package mycalendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

	@Test
	@DisplayName("Test de la méthode description() pour un RDV personnel")
	void testDescriptionRdvPersonnel() {
		Event rdv = new RdvPersonnel(
				new TitreEvenement("Dentiste"),
				new ProprietaireEvenement("Alice"),
				LocalDateTime.of(2026, 3, 24, 10, 0),
				30
		);

		assertEquals("RDV : Dentiste à 2026-03-24T10:00", rdv.description());
	}

	@Test
	@DisplayName("Test de la méthode description() pour une réunion")
	void testDescriptionReunion() {
		Event reunion = new Reunion(
				new TitreEvenement("Point Projet"),
				new ProprietaireEvenement("Bob"),
				LocalDateTime.of(2026, 3, 25, 14, 0),
				60,
				"Salle A",
				"Alice, Charlie"
		);

		assertEquals("Réunion : Point Projet à Salle A avec Alice, Charlie", reunion.description());
	}

	@Test
	@DisplayName("Test de la méthode description() pour un événement périodique")
	void testDescriptionPeriodique() {
		Event periodique = new EvenementPeriodique(
				new TitreEvenement("Sport"),
				new ProprietaireEvenement("Charlie"),
				LocalDateTime.of(2026, 3, 26, 18, 0),
				60,
				7
		);

		assertEquals("Événement périodique : Sport tous les 7 jours", periodique.description());
	}

}