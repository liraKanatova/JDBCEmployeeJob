package peaksoft;

import peaksoft.config.Configuration;

import peaksoft.models.Employee;
import peaksoft.models.Job;
import peaksoft.service.EmployeeService;
import peaksoft.service.JobService;
import peaksoft.service.serviceImpl.EmployeeServiceImpl;
import peaksoft.service.serviceImpl.JobServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {

        JobService jobService = new JobServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();



//      Configuration.getConnectionToDatabase();
//       jobService.createJob();
//        jobService.addJob(new Job("Instuctor","Java9","Backend developer",2));
//        jobService.addJob(new Job("Mentor","Js9"," developer Fronted",1));
       // System.out.println(jobService.getJobById(1L));
//        String ascOrDesc = new Scanner(System.in).next();
//        System.out.println(jobService.sortByExperience(ascOrDesc));
//        System.out.println(jobService.getJobByEmployeeId(2L));
//        jobService.deleteDescriptionColumn();

//        employeeService.createEmployee();
//        employeeService.addEmployee(new Employee("Aijamal","Asangazieva",18,"aijamal@gmail.com",2));
//        employeeService.addEmployee(new Employee("Elizar","Mentor",20,"elizar@gmail.com",1));
//        employeeService.dropTable();
//        employeeService.cleanTable();
//        employeeService.updateEmployee(2L,new Employee("Mukhammed","Asantegin",22,"mukhammed@gmail.com",1));
//        System.out.println(employeeService.getAllEmployees());
//        String email = new Scanner(System.in).nextLine();
//        System.out.println(employeeService.findByEmail(email));
//        String position = new Scanner(System.in).nextLine();
//        System.out.println(employeeService.getEmployeeByPosition(position));
    }
}
