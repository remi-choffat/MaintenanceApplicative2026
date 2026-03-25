package mycalendar;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UtilisateurService {
	private final Map<Utilisateur, String> identifiants = new HashMap<>();

	public void inscrire(Utilisateur utilisateur, String motDePasse) {
		identifiants.put(utilisateur, motDePasse);
	}

	public Optional<Utilisateur> connecter(Utilisateur utilisateur, String motDePasse) {
		return Optional.of(utilisateur)
				.filter(identifiants::containsKey)
				.filter(u -> identifiants.get(u).equals(motDePasse));
	}
}