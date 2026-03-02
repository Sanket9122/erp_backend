-- Insert test users for API testing
INSERT INTO users (name, email, password, role) VALUES
('Admin User', 'admin@erp.com', 'admin123', 'ADMIN'),
('Manager User', 'manager@erp.com', 'manager123', 'MANAGER'),
('John Developer', 'john@erp.com', 'john123', 'USER'),
('Sarah Developer', 'sarah@erp.com', 'sarah123', 'USER'),
('Mike Developer', 'mike@erp.com', 'mike123', 'USER');

-- Insert test projects
INSERT INTO projects (name, status) VALUES 
('ERP System Development', 'IN_PROGRESS'),
('Mobile App Project', 'NOT_STARTED'),
('Website Redesign', 'COMPLETED');

-- Insert test accessories
INSERT INTO accessories (name, serial_number, is_available) VALUES
('Laptop Dell XPS', 'DLX-001', false),
('Monitor LG 27"', 'MON-002', true),
('Keyboard Mechanical', 'KEY-003', true),
('Mouse Wireless', 'MOU-004', true),
('Docking Station', 'DOC-005', false);

-- Assign users to projects
UPDATE users SET project_id = 1 WHERE email IN ('john@erp.com', 'sarah@erp.com');
UPDATE users SET project_id = 2 WHERE email = 'mike@erp.com';

-- Assign accessories to projects
# UPDATE accessories SET assigned_project_id = 1 WHERE serial_number IN ('DLX-001', 'MON-002');
# UPDATE accessories SET assigned_project_id = 2 WHERE serial_number = 'DOC-005';


-- Assign Laptop and Monitor to Project 1
UPDATE accessories SET project_id = 1 WHERE serial_number IN ('DLX-001', 'MON-002');

-- Assign Docking Station to Project 2
UPDATE accessories SET project_id = 2 WHERE serial_number = 'DOC-005';

-- Update availability status
UPDATE accessories SET is_available = false WHERE project_id IS NOT NULL;

-- Check assignments
SELECT a.name, a.serial_number, a.is_available, p.name as project_name
FROM accessories a
         LEFT JOIN projects p ON a.project_id = p.project_id;