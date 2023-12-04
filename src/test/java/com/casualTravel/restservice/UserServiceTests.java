package com.casualTravel.restservice;

import com.casualTravel.restservice.dto.AuthenticationRequest;
import com.casualTravel.restservice.dto.AuthenticationResponse;
import com.casualTravel.restservice.dto.RegisterRequest;
import com.casualTravel.restservice.models.User;
import com.casualTravel.restservice.repository.UserRepository;
import com.casualTravel.restservice.service.JwtService;
import com.casualTravel.restservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void testRegister() throws Exception {
        // Підготовка даних для тесту
        RegisterRequest registerRequest = new RegisterRequest("testUser", "test@example.com", "password123");

        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        Mockito.when(jwtService.generateToken(Mockito.any())).thenReturn("mockedJwtToken");

        // Виклик сервісного методу
        AuthenticationResponse result = userService.register(registerRequest);

        // Перевірка результату
        assertEquals("mockedJwtToken", result.getToken());
    }

    @Test
    void testAuthenticate() throws Exception {
        // Підготовка даних для тесту
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("test@example.com", "password123");

        User user = User.builder()
                .username("testUser")
                .email(authenticationRequest.getEmail())
                .password(passwordEncoder.encode(authenticationRequest.getPassword()))
                .build();

        Mockito.when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(null); // Мокуємо успішну аутентифікацію

        Mockito.when(userRepository.findByEmail(authenticationRequest.getEmail()))
                .thenReturn(java.util.Optional.ofNullable(user));

        Mockito.when(jwtService.generateToken(Mockito.any())).thenReturn("mockedJwtToken");

        // Виклик сервісного методу
        AuthenticationResponse result = userService.authenticate(authenticationRequest);

        // Перевірка результату
        assertEquals("mockedJwtToken", result.getToken());
    }

    @Test
    void testAuthenticateUsernameNotFoundException() {
        // Підготовка даних для тесту
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("nonexistent@example.com", "password123");

        Mockito.when(authenticationManager.authenticate(Mockito.any()))
                .thenThrow(new UsernameNotFoundException("User not found"));

        // Виклик сервісного методу та перевірка виключення
        assertThrows(UsernameNotFoundException.class, () -> userService.authenticate(authenticationRequest));
    }

}

