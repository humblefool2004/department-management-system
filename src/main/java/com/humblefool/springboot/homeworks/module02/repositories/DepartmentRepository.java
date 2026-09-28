package com.humblefool.springboot.homeworks.module02.repositories;

import com.humblefool.springboot.homeworks.module02.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

}
