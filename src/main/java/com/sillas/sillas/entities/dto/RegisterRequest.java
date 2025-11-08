package com.sillas.sillas.entities.dto;

import com.sillas.sillas.entities.Role;

import java.util.List;
import java.util.Set;
public record RegisterRequest(String username, String password, List<Long> roles) {
}
