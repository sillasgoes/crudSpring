package com.sillas.sillas.config.jwt;

import lombok.Builder;

@Builder
public record JWTUserData(Long userid, String username) {
}
