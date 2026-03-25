package mycalendar;

/**
 * Énumération définissant les différents types d'événements supportés par le système.
 * Implémente le pattern Strategy pour déléguer la création de l'objet métier
 * sans utiliser de structure conditionnelle.
 */
public enum EventType {
	RDV_PERSONNEL {
		@Override
		public Event creer(EventId id, TitreEvenement t, Utilisateur u, DateDebut d, DureeEvenement dur,
		                   LieuEvenement l, ParticipantsReunion p, FrequenceEvenement f) {
			return new RdvPersonnel(id, t, u, d, dur);
		}
	},
	REUNION {
		@Override
		public Event creer(EventId id, TitreEvenement t, Utilisateur u, DateDebut d, DureeEvenement dur,
		                   LieuEvenement l, ParticipantsReunion p, FrequenceEvenement f) {
			return new Reunion(id, t, u, d, dur, l, p);
		}
	},
	PERIODIQUE {
		@Override
		public Event creer(EventId id, TitreEvenement t, Utilisateur u, DateDebut d, DureeEvenement dur,
		                   LieuEvenement l, ParticipantsReunion p, FrequenceEvenement f) {
			return new EvenementPeriodique(id, t, u, d, dur, f);
		}
	},
	WEBINAIRE {
		@Override
		public Event creer(EventId id, TitreEvenement t, Utilisateur u, DateDebut d, DureeEvenement dur, LieuEvenement l, ParticipantsReunion p, FrequenceEvenement f) {
			return new Webinaire(id, t, u, d, dur, l);
		}
	};

	/**
	 * Instancie le type d'événement correspondant à la constante de l'énumération.
	 *
	 * @param id  L'identifiant métier unique.
	 * @param t   Le titre de l'événement.
	 * @param u   Le propriétaire de l'événement.
	 * @param d   La date de début.
	 * @param dur La durée de l'événement.
	 * @param l   Le lieu (peut être null selon le type).
	 * @param p   Les participants (peut être null selon le type).
	 * @param f   La fréquence (peut être null selon le type).
	 * @return Une nouvelle instance concrète héritant de Event.
	 */
	public abstract Event creer(EventId id, TitreEvenement t, Utilisateur u, DateDebut d, DureeEvenement dur,
	                            LieuEvenement l, ParticipantsReunion p, FrequenceEvenement f);
}