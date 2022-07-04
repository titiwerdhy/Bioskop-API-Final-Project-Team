package com.teama.bioskop.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.teama.bioskop.DTOs.UsersResponseDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String username;

    private String emailAddress;

    private String password;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UsersResponseDTO convertToResponse(){
        return UsersResponseDTO.builder()
            .userId(this.userId)
            .username(this.username)
            .emailAddress(this.emailAddress)
            .password(this.password)
            .createdAt(this.createdAt)
            .updatedAt(updatedAt)
            .build();
    }

    @Override
    public String toString() {
        return "\n Users [createdAt=" + createdAt + ", emailAddress=" + emailAddress + ", password=" + password
                + ", updatedAt=" + updatedAt + ", userId=" + userId + ", username=" + username + "]";
    }

}