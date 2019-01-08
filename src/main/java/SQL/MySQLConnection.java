package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    String HOST;
    String PORT;
    String DB;
    String USER;
    String PASSWORD;
    public MySQLConnection(String HOST, String PORT, String DB, String USER, String PASSWORD) {
        this.HOST = HOST;
        this.PORT = PORT;
        this.DB = DB;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }
    public Connection open() {
        Connection con = null;
        try {
                Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            System.out.println("conメソッドのJDBCのクラス指定でエラーが発生しました。開発者に問い合わせてください");
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PORT+"/"+DB, USER, PASSWORD);
            return con;
        } catch(SQLException e) {
            System.out.println("openメソッドでのMySQLとのコネクションが失敗しました。");
            e.printStackTrace();
        }
        return con;
    }
}
