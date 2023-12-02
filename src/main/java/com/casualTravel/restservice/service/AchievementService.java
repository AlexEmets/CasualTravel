package com.casualTravel.restservice.service;

import com.casualTravel.restservice.models.Achievement;
import com.casualTravel.restservice.models.User;
import com.casualTravel.restservice.repository.AchievementRepository;
import com.casualTravel.restservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AchievementService {

    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;

    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    public Optional<Achievement> getAchievementById(Long achievementId) {
        return achievementRepository.findById(achievementId);
    }

    public Achievement createAchievement(Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    public Achievement updateAchievement(Long achievementId, Achievement updatedAchievement) {
        Optional<Achievement> existingAchievementOptional = achievementRepository.findById(achievementId);

        if (existingAchievementOptional.isPresent()) {
            Achievement existingAchievement = existingAchievementOptional.get();
            existingAchievement.setName(updatedAchievement.getName());
            existingAchievement.setLevel(updatedAchievement.getLevel());
            existingAchievement.setAchievementPhotoUrl(updatedAchievement.getAchievementPhotoUrl());

            return achievementRepository.save(existingAchievement);
        } else {
            // Handle case where the achievement with the given ID is not found
            throw new IllegalArgumentException("Achievement with ID " + achievementId + " not found");
        }
    }

    public void deleteAchievement(Long achievementId) {
        achievementRepository.deleteById(achievementId);
    }

    public void addAchievementToUser(Long achievementId, Long userId) {
        Achievement achievement = achievementRepository.findById(achievementId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (achievement != null && user != null) {
            user.addAchievement(achievement);
            userRepository.save(user);
        }
    }

    public void removeAchievementFromUser(Long achievementtId, Long userId) {
        Achievement achievement = achievementRepository.findById(achievementtId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (achievement != null && user != null) {
            user.removeAchievement(achievement);
            userRepository.save(user);
        }
    }
}

