package com.example.schedulepj.repository;

import com.example.schedulepj.entity.ScheduleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 일정 생성
    public Schedule save(Schedule schedule) {
        String sql = "INSERT INTO schedules (todo, author, password, created_at, modified_at) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, schedule.getTodo());
            ps.setString(2, schedule.getAuthor());
            ps.setString(3, schedule.getPassword());
            ps.setTimestamp(4, Timestamp.valueOf(schedule.getCreatedAt()));
            ps.setTimestamp(5, Timestamp.valueOf(schedule.getModifiedAt()));
            return ps;
        }, keyHolder);

        Long generatedId = keyHolder.getKey().longValue();
        schedule.setId(generatedId);

        return schedule;
    }

    // 일정 ID로 조회
    public Optional<Schedule> findById(Long id) {
        String sql = "SELECT * FROM schedules WHERE id = ?";

        try {
            Schedule schedule = jdbcTemplate.queryForObject(sql, scheduleRowMapper(), id);
            return Optional.ofNullable(schedule);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // 모든 일정 조회 (필터링 기능 포함)
    public List<Schedule> findAll(String date, String author) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM schedules WHERE 1=1");

        if (date != null && !date.isEmpty()) {
            sqlBuilder.append(" AND DATE(modified_at) = ?");
        }

        if (author != null && !author.isEmpty()) {
            sqlBuilder.append(" AND author = ?");
        }

        sqlBuilder.append(" ORDER BY modified_at DESC");

        String sql = sqlBuilder.toString();

        if (date != null && !date.isEmpty() && author != null && !author.isEmpty()) {
            return jdbcTemplate.query(sql, scheduleRowMapper(), LocalDate.parse(date), author);
        } else if (date != null && !date.isEmpty()) {
            return jdbcTemplate.query(sql, scheduleRowMapper(), LocalDate.parse(date));
        } else if (author != null && !author.isEmpty()) {
            return jdbcTemplate.query(sql, scheduleRowMapper(), author);
        } else {
            return jdbcTemplate.query(sql, scheduleRowMapper());
        }
    }

    // 일정 수정
    public void update(Schedule schedule) {
        String sql = "UPDATE schedules SET todo = ?, author = ?, modified_at = ? WHERE id = ?";

        jdbcTemplate.update(sql,
                schedule.getTodo(),
                schedule.getAuthor(),
                Timestamp.valueOf(schedule.getModifiedAt()),
                schedule.getId());
    }

    // 일정 삭제
    public void delete(Long id) {
        String sql = "DELETE FROM schedules WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // 일정 객체 매핑을 위한 RowMapper
    private RowMapper<Schedule> scheduleRowMapper() {
        return (rs, rowNum) -> {
            Schedule schedule = new Schedule();
            schedule.setId(rs.getLong("id"));
            schedule.setTodo(rs.getString("todo"));
            schedule.setAuthor(rs.getString("author"));
            schedule.setPassword(rs.getString("password"));
            schedule.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            schedule.setModifiedAt(rs.getTimestamp("modified_at").toLocalDateTime());
            return schedule;
        };
    }
}