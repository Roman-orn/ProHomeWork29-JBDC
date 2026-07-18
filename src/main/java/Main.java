import org.postgresql.ds.PGSimpleDataSource;

public class Main {

    static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.addEmployee("name1", 20, "worker", 100);
        employeeDAO.addEmployee("name2", 30, "master", 200);
        employeeDAO.addEmployee("name3", 40, "engineer", 300);
        employeeDAO.addEmployee("name4", 50, "director", 400);

        employeeDAO.updateEmployee(2, "newName", 35, "master", 250);

        employeeDAO.deleteEmployee(2);

        employeeDAO.getEmployeeById(3);
    }
}
