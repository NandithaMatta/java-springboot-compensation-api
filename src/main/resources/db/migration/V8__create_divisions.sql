CREATE TABLE divisions (
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(200) NOT NULL,
    code            VARCHAR(20)  NOT NULL UNIQUE,
    organization_id BIGINT       NOT NULL REFERENCES organizations(id),
    created_at      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_divisions_org ON divisions(organization_id);

INSERT INTO divisions (name, code, organization_id) VALUES
    ('Technology',  'ACME-TECH', 1),
    ('Corporate',   'ACME-CORP', 1),
    ('Engineering', 'GTL-ENG',   2);
