package com.sillas.sillas.service;

import com.sillas.sillas.config.TokenConfig;
import com.sillas.sillas.entities.Role;
import com.sillas.sillas.entities.dto.UserDto;
import com.sillas.sillas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDto> allUsers() {

        Role role = new Role();
         return userRepository.findAll()
                 .stream()
                 .map((user) ->
                            new UserDto(
                             user.getUser_id(),
                             user.getUsername(),
                             user.getRoles())).toList();
    }

    public Page<UserDto> allUserPage(int page, int pageSize){
        return userRepository
                .findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC))
                .map((user) ->
                        new UserDto(user.getUser_id(),
                                user.getUsername(),
                                user.getRoles()));
    }
}
