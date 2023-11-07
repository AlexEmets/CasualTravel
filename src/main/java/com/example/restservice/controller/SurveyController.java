package com.example.restservice.controller;

import com.example.restservice.models.Survey;
import com.example.restservice.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @PostMapping
    public Survey createSurvey(@RequestBody Survey survey) {
        return surveyService.createSurvey(survey);
    }

    @GetMapping("/{surveyID}")
    public Survey getSurvey(@PathVariable Long surveyID) {
        return surveyService.getSurvey(surveyID);
    }

    @GetMapping
    public List<Survey> listSurveys() {
        return surveyService.listSurveys();
    }

    @PutMapping("/{surveyID}")
    public Survey updateSurvey(@PathVariable Long surveyID, @RequestBody Survey updatedSurvey) {
        return surveyService.updateSurvey(surveyID, updatedSurvey);
    }

    @DeleteMapping("/{surveyID}")
    public void deleteSurvey(@PathVariable Long surveyID) {
        surveyService.deleteSurvey(surveyID);
    }

}