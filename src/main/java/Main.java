
import java.util.Optional;

public class Main {

    static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAO();

        try{
            Optional<Employee> optionalEmployee = employeeDAO.getById(1);
            if(optionalEmployee.isPresent()){
                Employee employee = optionalEmployee.get();
                employee.setSalary(1000);
                employeeDAO.update(employee);
            }
        } catch (DataProcessingException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }

        try{
            employeeDAO.deleteById(3);
        } catch (DataProcessingException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
