package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "EMP_ID", length = 5)
    private String empId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "FLOOR_SEAT_SEQ")
    private Seatingchart seat;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Seatingchart getSeat() {
		return seat;
	}

	public void setSeat(Seatingchart seat) {
		this.seat = seat;
	}
    
    
}
