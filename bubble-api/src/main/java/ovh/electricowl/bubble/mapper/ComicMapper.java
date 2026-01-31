package ovh.electricowl.bubble.mapper;

import ovh.electricowl.bubble.dto.ComicResponse;
import ovh.electricowl.bubble.entities.Comic;

public class ComicMapper {

	public static ComicResponse toDTO(Comic entity) {
		if (entity == null) {
			return null;
		}
		ComicResponse dto = new ComicResponse();
		dto.setId(entity.getId());
		dto.setIsbn(entity.getIsbn());
		dto.setTitle(entity.getTitle());
		dto.setAuthor(entity.getAuthor());
		dto.setCountry(entity.getCountry());
		dto.setPublicationYear(entity.getPublicationYear());
		dto.setGenre(entity.getGenre());
		dto.setFrontCover(entity.getFrontCover());
		dto.setEditor(entity.getEditor());
		dto.setSummary(entity.getSummary());
		return dto;

	}

}
