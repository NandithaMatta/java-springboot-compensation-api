CREATE TABLE organizations (
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(200) NOT NULL UNIQUE,
    code       VARCHAR(20)  NOT NULL UNIQUE,
    country    VARCHAR(100),
    currency   VARCHAR(3)   NOT NULL DEFAULT 'GBP',
    status     VARCHAR(20)  NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_org_status CHECK (status IN ('ACTIVE','INACTIVE'))
);

INSERT INTO organizations (name, code, country, currency) VALUES
    ('Acme Corporation',  'ACME',  'United Kingdom', 'GBP'),
    ('Global Tech Ltd',   'GTL',   'United Kingdom', 'GBP');
