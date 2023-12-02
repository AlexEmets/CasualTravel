package com.casualTravel.restservice.service;

import com.casualTravel.restservice.dto.AnswerDTO;
import com.casualTravel.restservice.dto.AuthenticationRequest;
import com.casualTravel.restservice.dto.AuthenticationResponse;
import com.casualTravel.restservice.dto.RegisterRequest;
import com.casualTravel.restservice.models.Achievement;
import com.casualTravel.restservice.models.Interest;
import com.casualTravel.restservice.models.User;
import com.casualTravel.restservice.repository.AchievementRepository;
import com.casualTravel.restservice.repository.UserPlaceRepository;
import com.casualTravel.restservice.repository.UserRepository;
import com.casualTravel.restservice.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.casualTravel.restservice.models.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final InterestRepository interestRepository;
    private final AchievementRepository achievementRepository;
    private final UserPlaceRepository userPlaceRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final InterestService interestService;
    private final AuthenticationManager authenticationManager;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User addInterestsByAnswers(User user, List<AnswerDTO> answers) {
        Map<Long, Long> interestIdCounts = answers.stream()
                .collect(Collectors.groupingBy(AnswerDTO::getInterestId, Collectors.counting()));

        long totalAnswers = answers.size();

        // Оновити поле interests користувача
        Map<Interest, Double> updatedInterests = new HashMap<>();
        interestIdCounts.forEach((interestId, count) -> {
            // Отримати Interest за interestId
            Interest interest = interestService.getInterestById(interestId).orElse(null);

            if (interest != null) {
                // Обчислити вагу для даного Interest
                double weight = (double) count / totalAnswers;

                // Додати в оновлену Map
                updatedInterests.put(interest, weight);
            }
        });

        // Оновити поле interests користувача
        user.setInterests(updatedInterests);
        userRepository.save(user);
        return user;
    }

    public void addInterestToUser(Long userId, Long interestId) {
        User user = userRepository.findById(userId).orElse(null);
        Interest interest = interestRepository.findById(interestId).orElse(null);

        if (user != null && interest != null) {
            user.addInterest(interest);
            userRepository.save(user);
        }
    }

    public void removeInterestFromUser(Long userId, Long interestId) {
        User user = userRepository.findById(userId).orElse(null);
        Interest interest = interestRepository.findById(interestId).orElse(null);

        if (user != null && interest != null) {
            user.removeInterest(interest);
            userRepository.save(user);
        }
    }

    public void addAchievementToUser(Long userId, Long achievementId) {
        User user = userRepository.findById(userId).orElse(null);
        Achievement achievement = achievementRepository.findById(achievementId).orElse(null);

        if (user != null && achievement != null) {
            user.addAchievement(achievement);
            userRepository.save(user);
        }
    }

    public void removeAchievementFromUser(Long userId, Long achievementId) {
        User user = userRepository.findById(userId).orElse(null);
        Achievement achievement = achievementRepository.findById(achievementId).orElse(null);

        if (user != null && achievement != null) {
            user.removeAchievement(achievement);
            userRepository.save(user);
        }
    }

    public void addPlaceToUser(Place place, User user, LocalDateTime timeSpent) {
        // Створюємо новий об'єкт UserPlace з вказаним користувачем, місцем та часом
        UserPlace userPlace = new UserPlace(user, place, timeSpent);

        // Зберігаємо зв'язок у репозиторії
        userPlaceRepository.save(userPlace);
    }

    public void removePlaceFromUser(Place place, User user) {
        // Знаходимо UserPlace за вказаним користувачем та місцем
        Optional<UserPlace> userPlaceOptional = userPlaceRepository.findByUserAndPlace(user, place);

        // Видаляємо UserPlace, якщо знайдено
        userPlaceOptional.ifPresent(userPlaceRepository::delete);
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public AuthenticationResponse register(RegisterRequest request) throws Exception {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        //var refreshToken = jwtService.generateRefreshToken(user);
        //saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                //.refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(user);
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                //.refreshToken(refreshToken)
                .build();
    }
}