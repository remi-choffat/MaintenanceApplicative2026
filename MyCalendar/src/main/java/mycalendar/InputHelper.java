package mycalendar;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Classe utilitaire gérant les interactions avec l'utilisateur via la console.
 * Son rôle principal est de lire les entrées primitives (String, int)
 * et de les convertir immédiatement en Value Objects pour le domaine métier.
 */
public class InputHelper {

	public static TitreEvenement lireTitre(Scanner sc) {
		System.out.print("Titre : ");
		return new TitreEvenement(sc.nextLine());
	}

	public static DateDebut lireDate(Scanner sc) {
		System.out.print("Année : ");
		int a = Integer.parseInt(sc.nextLine());
		System.out.print("Mois : ");
		int m = Integer.parseInt(sc.nextLine());
		System.out.print("Jour : ");
		int j = Integer.parseInt(sc.nextLine());
		System.out.print("Heure : ");
		int h = Integer.parseInt(sc.nextLine());
		System.out.print("Minute : ");
		int min = Integer.parseInt(sc.nextLine());
		return new DateDebut(LocalDateTime.of(a, m, j, h, min));
	}

	public static DureeEvenement lireDuree(Scanner sc) {
		System.out.print("Durée (en minutes) : ");
		return new DureeEvenement(Integer.parseInt(sc.nextLine()));
	}

	public static LieuEvenement lireLieu(Scanner sc) {
		System.out.print("Lieu : ");
		return new LieuEvenement(sc.nextLine());
	}

	public static FrequenceEvenement lireFrequence(Scanner sc) {
		System.out.print("Frequence (en minutes) : ");
		return new FrequenceEvenement(Integer.parseInt(sc.nextLine()));
	}

	public static ParticipantsReunion lireParticipants(Scanner sc) {
		System.out.print("Participants (séparés par des virgules) : ");
		List<Utilisateur> liste = Arrays.stream(sc.nextLine().split(","))
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.map(Utilisateur::new)
				.toList();
		return new ParticipantsReunion(liste);
	}

}