CREATE TABLE job_career_levels (
    id               BIGSERIAL PRIMARY KEY,
    name             VARCHAR(100) NOT NULL,
    code             VARCHAR(20)  NOT NULL UNIQUE,
    level_order      INT          NOT NULL,
    career_group_id  BIGINT       NOT NULL REFERENCES job_career_groups(id),
    created_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO job_career_levels (name, code, level_order, career_group_id) VALUES
    ('Junior',    'IC1', 1, 1),
    ('Mid',       'IC2', 2, 1),
    ('Senior',    'IC3', 3, 1),
    ('Staff',     'IC4', 4, 1),
    ('Principal', 'IC5', 5, 1),
    ('Manager',   'M1',  1, 2),
    ('Sr Manager','M2',  2, 2),
    ('Director',  'M3',  3, 2),
    ('VP',        'E1',  1, 3),
    ('SVP',       'E2',  2, 3);
