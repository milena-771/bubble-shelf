package ovh.electricowl.bubble.mapper;

import java.util.HashSet;
import java.util.Set;

import ovh.electricowl.bubble.dto.AnswerDTO;
import ovh.electricowl.bubble.dto.ProgressDTO;
import ovh.electricowl.bubble.dto.QuestionDTO;
import ovh.electricowl.bubble.dto.QuestionResponse;
import ovh.electricowl.bubble.entities.Answer;
import ovh.electricowl.bubble.entities.Question;

public class QuestionMapper {

	public static QuestionResponse toQuestionResponse(Question entity, Integer totalQuestions) {
		if (entity == null) {
			return null;
		}
		QuestionResponse dto = new QuestionResponse();
		Set<AnswerDTO> answers = new HashSet<>();
		for (Answer answerEntity : entity.getAnswers()) {
			AnswerDTO answerDTO = new AnswerDTO();
			answerDTO.setId(answerEntity.getId());
			answerDTO.setOrder(answerEntity.getOrder());
			answerDTO.setLabel(answerEntity.getLabel());
			answerDTO.setReadingProfileId(answerEntity.getReadingProfileId());
			answers.add(answerDTO);
		}
		QuestionDTO question = new QuestionDTO();
		question.setOrder(entity.getOrder());
		question.setLabel(entity.getLabel());
		question.setAnswers(answers);
		ProgressDTO progress = new ProgressDTO();
		progress.setCurrentQuestionNumber(entity.getOrder());
		progress.setTotalQuestionNumber(totalQuestions);
		progress.setCompleted(false);
		dto.setQuestion(question);
		dto.setProgress(progress);
		return dto;
	}
}
