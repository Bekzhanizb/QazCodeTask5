package bekezhan.io.task5.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProbeService {
    private final JdbcTemplate jdbcTemplate;

    public ProbeService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String slowSelect() {
        jdbcTemplate.execute("SELECT pg_sleep(2)");
        return jdbcTemplate.queryForObject(
                "SELECT value FROM service_probe_data WHERE id = 1",
                String.class
        );
    }
}
