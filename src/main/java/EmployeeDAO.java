import java.sql.*;
import java.util.Optional;

public class EmployeeDAO {

    public void save(Employee employee) {
        String sql = "INSERT INTO employees (name, age, position, salary) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getAge());
            preparedStatement.setString(3, employee.getPosition());
            preparedStatement.setDouble(4, employee.getSalary());

            preparedStatement.executeUpdate();

            try (ResultSet res = preparedStatement.getGeneratedKeys()) {
                if (res.next()) {
                    int id = res.getInt(1);
                    employee.setId(id);
                    System.out.println("Employee added with ID: " + id);
                }
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to save the employee: " + employee.getName(), ex);
        }
    }

    public void update(Employee employee) {
        String sql = "UPDATE employees SET name = ?, age = ?, position = ?, salary = ? WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getAge());
            preparedStatement.setString(3, employee.getPosition());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setInt(5, employee.getId());

            int res = preparedStatement.executeUpdate();

            if (res == 1) {
                System.out.printf("The data for the employee with ID %d has been updated.\n", employee.getId());
            } else {
                System.out.printf("Employee with ID %d not found.\n", employee.getId());
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to update data for employee: " + employee.getName(), ex);
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int res = preparedStatement.executeUpdate();

            if (res == 1) {
                System.out.printf("Employee with ID %d has been deleted.\n", id);
            } else {
                System.out.printf("Employee with ID %d not found.\n", id);
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to delete employee with id=" + id, ex);
        }
    }

    public Optional<Employee> getById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            try (ResultSet res = preparedStatement.executeQuery()) {
                if (res.next()) {
                    Employee employee = new Employee();
                    employee.setId(res.getInt("id"));
                    employee.setName(res.getString("name"));
                    employee.setAge(res.getInt("age"));
                    employee.setPosition(res.getString("position"));
                    employee.setSalary(res.getInt("salary"));

                    return Optional.of(employee);
                }
            }
            return Optional.empty();

        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to find employee with id=" + id, ex);
        }
    }
}
