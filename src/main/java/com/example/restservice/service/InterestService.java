package com.example.restservice.service;

import com.example.restservice.models.Interest;
import com.example.restservice.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InterestService {
    private final InterestRepository interestRepository;

    @Autowired
    public InterestService(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public Interest createInterest(Interest interest) {
        return interestRepository.save(interest);
    }

    public Iterable<Interest> getAllInterests() {
        return interestRepository.findAll();
    }

    public Optional<Interest> getInterestById(Long interestId) {
        return interestRepository.findById(interestId);
    }

    public void updateInterest(Interest interest) {
        interestRepository.save(interest);
    }

    public void deleteInterest(Long interestId) {
        interestRepository.deleteById(interestId);
    }
}
