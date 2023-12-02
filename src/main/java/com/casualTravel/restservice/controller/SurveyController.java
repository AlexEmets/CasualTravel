package com.casualTravel.restservice.controller;

import com.casualTravel.restservice.dto.*;
import com.casualTravel.restservice.models.Interest;
import com.casualTravel.restservice.models.Place;
import com.casualTravel.restservice.models.Survey;
import com.casualTravel.restservice.models.User;
import com.casualTravel.restservice.service.JwtService;
import com.casualTravel.restservice.service.SurveyService;
import com.casualTravel.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<SurveyDataOut>> getAutoRouteSurvey() {
        // Логіка для отримання даних опитування
        List<Survey> surveys = surveyService.getAllSurveys();

        List<SurveyDataOut> listAllSurveys = new ArrayList<>();

        for (Survey survey : surveys){
            SurveyDataOut surveyData = new SurveyDataOut();
            surveyData.setSurveyName(survey.getSurveyName());
            surveyData.setSurveyId(survey.getSurveyID());
            surveyData.setQuestions(QuestionDTO.getQuestionsDTOBySurvey(survey));
            listAllSurveys.add(surveyData);
        }
        return new ResponseEntity<>( listAllSurveys, HttpStatus.OK);
    }

    @PostMapping("/profile/answers")
    public ResponseEntity<UserDTO> getAutoRoutePlaces(@RequestBody List<AnswerDTO> answers, @RequestHeader("Authorization") String authorizationHeader) {

        User user = new User();
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // "Bearer ".length()
            String userEmail = jwtService.extractEmail(token);
            user = (User) userService.loadUserByUsername(userEmail);
        } else user = null;

        User advancedUser = userService.addInterestsByAnswers(user, answers);
        UserDTO userDTO = UserDTO.mapToUserDTO(advancedUser);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }



}