package com.app.nexdeploy.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name="doctors")
public class DoctorEntity {
    @Id
    private Long id;

    private String specialty;
    private String numLicencia;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;  // Relación con user

    private String workSchedule;
}

