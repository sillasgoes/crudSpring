package com.sillas.sillas.config;

import lombok.Builder;

@Builder
public record JWTUserData(Long userid, String username) {
}
