CREATE TABLE job_families (
    id               BIGSERIAL PRIMARY KEY,
    name             VARCHAR(100) NOT NULL,
    code             VARCHAR(20)  NOT NULL UNIQUE,
    family_group_id  BIGINT       NOT NULL REFERENCES job_family_groups(id),
    created_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO job_families (name, code, family_group_id) VALUES
    ('Software Engineering',   'SWE',    1),
    ('Data Engineering',       'DE',     1),
    ('Infrastructure',         'INFRA',  1),
    ('Compensation & Benefits','COMP',   2),
    ('Talent Acquisition',     'TA',     2),
    ('Financial Planning',     'FP',     3),
    ('Product',                'PROD',   4),
    ('Business Operations',    'BIZOPS', 5);
