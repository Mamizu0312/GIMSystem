package Login;

import Config.ConfigManager;
import SQL.MySQLConnection;
import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class Login {
    String userID;
    String userPassword;
    public Login(String userID, String userPassword) {
        this.userID = userID;
        this.userPassword = userPassword;
    }
    public boolean login() {
        Connection con;
        HashMap<String, String> sqlconfigs;
        String HOST;
        String PORT;
        String DB;
        String USER;
        String PASSWORD;
        PreparedStatement pstmt;
        int sqlreturn = 0;

        sqlconfigs = ConfigManager.loadSQLConfigs();
        HOST = sqlconfigs.get("HOST");
        PORT = sqlconfigs.get("PORT");
        DB = sqlconfigs.get("DB");
        USER = sqlconfigs.get("USER");
        PASSWORD = sqlconfigs.get("PASSWORD");
        con = new MySQLConnection(HOST, PORT, DB, USER, PASSWORD).open();
        String sendSQL = "SELECT userID, userPassword FROM GIMINFO WHERE userID = '?' AND userPassword = '?'";
        try {
            pstmt = con.prepareStatement(sendSQL);
            pstmt.setString(1, userID);
            pstmt.setString(2, userPassword);
            sqlreturn = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        if (sqlreturn != 0) {
            return true;
        } else {
            return false;
        }
    }
}
