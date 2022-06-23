package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.UserDto;
import com.devlife.pf_sql_controller.mapper.UserMapper;
import com.devlife.pf_sql_controller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class UserController {

    private final UserService service;


    @PutMapping("user")
    Long addUser(@RequestBody UserDto userDto) {
        return service.addUser(userDto);
    }

    @GetMapping("user/{id}")
    UserDto getUser(@PathVariable("id") Long id) {
        return service.getUser(id);
    }

    @GetMapping("user")
    List<UserDto> getAllUser() {
        return service.getAllUsers();
    }

    @DeleteMapping("user/{id}")
    Boolean deleteUserById(@PathVariable("id") Long id) {
        return service.deleteUserById(id);
    }

}
