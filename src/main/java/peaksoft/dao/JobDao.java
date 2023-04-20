package peaksoft.dao;

import peaksoft.models.Job;

import java.util.List;

public interface JobDao {
    void createJob();
    void addJob(Job job);
    Job getJobById(Long jobId);
    List<Job>sortByExperience(String ascOrDesc);
    Job getJobByEmployeeId(Long employeeId);
    void deleteDescriptionColumn();
}
