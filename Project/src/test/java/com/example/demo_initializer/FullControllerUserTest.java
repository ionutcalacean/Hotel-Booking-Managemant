package com.example.demo_initializer;

import com.example.demo_initializer.Controllers.FullController;
import com.example.demo_initializer.Controllers.UserController;
import com.example.demo_initializer.Repositories.UserRepository;
import com.example.demo_initializer.components.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class FullControllerUserTest {
    @InjectMocks
    FullController fullController;

    @Mock
    UserRepository userRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteAllUsers()
    {
       // when(userRepository.deleteAll()).thenReturn(null);
        doNothing().when(userRepository).deleteAll();
        User user = fullController.deleteAllUsers();

        assertEquals(null,user);
    }

}
