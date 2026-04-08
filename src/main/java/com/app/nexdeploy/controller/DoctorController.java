package com.app.nexdeploy.controller;

import com.app.nexdeploy.service.IDoctorService;
import com.app.nexdeploy.model.request.DoctorRequest;
import com.app.nexdeploy.model.response.DoctorResponse;
import com.app.nexdeploy.service.IDoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doctors")
public class DoctorController {

  private final IDoctorService doctorService;

  @GetMapping
  public List<DoctorResponse> doctorList() {
    log.info("Fetching all doctors");

    return doctorService.findAllDoctors();
  }

  @PostMapping
  public ResponseEntity<Object> save(
    @Valid @RequestBody DoctorRequest doctorRequest,
    BindingResult bindingResult
  ) {
    log.info("Creating doctor: {}", doctorRequest);

    if (bindingResult.hasErrors()) {
      log.info("An error has occurred: {}", bindingResult.hasErrors());

      List<String> errors = bindingResult.getFieldErrors().stream()
        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
        .collect(Collectors.toList());

      return ResponseEntity.badRequest().body(errors);
    }

    DoctorResponse doctorSaved = doctorService.save(doctorRequest);

    return ResponseEntity.status(HttpStatus.CREATED).body(doctorSaved);
  }

  @GetMapping("/{id}")
  private ResponseEntity<DoctorResponse> findById(@PathVariable Long id) {
    log.info("Fetching doctor by id: {}", id);

    return new ResponseEntity<>(doctorService.getById(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  private ResponseEntity<?> update(
    @PathVariable Long id,
    @Valid @RequestBody DoctorRequest doctorRequest,
    BindingResult bindingResult
  ) {
    log.info("Updating doctor by id: {} {}", id, doctorRequest);

    if (bindingResult.hasErrors()) {
      log.info("An error has occurred: {}", bindingResult.hasErrors());

      List<String> errors = bindingResult.getFieldErrors().stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .collect(Collectors.toList());

      return ResponseEntity.badRequest().body(errors);
    }

    DoctorResponse doctorUpdated = doctorService.update(id, doctorRequest);

    return ResponseEntity.status(HttpStatus.CREATED).body(doctorUpdated);
  }

  @DeleteMapping("/{id}")
  private ResponseEntity<?> delete(@PathVariable Long id) {
    log.info("Deleting doctor with id: {}", id);

    doctorService.delete(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }


}
