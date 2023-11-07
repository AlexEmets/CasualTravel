package com.example.restservice.repository;
import com.example.restservice.models.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question,Long> {
    Question getById(Long questionId);
}
