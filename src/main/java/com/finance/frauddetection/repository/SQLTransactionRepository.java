package com.finance.frauddetection.repository;

import com.finance.frauddetection.model.Transaction;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Profile("jdbc")
public class SQLTransactionRepository implements iTransactionRepository {

    private final JdbcTemplate jdbcTemplate;

    public SQLTransactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Transaction> rowMapper = (rs, rowNum) -> {
        Transaction transaction = new Transaction();
        transaction.setId(rs.getInt("id"));
        transaction.setCustomerId(rs.getInt("customer_id"));
        transaction.setAmount(rs.getBigDecimal("amount"));
        transaction.setTxnCountry(rs.getString("txn_country"));
        transaction.setTxnTimestamp(rs.getTimestamp("txn_timestamp").toLocalDateTime());
        transaction.setStatus(rs.getString("status"));
        return transaction;
    };

    @Override
    public List<Transaction> findAll() {
        String sql = "SELECT * FROM transactions";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Transaction findById(int id) {
        String sql = "SELECT * FROM transactions WHERE id = ?";
        List<Transaction> list = jdbcTemplate.query(sql, rowMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int save(Transaction transaction) {

        String sql = """
                INSERT INTO transactions
                (customer_id, amount, txn_country, txn_timestamp, status)
                VALUES (?, ?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setInt(1, transaction.getCustomerId());
            ps.setBigDecimal(2, transaction.getAmount());
            ps.setString(3, transaction.getTxnCountry());
            ps.setTimestamp(4, Timestamp.valueOf(transaction.getTxnTimestamp()));
            ps.setString(5, transaction.getStatus());

            return ps;
        }, keyHolder);

        Integer id = keyHolder.getKeyAs(Integer.class);

        if (id == null) {
            throw new RuntimeException("Unable to generate transaction id");
        }

        return id;
    }

    @Override
    public void updateStatus(int id, String status) {
        String sql = "UPDATE transactions SET status=? WHERE id=?";
        jdbcTemplate.update(sql, status, id);
    }
}
