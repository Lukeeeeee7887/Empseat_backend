package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Seatingchart")
public class Seatingchart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLOOR_SEAT_SEQ")
    private Integer floorSeatSeq;

    @Column(name = "FLOOR_NO", nullable = false)
    private Integer floorNo;

    @Column(name = "SEAT_NO", nullable = false)
    private Integer seatNo;

    @JsonBackReference
    @OneToOne(mappedBy = "seat")
    private Employee employee;

	public Integer getFloorSeatSeq() {
		return floorSeatSeq;
	}

	public void setFloorSeatSeq(Integer floorSeatSeq) {
		this.floorSeatSeq = floorSeatSeq;
	}

	public Integer getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(Integer floorNo) {
		this.floorNo = floorNo;
	}

	public Integer getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(Integer seatNo) {
		this.seatNo = seatNo;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
    
    
}
