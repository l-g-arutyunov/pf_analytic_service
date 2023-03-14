package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.UserDto;
import com.devlife.pf_sql_controller.entity.User;
import com.devlife.pf_sql_controller.mapper.UserMapper;
import com.devlife.pf_sql_controller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    public Long addUser(UserDto user) {
        User saveUser = userRepository.save(mapper.convertToEntity(user));
        if (saveUser != null) {
            return saveUser.getId();
        }
        return null;
    }

    public UserDto getUser(Long id) {
        User user = userRepository.getById(id);
        return mapper.convertToDto(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        return usersList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteUserById(Long id) {
        userRepository.deleteById(id);
        return !userRepository.existsById(id);
    }

}
