package az.bdc.coursereporterjsp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/bdc_course";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "5524";

    public Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
