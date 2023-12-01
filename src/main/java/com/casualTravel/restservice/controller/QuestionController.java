package com.casualTravel.restservice.controller;

import com.casualTravel.restservice.models.Question;
import com.casualTravel.restservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }

    @GetMapping("/{questionID}")
    public Question getQuestion(@PathVariable Long questionID) {
        return questionService.getQuestion(questionID);
    }

    @GetMapping
    public List<Question> listQuestions() {
        return questionService.getAllQuestions();
    }

    @PutMapping("/{questionID}")
    public Question updateQuestion(@PathVariable Long questionID, @RequestBody Question updatedQuestion) {
        return questionService.updateQuestion(questionID, updatedQuestion);
    }

    @DeleteMapping("/{questionID}")
    public void deleteQuestion(@PathVariable Long questionID) {
        questionService.deleteQuestion(questionID);
    }

    @PostMapping("/{questionId}/addsurvey/{surveyId}")
    public void addSurvey(@PathVariable Long questionId,@PathVariable Long surveyId)
    {
      questionService.addToSurveyUsingGetById(questionId,surveyId);
    }

}