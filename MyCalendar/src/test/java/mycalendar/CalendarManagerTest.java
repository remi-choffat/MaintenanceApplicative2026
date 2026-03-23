package mycalendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CalendarManagerTest {

	@Test
	@DisplayName("Test de conflit entre deux rendez-vous qui se chevauchent")
	void testConflitEntreDeuxRdv() {
		CalendarManager manager = new CalendarManager();

		// Rdv 1 : 10h00 - 11h00
		Event rdv1 = new RdvPersonnel(
				new TitreEvenement("Réunion A"), new Utilisateur("Alice"),
				new DateDebut(LocalDateTime.of(2026, 3, 24, 10, 0)), new DureeEvenement(60));

		// Rdv 2 : 10h30 - 11h30
		Event rdv2 = new RdvPersonnel(
				new TitreEvenement("Réunion B"), new Utilisateur("Bob"),
				new DateDebut(LocalDateTime.of(2026, 3, 24, 10, 30)), new DureeEvenement(60));

		assertTrue(manager.conflit(rdv1, rdv2), "Un conflit devrait être détecté");
	}
}