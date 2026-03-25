package mycalendar;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Value Object encapsulant la liste des participants à une réunion.
 *
 * @param liste La liste des utilisateurs participant à la réunion.
 */
public record ParticipantsReunion(List<Utilisateur> liste) {

	public final static ParticipantsReunion AUCUN = new ParticipantsReunion(List.of());

	@Override
	public String toString() {
		return liste.stream()
				.map(Utilisateur::nom)
				.collect(Collectors.joining(", "));
	}

	public void ajouterParticipant(Utilisateur utilisateur) {
		liste.add(utilisateur);
	}
}