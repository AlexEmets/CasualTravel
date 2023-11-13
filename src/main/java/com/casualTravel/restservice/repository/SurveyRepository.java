package com.casualTravel.restservice.repository;

import com.casualTravel.restservice.models.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends CrudRepository<Survey,Long> {
    Survey getById(Long surveyId);
}
