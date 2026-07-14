package ovh.electricowl.bubble.mapper;

import ovh.electricowl.bubble.dto.ComicDTO;
import ovh.electricowl.bubble.entities.Comic;

public class ComicMapper {

	public static ComicDTO toComicDTO(Comic entity) {
		if (entity == null) {
			return null;
		}
		ComicDTO dto = new ComicDTO();
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
		dto.setIdProfile(entity.getIdProfile());
		return dto;

	}

}
