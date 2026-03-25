-- V1: Initial schema
-- Creates the core entries table for the personal assistant

CREATE TABLE IF NOT EXISTS entries (
    id          BIGSERIAL       PRIMARY KEY,
    content     TEXT            NOT NULL,
    source      VARCHAR(50)     NOT NULL,   -- e.g. 'whatsapp', 'api', 'web'
    created_at  TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMPTZ     NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_entries_created_at ON entries (created_at DESC);
