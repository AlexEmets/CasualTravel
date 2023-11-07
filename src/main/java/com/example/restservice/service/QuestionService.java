package com.example.restservice.service;
import com.example.restservice.models.Question;
import com.example.restservice.models.Survey;
import com.example.restservice.repository.QuestionRepository;
import com.example.restservice.repository.SurveyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private SurveyRepository surveyRepository;

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question getQuestion(Long questionID) {
        return questionRepository.findById(questionID)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));
    }

    public List<Question> listQuestions() {
        return (List<Question>) questionRepository.findAll();
    }

    public Question updateQuestion(Long questionID, Question updatedQuestion) {
        Question question = getQuestion(questionID);
        question.setText(updatedQuestion.getText());
        question.setAnswerOptions(updatedQuestion.getAnswerOptions());
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long questionID) {
        questionRepository.deleteById(questionID);
    }
    public Question addToSurveyUsingGetById(Long questionId,Long surveyId) {
        Survey survey = surveyRepository.getById(surveyId);
        Question question = questionRepository.getById(questionId);
        question.setSurvey(survey);
        question = questionRepository.save(question);
        return question;
    }
}