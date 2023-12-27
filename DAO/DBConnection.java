package DAO;
import java.sql.*;
public class DBConnection {
    private Connection con;
    private Statement st = null;
    public DBConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost/tp_threads";
            con = DriverManager.getConnection(url,"root","");
        } catch (Exception e){
            System.out.println("Error creation con: " + e.getMessage());
        }
    }
    public Connection getCon() {
        return con;
    }
    public Statement getSt() {
        return st;
    }
    public void setSt(Statement st) {
        this.st = st;
    }
}