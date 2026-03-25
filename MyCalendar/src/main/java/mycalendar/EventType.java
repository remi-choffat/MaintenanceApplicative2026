package mycalendar;

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

	public abstract Event creer(EventId id, TitreEvenement t, Utilisateur u, DateDebut d, DureeEvenement dur,
	                            LieuEvenement l, ParticipantsReunion p, FrequenceEvenement f);
}