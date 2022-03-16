package javaapplication1;
import java.sql.*;

public class DBConn {
    Connection conn = null;
    public Connection getConnection() {
        conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "tark", "sogong");
        } catch (ClassNotFoundException e) {
            System.out.println("1/6 로딩실패");
        } catch (SQLException e) {
           System.out.println("Error11");
        } catch (Exception e) {
           System.out.println("Error22");
        }
        return conn;
    }
    public void freeConnection()
    {
        try{
            conn.close();
        }
        catch(SQLException se){

        }
    }
}
