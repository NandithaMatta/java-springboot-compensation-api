CREATE TABLE employees (
    id              BIGSERIAL PRIMARY KEY,
    employee_number VARCHAR(20)   NOT NULL UNIQUE,
    first_name      VARCHAR(100)  NOT NULL,
    last_name       VARCHAR(100)  NOT NULL,
    email           VARCHAR(255)  NOT NULL UNIQUE,
    job_id          BIGINT        NOT NULL REFERENCES jobs(id),
    department_id   BIGINT        NOT NULL REFERENCES departments(id),
    basic_salary    DECIMAL(15,2) NOT NULL,
    hire_date       DATE          NOT NULL,
    status          VARCHAR(20)   NOT NULL DEFAULT 'ACTIVE',
    created_at      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_emp_status CHECK (status IN ('ACTIVE','INACTIVE','ON_LEAVE'))
);

CREATE INDEX idx_employees_job        ON employees(job_id);
CREATE INDEX idx_employees_department ON employees(department_id);
CREATE INDEX idx_employees_status     ON employees(status);

INSERT INTO employees (employee_number, first_name, last_name, email, job_id, department_id, basic_salary, hire_date) VALUES
    ('EMP001', 'Alice', 'Smith',  'alice.smith@acme.com',  2, 1, 55000, '2021-03-15'),
    ('EMP002', 'Bob',   'Jones',  'bob.jones@acme.com',    3, 1, 72000, '2019-07-01'),
    ('EMP003', 'Carol', 'White',  'carol.white@acme.com',  5, 2, 60000, '2022-01-10'),
    ('EMP004', 'David', 'Brown',  'david.brown@acme.com',  6, 3, 48000, '2020-09-20');
