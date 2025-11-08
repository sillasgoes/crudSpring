package com.sillas.sillas.service;

import com.sillas.sillas.entities.Role;
import com.sillas.sillas.entities.User;
import com.sillas.sillas.entities.dto.RegisterRequest;
import com.sillas.sillas.entities.dto.RegisterResponse;
import com.sillas.sillas.repository.RoleRepository;
import com.sillas.sillas.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final RoleRepository  roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public RegisterResponse register(RegisterRequest user){

        User tUser = new User();
        var roles = roleRepository.findAllById(user.roles());

        userRepository.findByUsername(user.username())
                .ifPresentOrElse(
                (u) -> { throw new ResponseStatusException(HttpStatus.CONFLICT);},
                () -> {
                    tUser.setUsername(user.username());
                    tUser.setPassword(passwordEncoder.encode(user.password()));
                    tUser.setRoles(Set.copyOf(roles));
                });

        userRepository.save(tUser);

        return new RegisterResponse(tUser.getUsername());
    }
}
