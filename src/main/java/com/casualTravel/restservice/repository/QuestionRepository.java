package com.casualTravel.restservice.repository;
import com.casualTravel.restservice.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    Question getById(Long questionId);
}
