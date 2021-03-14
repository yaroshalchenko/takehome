package com.challenge.demo.dto;

import com.challenge.demo.domain.Question;
import com.challenge.demo.domain.Site;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class QuestionDTO {

	private Long questionId;

	private Long siteId;

	private String question;

	private Date createdAt;

	private Date updatedAt;

	private QuestionDTO parent;

	private List<QuestionDTO> children;

	public static QuestionDTO build(Question question) {
		final QuestionDTO obj = new QuestionDTO();

		if (question== null ){
			return null;
		}

		obj.setQuestionId(question.getQuestionId());
		obj.setQuestion(question.getQuestion());
		obj.setUpdatedAt(question.getUpdatedAt());
		obj.setCreatedAt(question.getCreatedAt());

		Question parent = question.getParent();

		if (parent != null){
			obj.setParent(buildParent(parent));
		}

		List<Question> children = question.getChildren();

		if (children != null && !children.isEmpty() ) {
			obj.setChildren(buildChildren(children));
		}

		return obj;
	}

	public static List<QuestionDTO> build(List<Question> questions) {
		final List<QuestionDTO> ret = new ArrayList<>();

		for (Question question : questions) {
			ret.add(build(question));
		}

		return ret.stream().filter(questionDTO -> questionDTO.getParent() == null).collect(Collectors.toList());
	}


	private static QuestionDTO buildParent(Question question) {
		final QuestionDTO obj = new QuestionDTO();

		if (question == null ){
			return null;
		}

		obj.setQuestionId(question.getQuestionId());
		obj.setQuestion(question.getQuestion());
		obj.setUpdatedAt(question.getUpdatedAt());
		obj.setCreatedAt(question.getCreatedAt());

		return obj;
	}

	public static List<QuestionDTO> buildChildren(List<Question> questions) {
		final List<QuestionDTO> ret = new ArrayList<>();

		for (Question question : questions) {
			ret.add(build(question));
		}

		return ret;
	}

	public static Question createQuestion(final QuestionDTO incomingQuestion, final Site site) {
		final Question newQ = new Question();
		newQ.setSite(site);
		newQ.setQuestion(incomingQuestion.getQuestion());

		if(incomingQuestion.getChildren() != null){
			createChildren(newQ.getChildren(), incomingQuestion.getChildren(), site, newQ);
		}

		return newQ;
	}

	private static void createChildren(List<Question> questionsCh, List<QuestionDTO> questions, Site site, Question parent) {
		for (QuestionDTO question : questions) {
			Question question1 = createQuestion(question, site);
			question1.setParent(parent);
			questionsCh.add(question1);
		}
	}
}
