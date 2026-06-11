-- 1. Create 'departments' table
CREATE TABLE departments (
                             id BIGSERIAL PRIMARY KEY,
                             dept_name VARCHAR(100) NOT NULL,
                             created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 2. Create 'positions' table
CREATE TABLE positions (
                           id BIGSERIAL PRIMARY KEY,
                           position_name VARCHAR(100) NOT NULL,
                           created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 3. Create 'employees' table
CREATE TABLE employees (
                           id BIGSERIAL PRIMARY KEY,
                           keycloak_user_id UUID NOT NULL UNIQUE,
                           employee_code VARCHAR(50) NOT NULL UNIQUE,
                           first_name VARCHAR(100) NOT NULL,
                           last_name VARCHAR(100) NOT NULL,
                           email VARCHAR(150) NOT NULL UNIQUE,
                           phone VARCHAR(50),
                           department_id BIGINT,
                           position_id BIGINT,
                           manager_id BIGINT,
                           hire_date DATE NOT NULL,
                           active BOOLEAN DEFAULT TRUE,
                           created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    -- Relationships (Foreign Keys)
                           CONSTRAINT fk_employee_department FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE SET NULL,
                           CONSTRAINT fk_employee_position FOREIGN KEY (position_id) REFERENCES positions(id) ON DELETE SET NULL,
                           CONSTRAINT fk_employee_manager FOREIGN KEY (manager_id) REFERENCES employees(id) ON DELETE SET NULL
);

-- 4. Create 'leaves' table
CREATE TABLE leaves (
                        id BIGSERIAL PRIMARY KEY,
                        employee_id BIGINT NOT NULL,
                        leave_type VARCHAR(50) NOT NULL,
                        start_date DATE NOT NULL,
                        end_date DATE NOT NULL,
                        reason TEXT,
                        status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
                        approved_by BIGINT,
                        approved_at TIMESTAMP WITH TIME ZONE,
                        created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    -- Relationships (Foreign Keys)
                        CONSTRAINT fk_leave_employee FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE,
                        CONSTRAINT fk_leave_approver FOREIGN KEY (approved_by) REFERENCES employees(id) ON DELETE SET NULL
);

-- 5. Create 'audit_logs' table
CREATE TABLE audit_logs (
                            id BIGSERIAL PRIMARY KEY,
                            employee_id BIGINT,
                            action VARCHAR(100) NOT NULL,
                            entity_name VARCHAR(100) NOT NULL,
                            entity_id BIGINT,
                            details TEXT,
                            created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    -- Relationships (Foreign Keys)
                            CONSTRAINT fk_audit_employee FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE SET NULL
);

-- =========================================================================
-- CREATE INDEXES (Performance ပိုကောင်းစေရန် နှင့် Query မြန်ဆန်စေရန်)
-- =========================================================================

CREATE INDEX idx_employees_department_id ON employees(department_id);
CREATE INDEX idx_employees_manager_id ON employees(manager_id);
CREATE INDEX idx_leaves_employee_id ON leaves(employee_id);
CREATE INDEX idx_leaves_status ON leaves(status);
CREATE INDEX idx_audit_logs_action ON audit_logs(action);