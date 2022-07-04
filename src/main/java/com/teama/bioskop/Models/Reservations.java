package com.teama.bioskop.Models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.teama.bioskop.DTOs.ReservationsResponseDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    private Boolean isActive;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ReservationsResponseDTO convertToResponse(){
        return ReservationsResponseDTO.builder()
            .reservationId(this.reservationId)
            .userId(this.users.getUserId())
            .scheduleId(this.schedule.getScheduleId())
            .isActive(this.isActive)
            .createdAt(this.createdAt)
            .updatedAt(this.updatedAt)
            .build();
    }

    @Override
    public String toString() {
        return "\n Reservations [createdAt=" + createdAt + ", isActive=" + isActive + ", reservationId=" + reservationId
                + ", schedule=" + schedule + ", updatedAt=" + updatedAt + ", users=" + users + "]";
    }
}
