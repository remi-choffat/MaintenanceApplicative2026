package mycalendar;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	private static Utilisateur utilisateurConnecte = null;
	private static boolean enCours = true;

	public static void main(String[] args) {
		CalendarManager calendar = new CalendarManager();
		UtilisateurService auth = new UtilisateurService();
		Scanner scanner = new Scanner(System.in);

		// Commandes du menu (utilisateur non connecté)
		Map<String, Runnable> menuConnexion = new HashMap<>();
		menuConnexion.put("1", () -> {
			System.out.print("Nom : ");
			Utilisateur u = new Utilisateur(scanner.nextLine());
			System.out.print("Mot de passe : ");
			auth.connecter(u, scanner.nextLine()).ifPresentOrElse(
					user -> {
						utilisateurConnecte = user;
						System.out.println("✅ Connexion réussie !");
					},
					() -> System.out.println("❌ Échec de la connexion.")
			);
		});
		menuConnexion.put("2", () -> {
			System.out.print("Nom : ");
			Utilisateur u = new Utilisateur(scanner.nextLine());
			System.out.print("Mot de passe : ");
			auth.inscrire(u, scanner.nextLine());
			System.out.println("✅ Inscription réussie, vous pouvez vous connecter.");
		});
		menuConnexion.put("3", () -> enCours = false);

		// Commandes du menu (utilisateur connecté)
		Map<String, Runnable> menuPrincipal = new HashMap<>();
		menuPrincipal.put("1", () -> {
			System.out.println("\n--- Votre Planning Complet ---");
			calendar.events.forEach(e -> System.out.println("- " + e.description()));
		});
		menuPrincipal.put("2", () -> {
			calendar.ajouterEvent(EventType.RDV_PERSONNEL, InputHelper.lireTitre(scanner),
					utilisateurConnecte, InputHelper.lireDate(scanner), InputHelper.lireDuree(scanner),
					null, null, null);
			System.out.println("✅ RDV ajouté.");
		});
		menuPrincipal.put("3", () -> {
			calendar.ajouterEvent(EventType.REUNION, InputHelper.lireTitre(scanner),
					utilisateurConnecte, InputHelper.lireDate(scanner), InputHelper.lireDuree(scanner),
					InputHelper.lireLieu(scanner), InputHelper.lireParticipants(scanner), null);
			System.out.println("✅ Réunion ajoutée.");
		});
		menuPrincipal.put("4", () -> {
			calendar.ajouterEvent(EventType.PERIODIQUE, InputHelper.lireTitre(scanner),
					utilisateurConnecte, InputHelper.lireDate(scanner), InputHelper.lireDuree(scanner),
					null, null, InputHelper.lireFrequence(scanner));
			System.out.println("✅ Événement périodique ajouté.");
		});
		menuPrincipal.put("5", () -> {
			calendar.ajouterEvent(EventType.WEBINAIRE, InputHelper.lireTitre(scanner),
					utilisateurConnecte, InputHelper.lireDate(scanner), InputHelper.lireDuree(scanner),
					InputHelper.lireLieu(scanner), null, null);
			System.out.println("✅ Webinaire ajouté.");
		});
		menuPrincipal.put("6", () -> {
			utilisateurConnecte = null;
			System.out.println("👋 Déconnexion réussie.");
		});

		// Gestion de l'état (connexion)
		Map<Boolean, Runnable> controleurEtat = new HashMap<>();
		controleurEtat.put(true, () -> { // Utilisateur connecté
			System.out.println("\n1. Voir le planning | 2. Ajouter RDV | 3. Ajouter Réunion | 4. Ajouter Périodique | 5. Ajouter Webinaire | 6. Se déconnecter");
			String choix = scanner.nextLine();
			menuPrincipal.getOrDefault(choix, () -> System.out.println("❌ Choix invalide.")).run();
		});
		controleurEtat.put(false, () -> { // Aucun utilisateur connecté
			System.out.println("  _____         _                   _                __  __");
			System.out.println(" / ____|       | |                 | |              |  \\/  |");
			System.out.println("| |       __ _ | |  ___  _ __    __| |  __ _  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __");
			System.out.println("| |      / _` || | / _ \\| '_ \\  / _` | / _` || '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|");
			System.out.println("| |____ | (_| || ||  __/| | | || (_| || (_| || |    | |  | || (_| || | | || (_| || (_| ||  __/| |");
			System.out.println(" \\_____| \\__,_||_| \\___||_| |_| \\__,_| \\__,_||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|");
			System.out.println("                                                                                   __/ |");
			System.out.println("                                                                                  |___/");
			System.out.println("\n1. Se connecter | 2. S'inscrire | 3. Quitter");
			String choix = scanner.nextLine();
			menuConnexion.getOrDefault(choix, () -> System.out.println("❌ Choix invalide.")).run();
		});

		// Boucle principale de l'application
		while (enCours) {
			controleurEtat.get(utilisateurConnecte != null).run();
		}
	}
}