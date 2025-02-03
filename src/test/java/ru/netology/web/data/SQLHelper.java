package ru.netology.web.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static void insertUsers(DataHelper.AuthInfo authInfo) {
        String passwordHash = "$2a$10$idd96zJ3W4pvWKxXMi155eMdE/nlUEsG9po5gjBDDLpHccXgAb9Iy";

        var dataSQL = "INSERT INTO users(id, login, password) VALUES (?, ?, ?);";
        try (var conn = getConnection()) {
            runner.update(conn, dataSQL, authInfo.getId(), authInfo.getLogin(), passwordHash);
        }
    }

    @SneakyThrows
    public static String getVerificationCode(DataHelper.AuthInfo authInfo) {
        String code;
        var dataSQL = "select code from auth_codes where user_id = ?";
        try (var conn = getConnection()) {
            code = runner.query(conn, dataSQL, authInfo.getId(), new ScalarHandler<>());
        }

        return code;
    }

    @SneakyThrows
    public static void deleteTestData() {
        var dataSQLAuthCodes = "delete from auth_codes;";
        var dataSQLCardTransactions = "delete from card_transactions;";
        var dataSQLCards = "delete from cards;";
        var dataSQLUsers = "delete from users;";

        try (var conn = getConnection()) {
            runner.update(conn, dataSQLAuthCodes);
            runner.update(conn, dataSQLCardTransactions);
            runner.update(conn, dataSQLCards);
            runner.update(conn, dataSQLUsers);
        }
    }
}
