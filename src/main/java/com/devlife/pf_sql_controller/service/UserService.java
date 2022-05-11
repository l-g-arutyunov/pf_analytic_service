package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.UserDto;
import com.devlife.pf_sql_controller.entity.User;
import com.devlife.pf_sql_controller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Long addUser(User user) {
        User saveUser = userRepository.save(user);
        if (saveUser != null) {
            return saveUser.getId();
        }
        return null;
    }

    public User getUser(Long id) {
        User user = userRepository.getById(id);
        return user;
    }

    public List<User> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        return usersList;
    }

    public Boolean deleteUserById(Long id) {
        userRepository.deleteById(id);
        return !userRepository.existsById(id);
    }

}
