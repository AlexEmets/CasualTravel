package com.casualTravel.restservice.service;
import com.casualTravel.restservice.dto.AnswerDTO;
import com.casualTravel.restservice.dto.InterestDTO;
import com.casualTravel.restservice.dto.QuestionDTO;
import com.casualTravel.restservice.models.Interest;
import com.casualTravel.restservice.models.Question;
import com.casualTravel.restservice.models.Survey;
import com.casualTravel.restservice.repository.SurveyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final QuestionService questionService;

    public Survey createSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    public Survey getSurvey(Long surveyID) {
        return surveyRepository.findById(surveyID)
                .orElseThrow(() -> new EntityNotFoundException("Survey not found"));
    }

    public List<Survey> getAllSurveys() {
        return (List<Survey>) surveyRepository.findAll();
    }

    public Survey updateSurvey(Long surveyID, Survey updatedSurvey) {
        Survey survey = getSurvey(surveyID);
        return surveyRepository.save(survey);
    }

    public void deleteSurvey(Long surveyID) {
        surveyRepository.deleteById(surveyID);
    }

}
