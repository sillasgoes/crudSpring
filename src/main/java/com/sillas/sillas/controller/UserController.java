package com.sillas.sillas.controller;

import com.sillas.sillas.entities.dto.UserDto;
import com.sillas.sillas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/alluser")
    public ResponseEntity<List<UserDto>> allUser() {
        var list = userService.allUsers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/alluserpage")
    public ResponseEntity<Page<UserDto>> allUserPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                                     @RequestParam(name = "pageSize", defaultValue = "10")int pageSize) {

        var pageDto = userService.allUserPage(page, pageSize);
        return ResponseEntity.ok(pageDto);
    }

}
