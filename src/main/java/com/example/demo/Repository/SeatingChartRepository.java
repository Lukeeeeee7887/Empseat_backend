package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Seatingchart;

public interface SeatingChartRepository extends JpaRepository<Seatingchart, Integer> {
	List<Seatingchart> findByFloorNoOrderBySeatNo(Integer floorNo);
}
