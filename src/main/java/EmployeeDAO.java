import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    public void addEmployee(String name, int age, String position, double salary) {
        String sql = "INSERT INTO employees (name, age, position, salary) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, position);
            preparedStatement.setDouble(4, salary);
            preparedStatement.executeUpdate();
            System.out.println("Employee added.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateEmployee(Integer id, String name, int age, String position, double salary) {
        String sql = "UPDATE employees SET name = ?, age = ?, position = ?, salary = ? WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, position);
            preparedStatement.setDouble(4, salary);
            preparedStatement.setInt(5, id);
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                System.out.println("Employee updated.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteEmployee(Integer id) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                System.out.println("Employee deleted.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void getEmployeeById(Integer id) {
        String sql = "SELECT * FROM employees WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                System.out.printf(
                        "Employee with id %d (name: %s, age: %d, position: %s, salary: %.2f)\n",
                        id,
                        res.getString("name"),
                        res.getInt("age"),
                        res.getString("position"),
                        res.getDouble("salary")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
