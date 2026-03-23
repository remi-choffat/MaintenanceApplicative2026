package mycalendar;

import java.time.LocalDateTime;
import java.util.Scanner;

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
		int d = Integer.parseInt(sc.nextLine());
		return new DureeEvenement(d);
	}

	public static LieuEvenement lireLieu(Scanner sc) {
		System.out.print("Lieu : ");
		String l = sc.nextLine();
		return new LieuEvenement(l);
	}

	public static FrequenceEvenement lireFrequence(Scanner sc) {
		System.out.print("Frequence (en minutes) : ");
		int d = Integer.parseInt(sc.nextLine());
		return new FrequenceEvenement(d);
	}

}