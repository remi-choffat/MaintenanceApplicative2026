package mycalendar;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Service gérant l'authentification et l'inscription des utilisateurs du calendrier.
 * Conçu de manière fonctionnelle pour éviter les conditionnels explicites.
 */
public class UtilisateurService {
	private final Map<Utilisateur, String> identifiants = new HashMap<>();

	/**
	 * Enregistre un nouvel utilisateur dans le système avec son mot de passe.
	 *
	 * @param utilisateur Le Value Object représentant le nom de l'utilisateur.
	 * @param motDePasse  Le mot de passe associé en clair.
	 */
	public void inscrire(Utilisateur utilisateur, String motDePasse) {
		identifiants.put(utilisateur, motDePasse);
	}

	/**
	 * Tente d'authentifier un utilisateur.
	 *
	 * @param utilisateur Le nom de l'utilisateur tentant de se connecter.
	 * @param motDePasse  Le mot de passe fourni.
	 * @return Un Optional contenant l'Utilisateur si l'authentification réussit, ou un Optional vide sinon.
	 */
	public Optional<Utilisateur> connecter(Utilisateur utilisateur, String motDePasse) {
		return Optional.of(utilisateur)
				.filter(identifiants::containsKey)
				.filter(u -> identifiants.get(u).equals(motDePasse));
	}
}