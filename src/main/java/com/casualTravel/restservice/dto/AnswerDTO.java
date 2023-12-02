package com.casualTravel.restservice.dto;

import com.casualTravel.restservice.models.Answer;
import com.casualTravel.restservice.models.Question;
import com.casualTravel.restservice.models.Survey;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AnswerDTO {
    private Long answerId;
    private String answerText;
    private Long interestId;
    private String interestName;

    public static List<AnswerDTO> getAnswerDTOByQuestion(Question question) {
        List<AnswerDTO> answerDTOS = new ArrayList<>();

        if (question != null && question.getAnswerOptions() != null) {
            for (Answer answer : question.getAnswerOptions()) {
                AnswerDTO answerDTO = convertAnswerToDTO(answer);
                answerDTOS.add(answerDTO);
            }
        }

        return answerDTOS;
    }

    private static AnswerDTO convertAnswerToDTO(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO(
                answer.getAnswerID(),
                answer.getAnswerText(),
                answer.getInterest().getInterestID(),
                answer.getInterest().getName()
        );
        return answerDTO;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Long getInterestId() {
        return interestId;
    }

    public void setInterestId(Long interestId) {
        this.interestId = interestId;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }
}
