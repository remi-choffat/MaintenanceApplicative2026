package mycalendar;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service gérant la persistance du calendrier dans un fichier JSON.
 */
public class JsonStorageService {
	private final ObjectMapper mapper;

	public JsonStorageService() {
		this.mapper = new ObjectMapper();
		this.mapper.registerModule(new JavaTimeModule());
		this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		this.mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		this.mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
	}

	/**
	 * Récupère le fichier de sauvegarde d'un utilisateur
	 *
	 * @param utilisateur L'utilisateur connecté
	 * @return Le fichier contenant la sauvegarde du calendrier
	 */
	private File getFichierUtilisateur(Utilisateur utilisateur) {
		return new File("calendrier-" + utilisateur.nom() + ".json");
	}

	/**
	 * Sauvegarde le calendrier
	 *
	 * @param utilisateur L'utilisateur connecté
	 * @param events      évènements du calendrier
	 */
	public void sauvegarder(Utilisateur utilisateur, List<Event> events) {
		try {
			mapper.writerFor(new TypeReference<List<Event>>() {})
					.writeValue(getFichierUtilisateur(utilisateur), events);
			System.out.println("💾 Calendrier de " + utilisateur.nom() + " sauvegardé !");
		} catch (IOException e) {
			System.err.println("❌ Erreur lors de la sauvegarde : " + e.getMessage());
		}
	}

	/**
	 * Charge le calendrier à partir d'un fichier
	 *
	 * @param utilisateur L'utilisateur connecté
	 * @return la liste des évènements du calendrier
	 */
	public List<Event> charger(Utilisateur utilisateur) {
		File fichier = getFichierUtilisateur(utilisateur);
		if (!fichier.exists()) {
			return new ArrayList<>();
		}
		try {
			return mapper.readValue(fichier, new TypeReference<List<Event>>() {
			});
		} catch (IOException e) {
			System.err.println("❌ Erreur lors du chargement : " + e.getMessage());
			return new ArrayList<>();
		}
	}
}