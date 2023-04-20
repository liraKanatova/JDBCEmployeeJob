package peaksoft.dao.daoImpl;

import peaksoft.config.Configuration;
import peaksoft.dao.EmployeeDao;

import peaksoft.models.Employee;
import peaksoft.models.Job;

import java.sql.*;

import java.util.*;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public void createEmployee() {
        String sql = "create table  employees(" +
                "id serial primary key," +
                "first_name varchar," +
                "last_name varchar," +
                "age int," +
                "email varchar," +
                "job_id int references jobs(id)";
        try (Connection connection = Configuration.getConnectionToDatabase();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("employees table is created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addEmployee(Employee employee) {
        String sql = "insert into employees(first_name ,last_name ,age ,email ,job_id)" +
                "values(?,?,?,?,?); ";
        try (Connection connection = Configuration.getConnectionToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setInt(5, employee.getJobId());
            preparedStatement.executeUpdate();
            System.out.println(employee.getFirstName() + " is saved");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropTable() {
        String sql = "drop table employees;";
        try (Connection connection = Configuration.getConnectionToDatabase();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("successfully drop table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void cleanTable() {
        String sql = "delete from employees;";
        try (Connection connection = Configuration.getConnectionToDatabase();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("successfully is cleaned ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void updateEmployee(Long id, Employee newEmployee) {
        String sql = "update employees set first_name = ?, last_name = ?, age = ?, email = ?, job_id = ? where id = ?;";
        try (Connection connection = Configuration.getConnectionToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newEmployee.getFirstName());
            preparedStatement.setString(2, newEmployee.getLastName());
            preparedStatement.setInt(3, newEmployee.getAge());
            preparedStatement.setString(4, newEmployee.getEmail());
            preparedStatement.setInt(5, newEmployee.getJobId());
            preparedStatement.setLong(6, id);
            preparedStatement.executeUpdate();
            System.out.println(newEmployee.getFirstName() + " successfully updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "select * from employees;";
        try (Connection connection = Configuration.getConnectionToDatabase();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                employeeList.add(new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("job_id")));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employeeList;
    }

    @Override
    public Employee findByEmail(String email) {
        Employee employee = new Employee();
        String sql = "select * from employees where email = ?;";
        try (Connection connection = Configuration.getConnectionToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setFirstName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("job_id"));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employee;
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee, Job> employeeJobMap = new LinkedHashMap<>();
        String sql = "select * from employees e join jobs j on e.job_id=j.id where e.id = ?;";
        try (Connection connection = Configuration.getConnectionToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeJobMap.put(new Employee(
                                resultSet.getLong(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getInt(4),
                                resultSet.getString(5),
                                resultSet.getInt(6)),
                        new Job(
                                resultSet.getLong(7),
                                resultSet.getString(8),
                                resultSet.getString(9),
                                resultSet.getString(10),
                                resultSet.getInt(11)
                        ));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employeeJobMap;
    }

   @Override
    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee>employeeList = new ArrayList<>();
        String sql = "select * from employees e join jobs j on e.job_id= j.id where position = ? ;";
        try(Connection connection = Configuration.getConnectionToDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,position);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employeeList.add(new Employee(resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("job_id")));

            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employeeList;
    }
}
