package com.casualTravel.restservice.controller;

import com.casualTravel.restservice.models.Interest;
import com.casualTravel.restservice.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interests")
public class InterestController {

    @Autowired
    private InterestService interestService;

}