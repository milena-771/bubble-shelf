package ovh.electricowl.bubble.entities;

import java.util.Arrays;

public enum AcceptLangEnum {

	ENGLISH("en"), FRENCH("fr");

	private String codeLanguage;

	private AcceptLangEnum(String codeLanguage) {
		this.codeLanguage = codeLanguage;
	}

	public String getCodeLanguage() {
		return codeLanguage;
	}

	public void setCodeLanguage(String codeLanguage) {
		this.codeLanguage = codeLanguage;
	}

	public static boolean isValidLanguage(String code) {
		return Arrays.stream(AcceptLangEnum.values())
				.anyMatch(lang -> lang.getCodeLanguage().equals(code));
	}

}
