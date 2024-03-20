import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreation{

    public TableCreation(){

    }

    public static void main(String[] args) {
        try {
            createTable();
            insertValues();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable() throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/COMP3005";
        String user = "postgres";
        String password = "TFERPLGK";

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement stmt = connection.createStatement();

        if(connection != null){
            String tableCreation = "CREATE TABLE students (\n" +
                    "    student_id Integer PRIMARY KEY,\n" +
                    "    first_name text NOT NULL,\n" +
                    "    last_name text NOT NULL,\n" +
                    "    email text NOT NULL UNIQUE,\n" +
                    "    enrollment_date date\n" +
                    ");";

            stmt.executeUpdate(tableCreation);

            //stmt.close();
        }else{
            System.out.println("Failed to connect to the database");
        }
    }

    private static void insertValues() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/COMP3005";
        String user = "postgres";
        String password = "TFERPLGK";

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement stmt = connection.createStatement();
        String insertion = "INSERT INTO students (student_id, first_name, last_name, email, enrollment_date) VALUES\n" +
                "(1, 'John', 'Doe', 'john.doe@example.com', '2023-09-01'),\n" +
                "(2, 'Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),\n" +
                "(3, 'Jim', 'Beam', 'jim.beam@example.com', '2023-09-02');";

        stmt.executeQuery(insertion);
    }
}
