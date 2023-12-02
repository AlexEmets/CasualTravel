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


}