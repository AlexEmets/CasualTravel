package com.casualTravel.restservice.dto;

import com.casualTravel.restservice.models.Question;
import com.casualTravel.restservice.models.Survey;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class QuestionDTO {
    private Long questionId;
    private String text;
    private List<AnswerDTO> answerOptions;

    public static List<QuestionDTO> getQuestionsDTOBySurvey(Survey survey) {
        List<QuestionDTO> questionDTOs = new ArrayList<>();

        if (survey != null && survey.getQuestions() != null) {
            for (Question question : survey.getQuestions()) {
                QuestionDTO questionDTO = convertQuestionToDTO(question);
                questionDTOs.add(questionDTO);
            }
        }

        return questionDTOs;
    }

    private static QuestionDTO convertQuestionToDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO(
                question.getQuestionID(),
                question.getText(),
                AnswerDTO.getAbswerDTOByQuestion(question)
        );
        return questionDTO;
    }

    public QuestionDTO(Long questionId, String text) {
        this.questionId = questionId;
        this.text = text;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<AnswerDTO> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<AnswerDTO> answerOptions) {
        this.answerOptions = answerOptions;
    }
}
