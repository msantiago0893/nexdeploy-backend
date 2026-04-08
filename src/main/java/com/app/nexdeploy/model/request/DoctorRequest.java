package com.app.nexdeploy.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DoctorRequest {

  /*@NotBlank(message = "Nombre obligatorio")
  @Size(min = 3, max = 20, message = "El nombre debe de contener de 3 a 20 caracteres")
  @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü ]+$", message = "Nombre inválido")*/
  private String name;

  /*@NotBlank(message = "Apellido obligatorio")
  @Size(min = 3, max = 20, message = "El primer apellido debe contener de 3 a 20 caracteres")
  @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü ]+$", message = "Apellido inválido")*/
  private String firstName;

  /*@NotBlank(message = "Apellido obligatorio")
  @Size(min = 3, max = 20, message = "El segundo apellido debe contener de 3 a 20 caracteres")
  @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü ]+$", message = "Apellido inválido")*/
  private String secondName;

  //@Size(min = 3, max = 50, message = "La especialidad debe contener entre 3 y 50 caracteres")
  private String specialty;

  //@Pattern(regexp = "^[MF]$", message = "Género inválido")
  private String gender;

  //@Pattern(regexp = "^[0-9]{10}$", message = "Teléfono inválido")
  private String telephone;

  private String workSchedule;
}
