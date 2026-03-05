CREATE TABLE IF NOT EXISTS service_probe_data (
    id BIGINT PRIMARY KEY,
    value VARCHAR(255) NOT NULL
);

INSERT INTO service_probe_data (id, value)
VALUES (1, 'db-ok')
ON CONFLICT (id) DO NOTHING;
