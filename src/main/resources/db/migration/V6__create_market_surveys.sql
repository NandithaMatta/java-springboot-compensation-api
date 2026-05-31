CREATE TABLE market_surveys (
    id            BIGSERIAL PRIMARY KEY,
    survey_name   VARCHAR(200)  NOT NULL,
    provider      VARCHAR(100)  NOT NULL,
    survey_year   INT           NOT NULL,
    class_id      BIGINT        NOT NULL REFERENCES classes(id),
    p25           DECIMAL(15,2),
    p50           DECIMAL(15,2),
    p75           DECIMAL(15,2),
    p90           DECIMAL(15,2),
    created_at    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_survey_class_year UNIQUE (provider, survey_year, class_id)
);

CREATE INDEX idx_surveys_class_year ON market_surveys(class_id, survey_year);
