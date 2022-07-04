package com.teama.bioskop.DTOs;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsersResponseDTO {
    private Integer userId;

    private String username;

    private String emailAddress;

    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
