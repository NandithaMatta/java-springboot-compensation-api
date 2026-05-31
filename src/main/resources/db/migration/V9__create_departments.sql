CREATE TABLE departments (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(200) NOT NULL,
    code        VARCHAR(20)  NOT NULL UNIQUE,
    division_id BIGINT       NOT NULL REFERENCES divisions(id),
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_departments_division ON departments(division_id);

INSERT INTO departments (name, code, division_id) VALUES
    ('Backend Engineering',  'BE',      1),
    ('Data Platform',        'DP',      1),
    ('HR & Compensation',    'HR-COMP', 2),
    ('Software Development', 'SD',      3);
