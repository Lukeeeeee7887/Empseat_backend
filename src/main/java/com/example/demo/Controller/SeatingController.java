package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Seatingchart;
import com.example.demo.Service.SeatingService;

@RestController
@RequestMapping("/api/seating")
public class SeatingController {
    @Autowired
    private SeatingService seatingService;

    @GetMapping("/floor/{floorNo}")
    public List<Seatingchart> getSeatingByFloor(@PathVariable Integer floorNo) {
        return seatingService.getSeatingByFloor(floorNo);
    }

    @PostMapping("/assign")
    public ResponseEntity<String> assignSeat(@RequestParam String empId, @RequestParam Integer floorSeatSeq) {
        boolean success = seatingService.assignSeat(empId, floorSeatSeq);
        return success ? ResponseEntity.ok("Seat assigned successfully") : ResponseEntity.badRequest().body("Failed to assign seat");
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearSeat(@RequestParam Integer floorSeatSeq) {
        boolean success = seatingService.clearSeat(floorSeatSeq);
        return success ? ResponseEntity.ok("Seat cleared successfully") : ResponseEntity.badRequest().body("Failed to clear seat");
    }
}
