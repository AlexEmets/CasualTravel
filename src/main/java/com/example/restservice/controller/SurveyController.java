package com.example.restservice.controller;

import com.example.restservice.models.Survey;
import com.example.restservice.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/surveys")
public class SurveyController {
    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping
    public Survey createSurvey(@RequestBody Survey survey) {
        return surveyService.createSurvey(survey);
    }

    @GetMapping
    public Iterable<Survey> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/{surveyId}")
    public Survey getSurveyById(@PathVariable Long surveyId) {
        return surveyService.getSurveyById(surveyId).orElse(null);
    }

    @PutMapping("/{surveyId}")
    public void updateSurvey(@PathVariable Long surveyId, @RequestBody Survey survey) {
        surveyService.updateSurvey(survey);
    }

    @DeleteMapping("/{surveyId}")
    public void deleteSurvey(@PathVariable Long surveyId) {
        surveyService.deleteSurvey(surveyId);
    }
}
