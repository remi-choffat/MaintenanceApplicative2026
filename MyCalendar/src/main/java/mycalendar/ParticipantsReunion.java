package mycalendar;

import java.util.List;
import java.util.stream.Collectors;

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