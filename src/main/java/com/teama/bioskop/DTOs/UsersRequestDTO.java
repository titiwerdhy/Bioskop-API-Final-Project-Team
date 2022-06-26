package com.teama.bioskop.DTOs;

import java.time.LocalDateTime;

import com.teama.bioskop.Models.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersRequestDTO {
    private Integer userId;

    private String username;

    private String emailAddress;

    private String password;

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

    /***
     * Save new user data
     * @return requirements to create new data of user
     */
    public Users convertToUsers(){
        return Users.builder()
            .username(this.username)
            .emailAddress(this.emailAddress)
            .password(this.password)
            .build();
    }

    public Users updateUsers(){
        return Users.builder()
            .userId(this.userId)
            .username(this.username)
            .emailAddress(this.emailAddress)
            .password(this.password)
            .createdAt(this.createdAt)
            .updatedAt(this.updatedAt)
            .build();
    }
}
