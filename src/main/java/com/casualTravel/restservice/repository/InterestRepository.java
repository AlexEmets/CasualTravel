package com.casualTravel.restservice.repository;

import com.casualTravel.restservice.models.Interest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends CrudRepository<Interest,Long> {
}
