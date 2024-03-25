package com.dmdev.service;

import com.dmdev.dao.UserDao;
import com.dmdev.dto.UserDto;
import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import com.dmdev.mapper.CreateUserMapper;
import com.dmdev.mapper.UserMapper;
import com.dmdev.validator.CreateUserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private CreateUserValidator createUserValidator;
    @Mock
    private UserDao userDao;
    @Mock
    private CreateUserMapper createUserMapper;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    @Test
    void loginSuccess() {
        User user = User.builder()
                .id(99)
                .name("Ivan")
                .email("test@gmail.com")
                .password("123")
                .birthday(LocalDate.of(2000,1,1))
                .role(Role.USER)
                .gender(Gender.MALE)
                .build();

        UserDto userDto = UserDto.builder()
                .id(99)
                .name("Ivan")
                .email("test@gmail.com")
                .gender(Gender.MALE)
                .role(Role.USER)
                .birthday(LocalDate.of(2000,1,1))
                .build();
        doReturn(Optional.of(user)).when(userDao).findByEmailAndPassword(user.getEmail(), user.getPassword());
        doReturn(userDto).when(userMapper).map(user);
        Optional <UserDto> actualResult = userService.login(user.getEmail(), user.getPassword());
        org.assertj.core.api.Assertions.assertThat(actualResult).isPresent();
        org.assertj.core.api.Assertions.assertThat(actualResult.get()).isEqualTo(userDto);
    }

    @Test
    void loginFailed() {
    }
}