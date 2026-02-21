package ovh.electricowl.bubble.dto;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class ResultResponse {

	private UUID sessionId;

	private Map<Long, Integer> statistics;

	private Set<ProfileDTO> profileDescription;

	private Set<ComicDTO> recommendedComics;

	public UUID getSessionId() {
		return sessionId;
	}

	public Map<Long, Integer> getStatistics() {
		return statistics;
	}

	public Set<ProfileDTO> getProfileDescription() {
		return profileDescription;
	}

	public Set<ComicDTO> getRecommendedComics() {
		return recommendedComics;
	}

	public void setSessionId(UUID sessionId) {
		this.sessionId = sessionId;
	}

	public void setStatistics(Map<Long, Integer> statistics) {
		this.statistics = statistics;
	}

	public void setProfileDescription(Set<ProfileDTO> profileDescription) {
		this.profileDescription = profileDescription;
	}

	public void setRecommendedComics(Set<ComicDTO> recommendedComics) {
		this.recommendedComics = recommendedComics;
	}

	@Override
	public int hashCode() {
		return Objects.hash(profileDescription, recommendedComics, sessionId, statistics);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ResultResponse other = (ResultResponse) obj;
		return Objects.equals(profileDescription, other.profileDescription)
				&& Objects.equals(recommendedComics, other.recommendedComics)
				&& Objects.equals(sessionId, other.sessionId)
				&& Objects.equals(statistics, other.statistics);
	}

	@Override
	public String toString() {
		return "ResultResponse [sessionId=" + sessionId + ", statistics=" + statistics
				+ ", profileDescription=" + profileDescription + ", recommendedComics="
				+ recommendedComics + "]";
	}

}
