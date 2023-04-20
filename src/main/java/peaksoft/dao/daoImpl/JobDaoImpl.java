package peaksoft.dao.daoImpl;

import peaksoft.config.Configuration;
import peaksoft.dao.JobDao;
import peaksoft.models.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {
    @Override
    public void createJob() {
   String sql = "create table jobs(" +
           "id serial primary key," +
           "position varchar," +
           "profession varchar," +
           "description varchar," +
           "experience int);";
   try(Connection connection = Configuration.getConnectionToDatabase();
       Statement statement = connection.createStatement()){
       statement.executeUpdate(sql);
       System.out.println("successfully created");
   }catch (SQLException e){
       System.out.println(e.getMessage());
   }
    }

    @Override
    public void addJob(Job job) {
        String sql = " insert into jobs(position,profession,description,experience)" +
                "values(?,?,?,?);";
        try(Connection connection = Configuration.getConnectionToDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            System.out.println("successfully added");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Job getJobById(Long jobId) {
        Job job = new Job();
        String sql = "select * from jobs where id = ?;";
        try(Connection connection = Configuration.getConnectionToDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,jobId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                       job.setExperience(resultSet.getInt("experience"));
            }
            resultSet.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return job;
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job>jobList = new ArrayList<>();

        String sort = null;
        if(ascOrDesc.equals("asc")){
            sort = "select * from jobs order by experience";
        }else if(ascOrDesc.equals("desc")){
            sort = "select * from jobs order by experience";
        }
        try(Connection connection = Configuration.getConnectionToDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(sort)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Job job = new Job();
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
                jobList.add(job);
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return jobList;
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        Job job = new Job();
        String sql = "select * from jobs join employees e on e.job_id=j.id where e.id = ? ;";
        try(Connection connection = Configuration.getConnectionToDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return job;
    }

    @Override
    public void deleteDescriptionColumn() {
        String sql = "alter table jobs drop column description;";
        try(Connection connection = Configuration.getConnectionToDatabase();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("successfully deleted column");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
