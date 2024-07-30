package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	@Query("SELECT e FROM Employee e WHERE e.seat IS NOT NULL")
    List<Employee> findAllWithSeats();
}
