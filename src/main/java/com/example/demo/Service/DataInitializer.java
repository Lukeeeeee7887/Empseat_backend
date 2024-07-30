package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        // 先刪除已存在的存儲過程
        jdbcTemplate.execute("DROP PROCEDURE IF EXISTS AssignSeat");
        
        // 創建新的存儲過程
        String procedure = "CREATE PROCEDURE AssignSeat( IN p_emp_id CHAR(5), IN p_floor_no INT, IN p_seat_no INT ) BEGIN "
            + "DECLARE v_floor_seat_seq INT; "
            + "DECLARE v_current_seat INT; "
            + "START TRANSACTION; "
            + "SELECT FLOOR_SEAT_SEQ INTO v_floor_seat_seq "
            + "FROM Seatingchart "
            + "WHERE FLOOR_NO = p_floor_no AND SEAT_NO = p_seat_no "
            + "AND FLOOR_SEAT_SEQ NOT IN (SELECT FLOOR_SEAT_SEQ FROM Employee WHERE FLOOR_SEAT_SEQ IS NOT NULL) "
            + "FOR UPDATE; "
            + "IF v_floor_seat_seq IS NOT NULL THEN "
            + "SELECT FLOOR_SEAT_SEQ INTO v_current_seat FROM Employee WHERE EMP_ID = p_emp_id; "
            + "IF v_current_seat IS NOT NULL THEN "
            + "UPDATE Employee SET FLOOR_SEAT_SEQ = NULL WHERE EMP_ID = p_emp_id; "
            + "END IF; "
            + "UPDATE Employee SET FLOOR_SEAT_SEQ = v_floor_seat_seq WHERE EMP_ID = p_emp_id; "
            + "COMMIT; "
            + "SELECT 'Seat assigned successfully' AS result; "
            + "ELSE "
            + "ROLLBACK; "
            + "SELECT 'Seat is not available' AS result; "
            + "END IF; "
            + "END";
        
        jdbcTemplate.execute(procedure);
        
     // 執行存儲過程以設置初始值
        jdbcTemplate.execute("CALL AssignSeat('00001', 1, 3)");
        jdbcTemplate.execute("CALL AssignSeat('00002', 2, 3)");
        jdbcTemplate.execute("CALL AssignSeat('00003', 3, 1)");
        jdbcTemplate.execute("CALL AssignSeat('00004', 3, 2)");
    }
}
