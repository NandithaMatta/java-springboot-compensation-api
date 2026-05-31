package com.portfolio.payroll.service;

import com.portfolio.payroll.dto.JobRequest;
import com.portfolio.payroll.dto.JobResponse;
import com.portfolio.payroll.entity.Job;
import com.portfolio.payroll.exception.DuplicateResourceException;
import com.portfolio.payroll.exception.ResourceNotFoundException;
import com.portfolio.payroll.repository.DepartmentRepository;
import com.portfolio.payroll.repository.JobClassRepository;
import com.portfolio.payroll.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final JobClassRepository jobClassRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public List<JobResponse> getAllJobs() {
        return jobRepository.findAllActiveWithDetails()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public JobResponse getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job", id));
        return toResponse(job);
    }

    @Transactional
    public JobResponse createJob(JobRequest request) {
        if (jobRepository.existsByJobCode(request.getJobCode()))
            throw new DuplicateResourceException("Job", "jobCode", request.getJobCode());

        var jobClass = jobClassRepository.findById(request.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException("JobClass", request.getClassId()));
        var department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", request.getDepartmentId()));

        Job job = new Job();
        job.setJobCode(request.getJobCode());
        job.setTitle(request.getTitle());
        job.setJobClass(jobClass);
        job.setDepartment(department);
        job.setStatus("ACTIVE");

        return toResponse(jobRepository.save(job));
    }

    private JobResponse toResponse(Job j) {
        var jc   = j.getJobClass();
        var dept = j.getDepartment();
        return JobResponse.builder()
                .id(j.getId())
                .jobCode(j.getJobCode())
                .title(j.getTitle())
                .status(j.getStatus())
                .departmentName(dept.getName())
                .divisionName(dept.getDivision().getName())
                .careerLevelName(jc.getCareerLevel().getName())
                .careerLevelCode(jc.getCareerLevel().getCode())
                .jobFamilyName(jc.getJobFamily().getName())
                .jobFamilyGroupName(jc.getJobFamily().getFamilyGroup().getName())
                .minSalary(jc.getMinSalary())
                .maxSalary(jc.getMaxSalary())
                .currency(jc.getCurrency())
                .build();
    }
}