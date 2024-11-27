package com.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

