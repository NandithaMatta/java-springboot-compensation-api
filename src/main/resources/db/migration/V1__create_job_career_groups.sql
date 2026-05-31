CREATE TABLE job_career_groups (
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL UNIQUE,
    code       VARCHAR(20)  NOT NULL UNIQUE,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO job_career_groups (name, code) VALUES
    ('Individual Contributor', 'IC'),
    ('Management',             'MGT'),
    ('Executive',              'EXEC');
