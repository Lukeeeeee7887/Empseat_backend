-- Clear existing data (if any)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE Employee;
TRUNCATE TABLE Seatingchart;
SET FOREIGN_KEY_CHECKS = 1;

-- Insert sample data into Seatingchart
INSERT INTO Seatingchart (FLOOR_NO, SEAT_NO) VALUES
(1, 1), (1, 2), (1, 3), (1, 4),
(2, 1), (2, 2), (2, 3), (2, 4),
(3, 1), (3, 2), (3, 3), (3, 4),
(4, 1), (4, 2), (4, 3), (4, 4);

-- Insert sample data into Employee
INSERT INTO Employee (EMP_ID, NAME, EMAIL) VALUES
('00001', 'John Doe', 'john.doe@example.com'),
('00002', 'Jane Smith', 'jane.smith@example.com'),
('00003', 'Bob Johnson', 'bob.johnson@example.com'),
('00004', 'Alice Brown', 'alice.brown@example.com'),
('00005', 'Charlie Davis', 'charlie.davis@example.com');

