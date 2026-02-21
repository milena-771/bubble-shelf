package ovh.electricowl.bubble.entities;

public enum ReadingProfile {

	THE_DREAMER(1, "The Dreamer"), THE_ADVENTURER(2, "The Adventurer"),
	THE_DARK_MIND(3, "The Dark Mind"), THE_LAUGH_SEEKER(4, "The Laugh Seeker"),
	THE_ACTIVIST_READER(5, "The Activist Reader"), OLD_CLASSIC_READER(6, "The Old Classic Reader");

	private long id;

	private String name;

	private ReadingProfile(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static ReadingProfile getLabelById(long id) {
		for (ReadingProfile profile : ReadingProfile.values()) {
			if (profile.getId() == id) {
				return profile;
			}
		}
		return null;
	}
}
