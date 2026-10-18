package ovh.electricowl.bubble.entities;

public class Profile {

	private Long id;

	private String name;

	private String description;

	private String language;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", name=" + name + ", description=" + description
				+ ", language=" + language + "]";
	}
}
