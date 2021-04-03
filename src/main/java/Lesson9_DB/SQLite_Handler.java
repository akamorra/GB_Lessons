package Lesson9_DB;
import java.sql.*;

public class SQLite_Handler {
    public Connection connection;
    public Statement statement;
    public PreparedStatement preparedStatement;

    public void disconnect(){
        try {
            if (statement!= null) statement.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
        try {
            if (connection!= null) connection.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void connect(String name_or_url) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:"+name_or_url);
        statement = connection.createStatement();
    }
}
