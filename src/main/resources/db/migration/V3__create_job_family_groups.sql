CREATE TABLE job_family_groups (
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL UNIQUE,
    code       VARCHAR(20)  NOT NULL UNIQUE,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO job_family_groups (name, code) VALUES
    ('Engineering',        'ENG'),
    ('Human Resources',    'HR'),
    ('Finance',            'FIN'),
    ('Product Management', 'PM'),
    ('Operations',         'OPS');
