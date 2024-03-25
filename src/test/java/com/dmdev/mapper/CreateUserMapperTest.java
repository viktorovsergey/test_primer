package com.dmdev.mapper;

import com.dmdev.dto.CreateUserDto;
import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CreateUserMapperTest {
private final CreateUserMapper mapper =  CreateUserMapper.getInstance();
    @Test
    void map() {
        CreateUserDto dto = CreateUserDto.builder()
                .name("Ivan")
                .email("test@gmail.com")
                .password("123")
                .birthday("2000-01-01")
                .role(Role.USER.name())
                .gender(Gender.MALE.name())
                .build();
        User actualResult = mapper.map(dto);
        User expectedResult = User.builder()
                .name("Ivan")
                .email("test@gmail.com")
                .password("123")
                .birthday(LocalDate.of(2000,1,1))
                .role(Role.USER)
                .gender(Gender.MALE)
                .build();
        assertThat(actualResult).isEqualTo(expectedResult);

    }
}