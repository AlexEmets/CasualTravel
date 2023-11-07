package com.example.restservice.service;
import com.example.restservice.models.Question;
import com.example.restservice.models.Survey;
import com.example.restservice.repository.SurveyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    public Survey createSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    public Survey getSurvey(Long surveyID) {
        return surveyRepository.findById(surveyID)
                .orElseThrow(() -> new EntityNotFoundException("Survey not found"));
    }

    public List<Survey> listSurveys() {
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
