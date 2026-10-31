package db.repository;

import db.models.UserDbEntity;
import io.qameta.allure.Step;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDbRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDbRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Step("БД: Создать или обновить пользователя с id={user.id}")
    public void save(UserDbEntity user) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", user.id())
                .addValue("email", user.email())
                .addValue("firstName", user.firstName())
                .addValue("lastName", user.lastName());

        String updateSql = """
            UPDATE users 
            SET email = :email, first_name = :firstName, last_name = :lastName 
            WHERE id = :id
        """;

        int rowsUpdated = jdbcTemplate.update(updateSql, params);

        if (rowsUpdated == 0) {
            String insertSql = """
                INSERT INTO users (id, email, first_name, last_name, created_at)
                VALUES (:id, :email, :firstName, :lastName, CURRENT_TIMESTAMP)
            """;
            jdbcTemplate.update(insertSql, params);
        }
    }

    @Step("БД: Найти пользователя по id={id}")
    public Optional<UserDbEntity> findById(Long id) {
        String sql = "SELECT id, email, first_name, last_name, created_at FROM users WHERE id = :id";
        try {
            UserDbEntity entity = jdbcTemplate.queryForObject(
                    sql,
                    new MapSqlParameterSource("id", id),
                    new DataClassRowMapper<>(UserDbEntity.class)
            );
            return Optional.ofNullable(entity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Step("БД: Найти пользователя по email={email}")
    public Optional<UserDbEntity> findByEmail(String email) {
        String sql = "SELECT id, email, first_name, last_name, created_at FROM users WHERE email = :email";
        try {
            UserDbEntity entity = jdbcTemplate.queryForObject(
                    sql,
                    new MapSqlParameterSource("email", email),
                    new DataClassRowMapper<>(UserDbEntity.class)
            );
            return Optional.ofNullable(entity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Step("БД: Удалить пользователя по id={id}")
    public void deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = :id";
        jdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }
}