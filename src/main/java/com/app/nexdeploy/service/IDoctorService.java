package com.app.nexdeploy.service;

import com.app.nexdeploy.model.request.DoctorRequest;
import com.app.nexdeploy.model.response.DoctorResponse;

import java.util.List;

public interface IDoctorService {

  public List<DoctorResponse> findAllDoctors();

  public DoctorResponse save(DoctorRequest doctorRequest);

  public DoctorResponse getById(Long id);

  public List<DoctorResponse> getBySpecialty(String specialty);

  public DoctorResponse getByName(String name);

  public DoctorResponse update(Long id, DoctorRequest doctorRequest);

  public void delete(Long id);
}
