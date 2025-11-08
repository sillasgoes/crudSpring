package com.sillas.sillas.entities.dto;

import com.sillas.sillas.entities.Role;
import jakarta.persistence.SecondaryTable;

import java.util.Set;

public record UserDto(Long user_id, String username, Set<Role> role) {
}
