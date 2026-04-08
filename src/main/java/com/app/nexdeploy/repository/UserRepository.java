package com.app.nexdeploy.repository;

import com.app.nexdeploy.entity.DoctorEntity;
import com.app.nexdeploy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;

@Repository
public interface UserRepository extends JpaRepository<DoctorEntity, Long> {
}
