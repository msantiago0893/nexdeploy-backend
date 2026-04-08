package com.app.nexdeploy.entity;

import com.app.nexdeploy.entity.enums.Role;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name="users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String name;
	private String firstName;
	private String secondName;
	private String cel;

	@Enumerated(EnumType.STRING)
	private Role rol;

	private String direccion;
}