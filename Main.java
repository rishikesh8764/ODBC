import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ODBCExample {

    public static void main(String[] args) {
        // JDBC URL format for ODBC
        String jdbcUrl = "jdbc:odbc:YourDSNName";  // Replace YourDSNName with your actual DSN name

        // Database credentials
        String username = "your_username"; // Replace with your database username
        String password = "your_password"; // Replace with your database password

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Load the JDBC-ODBC Bridge driver
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            
            // Establish the connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            // Create a statement
            statement = connection.createStatement();
            
            // Execute a query
            String query = "SELECT * FROM your_table"; // Replace with your actual query
            resultSet = statement.executeQuery(query);
            
            // Process the result set
            while (resultSet.next()) {
                // Assuming the table has a column named 'id' and 'name'
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
