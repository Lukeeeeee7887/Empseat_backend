package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Employee;
import com.example.demo.Model.Seatingchart;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Repository.SeatingChartRepository;

import jakarta.transaction.Transactional;

@Service
public class SeatingService {
    @Autowired
    private SeatingChartRepository seatingChartRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Seatingchart> getSeatingByFloor(Integer floorNo) {
        return seatingChartRepository.findByFloorNoOrderBySeatNo(floorNo);
    }

    @Transactional
    public boolean assignSeat(String empId, Integer floorSeatSeq) {
        Employee employee = employeeRepository.findById(empId).orElse(null);
        Seatingchart seat = seatingChartRepository.findById(floorSeatSeq).orElse(null);

        if (employee == null || seat == null || seat.getEmployee() != null) {
            return false;
        }

        // Clear previous seat if exists
        if (employee.getSeat() != null) {
            employee.getSeat().setEmployee(null);
        }

        employee.setSeat(seat);
        seat.setEmployee(employee);

        employeeRepository.save(employee);
        seatingChartRepository.save(seat);

        return true;
    }

    @Transactional
    public boolean clearSeat(Integer floorSeatSeq) {
        Seatingchart seat = seatingChartRepository.findById(floorSeatSeq).orElse(null);

        if (seat == null || seat.getEmployee() == null) {
            return false;
        }

        Employee employee = seat.getEmployee();
        employee.setSeat(null);
        seat.setEmployee(null);

        employeeRepository.save(employee);
        seatingChartRepository.save(seat);

        return true;
    }
}
