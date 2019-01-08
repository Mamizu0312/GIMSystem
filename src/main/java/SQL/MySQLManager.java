package SQL;

import Config.ConfigManager;
import StartUp.main;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class MySQLManager {
    public boolean createNewTable() {
        String HOST;
        String PORT;
        String DB;
        String USER;
        String PASSWORD;
        Connection con = null;
        HashMap<String, String> sqlconfigs = new HashMap<String, String>();
        sqlconfigs = new ConfigManager(new File("config.yml")).loadSQLConfigs();
        //TODO: new File("config.yml"))の部分は試験的なコードなので後で書き直す
        HOST = sqlconfigs.get("HOST");
        PORT = sqlconfigs.get("PORT");
        DB = sqlconfigs.get("DB");
        USER = sqlconfigs.get("USER");
        PASSWORD = sqlconfigs.get("PASSWORD");
        try {
            con = new MySQLConnection(HOST, PORT, DB, USER, PASSWORD).open();
            if(con == null) {
                System.out.println("MySQLとのコネクションに失敗しました。");
                return false;
            }
            String sendSQL = "CREATE TABLE IF NOT EXISTS GIMINFO ("+
                             "ManagementNumber int NOT NULL PRIMARY KEY,"+
                             "userID varchar(16) NOT NULL,"+
                             "userPassword varchar(32) NOT NULL,"+
                             "FamilyName varchar(20) NOT NULL,"+
                             "FirstName varchar(30) NOT NULL,"+
                             "TEL varchar(11),"+
                             "EmailAddress varchar(100)"+
                             ");";
            PreparedStatement pstmt = con.prepareStatement(sendSQL);
            pstmt.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(con != null) {
                    con.close();
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
