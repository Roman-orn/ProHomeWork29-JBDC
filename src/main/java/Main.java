
import java.util.Optional;

public class Main {

    static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAO();

        try {
            Employee newEmployee = new Employee("newEmployee", 100, "newPosition", 1);
            employeeDAO.save(newEmployee);

            Integer newEmployeeId = newEmployee.getId();

            Optional<Employee> optionalEmployee = employeeDAO.getById(newEmployeeId);
            if (optionalEmployee.isPresent()) {
                Employee employeeForUpdate = optionalEmployee.get();
                employeeForUpdate.setSalary(1000);
                employeeDAO.update(employeeForUpdate);
            }

            employeeDAO.deleteById(newEmployeeId);

            employeeDAO.deleteById(newEmployeeId);

        } catch (DataProcessingException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
