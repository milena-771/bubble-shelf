package ovh.electricowl.bubble.mapper;

import ovh.electricowl.bubble.dto.ProfileDTO;
import ovh.electricowl.bubble.entities.Profile;

public class ReadingProfileMapper {

	public static ProfileDTO toProfileDTO(Profile entity) {
		if (entity == null) {
			return null;
		}
		ProfileDTO dto = new ProfileDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getName());
		return dto;
	}

}
