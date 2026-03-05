package bekezhan.io.task5.service;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProbeService {
    private final JdbcTemplate jdbcTemplate;

    public ProbeService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initTable() {
        jdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS service_probe_data (
                    id BIGINT PRIMARY KEY,
                    value VARCHAR(255) NOT NULL
                )
                """);
        jdbcTemplate.update("""
                INSERT INTO service_probe_data (id, value)
                VALUES (1, 'db-ok')
                ON CONFLICT (id) DO NOTHING
                """);
    }

    public String slowSelect() {
        jdbcTemplate.execute("SELECT pg_sleep(2)");
        return jdbcTemplate.queryForObject(
                "SELECT value FROM service_probe_data WHERE id = 1",
                String.class
        );
    }
}
