package com.app.nexdeploy.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DoctorResponse {

  private Long id;

  private String name;

  private String firstName;

  private String secondName;

  private String specialty;

  private String gender;

  private String telephone;

  private String workSchedule;
}
