package mycalendar;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Classe utilitaire gérant les interactions avec l'utilisateur via la console.
 * Son rôle principal est de lire les entrées primitives (String, int)
 * et de les convertir immédiatement en Value Objects pour le domaine métier.
 */
public class InputHelper {

	private static int lireEntier(Scanner sc, String prompt) {
		while (true) {
			System.out.print(prompt);
			try {
				return Integer.parseInt(sc.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("❌ Format invalide. Veuillez entrer un nombre entier.");
			}
		}
	}

	public static TitreEvenement lireTitre(Scanner sc) {
		while (true) {
			System.out.print("Titre : ");
			try {
				return Optional.of(sc.nextLine().trim())
						.filter(s -> !s.isEmpty())
						.map(TitreEvenement::new)
						.orElseThrow();
			} catch (Exception e) {
				System.out.println("❌ Le titre ne peut pas être vide.");
			}
		}
	}

	public static DateDebut lireDate(Scanner sc) {
		while (true) {
			try {
				int a = lireEntier(sc, "Année (ex: 2026) : ");
				int m = lireEntier(sc, "Mois (1-12) : ");
				int j = lireEntier(sc, "Jour (1-31) : ");
				int h = lireEntier(sc, "Heure (0-23) : ");
				int min = lireEntier(sc, "Minute (0-59) : ");
				return new DateDebut(LocalDateTime.of(a, m, j, h, min));
			} catch (DateTimeException e) {
				System.out.println("❌ Date ou heure impossible. Veuillez recommencer la saisie temporelle.");
			}
		}
	}

	public static DureeEvenement lireDuree(Scanner sc) {
		while (true) {
			try {
				int minutes = lireEntier(sc, "Durée (en minutes) : ");
				return Optional.of(minutes)
						.filter(m -> m > 0)
						.map(DureeEvenement::new)
						.orElseThrow();
			} catch (Exception e) {
				System.out.println("❌ La durée doit être un nombre positif supérieur à 0.");
			}
		}
	}

	public static LieuEvenement lireLieu(Scanner sc) {
		while (true) {
			System.out.print("Lieu : ");
			try {
				return Optional.of(sc.nextLine().trim())
						.filter(s -> !s.isEmpty())
						.map(LieuEvenement::new)
						.orElseThrow();
			} catch (Exception e) {
				System.out.println("❌ Le lieu ne peut pas être vide.");
			}
		}
	}

	public static ParticipantsReunion lireParticipants(Scanner sc) {
		System.out.print("Participants (séparés par des virgules, Entrée si aucun) : ");
		List<Utilisateur> liste = Arrays.stream(sc.nextLine().split(","))
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.map(Utilisateur::new)
				.toList();
		return new ParticipantsReunion(liste);
	}

	public static FrequenceEvenement lireFrequence(Scanner sc) {
		while (true) {
			try {
				int jours = lireEntier(sc, "Fréquence (en jours) : ");
				return Optional.of(jours)
						.filter(j -> j > 0)
						.map(FrequenceEvenement::new)
						.orElseThrow();
			} catch (Exception e) {
				System.out.println("❌ La fréquence doit être d'au moins 1 jour.");
			}
		}
	}

}