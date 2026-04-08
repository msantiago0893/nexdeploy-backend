package com.app.nexdeploy.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name="patients")
public class PatientEntity {
	@Id
	private Long id;

	private Double peso;
	private Double altura;

	@OneToOne
	@JoinColumn(name = "usuario_id")
	private UserEntity user;  // Relación con user
}
