CREATE TABLE classes (
    id              BIGSERIAL PRIMARY KEY,
    code            VARCHAR(30)   NOT NULL UNIQUE,
    job_family_id   BIGINT        NOT NULL REFERENCES job_families(id),
    career_level_id BIGINT        NOT NULL REFERENCES job_career_levels(id),
    min_salary      DECIMAL(15,2) NOT NULL,
    max_salary      DECIMAL(15,2) NOT NULL,
    currency        VARCHAR(3)    NOT NULL DEFAULT 'GBP',
    created_at      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_class_salary CHECK (max_salary > min_salary),
    CONSTRAINT uq_family_level  UNIQUE (job_family_id, career_level_id)
);

CREATE INDEX idx_classes_family  ON classes(job_family_id);
CREATE INDEX idx_classes_level   ON classes(career_level_id);

INSERT INTO classes (code, job_family_id, career_level_id, min_salary, max_salary) VALUES
    ('SWE-IC1', 1, 1, 28000,  42000),
    ('SWE-IC2', 1, 2, 42000,  65000),
    ('SWE-IC3', 1, 3, 65000,  90000),
    ('SWE-IC4', 1, 4, 90000,  120000),
    ('SWE-IC5', 1, 5, 120000, 160000),
    ('DE-IC1',  2, 1, 30000,  45000),
    ('DE-IC2',  2, 2, 45000,  70000),
    ('DE-IC3',  2, 3, 70000,  95000);
