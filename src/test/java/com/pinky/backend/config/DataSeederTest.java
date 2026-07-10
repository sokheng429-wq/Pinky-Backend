package com.pinky.backend.config;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pinky.backend.repository.ProductRepository;
import com.pinky.backend.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class DataSeederTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private DataSeeder dataSeeder;

    @Test
    void runShouldNotFailWhenDatabaseIsUnavailable() {
        when(userRepository.count()).thenThrow(new RuntimeException("DB unavailable"));

        assertDoesNotThrow(() -> dataSeeder.run());

        verify(productRepository, never()).saveAll(anyList());
        verify(userRepository, never()).save(any());
    }
}
