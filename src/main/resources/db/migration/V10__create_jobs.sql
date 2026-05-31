CREATE TABLE jobs (
    id            BIGSERIAL PRIMARY KEY,
    job_code      VARCHAR(30)  NOT NULL UNIQUE,
    title         VARCHAR(200) NOT NULL,
    class_id      BIGINT       NOT NULL REFERENCES classes(id),
    department_id BIGINT       NOT NULL REFERENCES departments(id),
    status        VARCHAR(20)  NOT NULL DEFAULT 'ACTIVE',
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_job_status CHECK (status IN ('ACTIVE','INACTIVE'))
);

CREATE INDEX idx_jobs_class      ON jobs(class_id);
CREATE INDEX idx_jobs_department ON jobs(department_id);

INSERT INTO jobs (job_code, title, class_id, department_id) VALUES
    ('J001', 'Junior Software Engineer',    1, 1),
    ('J002', 'Software Engineer',           2, 1),
    ('J003', 'Senior Software Engineer',    3, 1),
    ('J004', 'Junior Data Engineer',        6, 2),
    ('J005', 'Data Engineer',               7, 2),
    ('J006', 'Compensation Analyst',        6, 3);
