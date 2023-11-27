package com.casualTravel.restservice.controller;

import com.casualTravel.restservice.dto.QuestionDTO;
import com.casualTravel.restservice.dto.SurveyDataOut;
import com.casualTravel.restservice.models.Place;
import com.casualTravel.restservice.models.Survey;
import com.casualTravel.restservice.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping("/autoRoute")
    public ResponseEntity<SurveyDataOut> getAutoRouteSurvey() {
        // Логіка для отримання даних опитування
        Survey survey = surveyService.getSurvey(1L);

        SurveyDataOut surveyData = new SurveyDataOut();
        surveyData.setSurveyName(survey.getSurveyName());
        surveyData.setSurveyId(survey.getSurveyID());
        surveyData.setQuestions(QuestionDTO.getQuestionsDTOBySurvey(survey));

        System.out.println(survey.getQuestions());
        return new ResponseEntity<>(surveyData, HttpStatus.OK);
    }


}