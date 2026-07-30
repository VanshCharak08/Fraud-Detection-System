package com.finance.frauddetection.fraudDB;

public class SQLTransactionRepository implements iTransactionRepository{
    private JdbcTemplate jdbcTemplate;
    public SQLTransactionRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        public LIst<Transaction> findAll(){
            return jdbcTemplate.query("Select * from DB");
        }
    }
}
