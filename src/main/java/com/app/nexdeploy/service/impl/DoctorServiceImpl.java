package com.app.nexdeploy.service.impl;

import com.app.nexdeploy.constant.Message;
import com.app.nexdeploy.entity.DoctorEntity;
import com.app.nexdeploy.exception.InternalServerException;
import com.app.nexdeploy.exception.ResourceNotFoundException;
import com.app.nexdeploy.model.request.DoctorRequest;
import com.app.nexdeploy.model.response.DoctorResponse;
import com.app.nexdeploy.repository.UserRepository;
import com.app.nexdeploy.service.IDoctorService;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Slf4j
@RequiredArgsConstructor
@Service
public class DoctorServiceImpl implements IDoctorService {

  private final UserRepository userRepository;
  private final UserRepository doctorRepository;

  private final ModelMapper modelMapper;

  @Override
  public List<DoctorResponse> findAllDoctors() {
    log.info("DoctorServiceImpl - find all");

    List<DoctorEntity> doctors = doctorRepository.findAll();

    return doctors.stream()
      .map(doctorEntity -> modelMapper.map(doctorEntity, DoctorResponse.class))
      .collect(Collectors.toList());
  }

  @Override
  public DoctorResponse save(DoctorRequest doctorRequest) {
    log.info("DoctorServiceImpl - save: {}", doctorRequest);

    try {
      DoctorEntity doctorEntity = new DoctorEntity();
      doctorEntity.setUser(null);
      doctorEntity.setNumLicencia("asdas");
      doctorEntity.setSpecialty("Cirujano");
      doctorEntity.setWorkSchedule("1023921");

      DoctorEntity saved = doctorRepository.save(doctorEntity);

      return modelMapper.map(saved, DoctorResponse.class);
    } catch (Exception e) {
      log.error("Hubo un error al crear el doctor : {}", e.getMessage());
      throw new InternalServerException(Message.SAVE_ERROR + "the doctor");
    }
  }

  @Override
  public DoctorResponse getById(Long id) {
    log.info("DoctorServiceImpl - find doctor by id {}", id);

    Optional<DoctorEntity> doctorOptional = doctorRepository.findById(id);

    return doctorOptional
      .map(doctorEntity -> modelMapper.map(doctorEntity, DoctorResponse.class))
      .orElseThrow(() -> new ResourceNotFoundException("Doctor no encontrado con ID: " + id));
  }

  @Override
  public List<DoctorResponse> getBySpecialty(String specialty) {
    return List.of();
  }

  @Override
  public DoctorResponse getByName(String name) {
    return null;
  }

  @Override
  public DoctorResponse update(Long id, DoctorRequest doctorRequest) {
    log.info("DoctorServiceImpl - update: {} {}", id, doctorRequest);

    try {
      Optional<DoctorEntity> doctorOptional = doctorRepository.findById(id);

      if (doctorOptional.isPresent()) {
        DoctorEntity doctorEntity = doctorOptional.get();
        modelMapper.map(doctorRequest, doctorEntity);

        DoctorEntity doctorUpdated = doctorRepository.save(doctorEntity);
        return modelMapper.map(doctorUpdated, DoctorResponse.class);
      } else {
        throw new ResourceNotFoundException("No se encontró el doctor con ID: " + id);
      }
    } catch (DataAccessException e) {
      log.error("Hubo un error al actualizar el doctor: {}", e.getMessage());
      throw new InternalServerException(Message.UPDATE_ERROR + " the doctor with ID: " + id, e);
    }
  }

  @Override
  public void delete(Long id) {
    log.info("DoctorServiceImpl - delete: {}", id);

    try {
      Optional<DoctorEntity> doctorOptional = doctorRepository.findById(id);

      if (doctorOptional.isPresent()) {
        doctorRepository.deleteById(id);
      } else {
        throw new ResourceNotFoundException("No se encontró el doctor con ID: " + id);
      }
    } catch (DataAccessException e) {
      log.error("Hubo un error al eliminar el doctor: {}", e.getMessage());
      throw new InternalServerException("Error al eliminar el doctor con ID: " + id);
    }
  }
}
