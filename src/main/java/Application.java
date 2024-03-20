import java.sql.*;
import java.util.Scanner;
public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("org.postgresql.Driver");

            while (true) {
                System.out.println("Which function would you like to use? Enter the number next to the desired function to use it \n" +
                        "1: getAllStudents()\n" +
                        "2: addStudent(first_name, last_name, email, enrollment_date)\n" +
                        "3: updateStudentEmail(student_id, new_email)\n" +
                        "4: deleteStudent(student_id)\n" +
                        "Any other key: Quit application");

                int read = scanner.nextInt();

                switch (read){
                    case 1:
                        getAllStudents();
                        break;
                    case 2:
                        System.out.println("Enter first name: ");
                        String first_name = scanner.nextLine();

                        System.out.println("Enter last name: ");
                        String last_name = scanner.nextLine();

                        System.out.println("Enter email: ");
                        String email = scanner.nextLine();

                        System.out.println("Enter enrollment date (yyyy-MM-dd): ");
                        String enrollment_date = scanner.nextLine();

                        addStudent(first_name, last_name, email, enrollment_date);
                        break;
                    case 3:
                        System.out.println("Enter id: ");
                        int id = scanner.nextInt();

                        System.out.println("Enter email: ");
                        String new_email = scanner.nextLine();

                        updateStudentEmail(id, new_email);

                        break;
                    case 4:
                        System.out.println("Enter id: ");
                        int sid = scanner.nextInt();

                        deleteStudent(sid);

                        break;
                    case 5:
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        // code block
                }

            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    private static void deleteStudent(int id){
        String url = "jdbc:postgresql://localhost:5432/COMP3005";
        String user = "postgres";
        String password = "TFERPLGK";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            String SQL ="DELETE FROM students WHERE student_id=" + id + ";";
            stmt.executeQuery(SQL);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void addStudent(String first_name, String last_name, String email, String enrollment_date){
        Date trueDate = Date.valueOf(enrollment_date);
        String url = "jdbc:postgresql://localhost:5432/COMP3005";
        String user = "postgres";
        String password = "TFERPLGK";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            String SQL ="INSERT INTO students (first_name, last_name, email, trueDate)\n" +
                    "VALUES (" + first_name + ", " + last_name + ", " + email + ", " + String.valueOf(trueDate) + ");";
            stmt.executeQuery(SQL);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void updateStudentEmail(int id, String newEmail){
        String url = "jdbc:postgresql://localhost:5432/COMP3005";
        String user = "postgres";
        String password = "TFERPLGK";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            String SQL ="UPDATE students\n" +
                    "SET email =" + newEmail + "\n" +
                    "WHERE student_id = " + id + ";";
            stmt.executeQuery(SQL);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void getAllStudents() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/COMP3005";
        String user = "postgres";
        String password = "TFERPLGK";

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement stmt = connection.createStatement();
        String SQL = "SELECT student_id, first_name, last_name, email, enrollment_date FROM students";
        ResultSet rs = stmt.executeQuery(SQL); // Process the result
        while(rs.next()){
            int studentId = rs.getInt("student_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            Date enrollmentDate = rs.getDate("enrollment_date");
            System.out.println("Student ID: " + studentId + ", Name: " + firstName + " " + lastName + ", Email: " + email + ", Enrollment Date: " + enrollmentDate);

        }
        // Close resources
        rs.close();
    }




}
