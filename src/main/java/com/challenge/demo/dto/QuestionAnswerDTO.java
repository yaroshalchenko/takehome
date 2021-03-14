package com.challenge.demo.dto;

import com.challenge.demo.domain.Question;
import com.challenge.demo.domain.QuestionAnswer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class QuestionAnswerDTO {
	private Long id;
	private Long questionId;
	private String answer;
	private Boolean isCorrectAnswer;
	private Date createdAt;
	private Date updatedAt;
	private UUID uuid;

	public static QuestionAnswer transform(final QuestionAnswerDTO newQADto, final Question question) {
		final QuestionAnswer newQa = new QuestionAnswer();
		newQa.setAnswer(newQADto.getAnswer());
		newQa.setIsCorrectAnswer(newQADto.getIsCorrectAnswer());
		newQa.setQuestion(question);

		return newQa;
	}

	public static QuestionAnswerDTO build(final QuestionAnswer save) {
		final QuestionAnswerDTO newQaDto = new QuestionAnswerDTO();

		newQaDto.setId(save.getId());
		newQaDto.setAnswer(save.getAnswer());
		newQaDto.setIsCorrectAnswer(save.getIsCorrectAnswer());
		newQaDto.setCreatedAt(save.getCreatedAt());
		newQaDto.setUpdatedAt(save.getUpdatedAt());
		newQaDto.setQuestionId(save.getQuestion().getQuestionId());

		return newQaDto;
	}

	public static List<QuestionAnswerDTO> build(final List<QuestionAnswer> answers) {
		final List<QuestionAnswerDTO> ret = new ArrayList<>();
		for (QuestionAnswer qa : answers) {
			ret.add(build(qa));
		}
		return ret;
	}
}
