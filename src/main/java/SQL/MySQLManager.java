package SQL;

import Config.ConfigManager;
import com.sun.org.apache.xerces.internal.util.ErrorHandlerProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class MySQLManager {
    public void createTable() {
        String HOST = null;
        String PORT = null;
        String DB = null;
        String USER = null;
        String PASSWORD = null;
        Connection con = null;
        HashMap<String, String> sqlconfigs = new HashMap<String, String>();

        sqlconfigs = new ConfigManager().loadSQLConfigs();
        HOST = sqlconfigs.get("HOST");
        PORT = sqlconfigs.get("PORT");
        DB = sqlconfigs.get("DB");
        USER = sqlconfigs.get("USER");
        PASSWORD = sqlconfigs.get("PASSWORD");
        try {
            con = new MySQLConnection(HOST, PORT, DB, USER, PASSWORD).open();
            if(con == null) {
                System.out.println("MySQLとのコネクションに失敗しました。");
                System.out.println("現在、このソフトウェアは使用できません。");
                System.exit(-1);
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
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
