package com.portfolio.payroll.service;

import com.portfolio.payroll.dto.EmployeeRequest;
import com.portfolio.payroll.dto.EmployeeResponse;
import com.portfolio.payroll.dto.StatusUpdateRequest;
import com.portfolio.payroll.entity.Employee;
import com.portfolio.payroll.entity.Job;
import com.portfolio.payroll.entity.Department;
import com.portfolio.payroll.exception.BusinessValidationException;
import com.portfolio.payroll.exception.DuplicateResourceException;
import com.portfolio.payroll.exception.ResourceNotFoundException;
import com.portfolio.payroll.repository.DepartmentRepository;
import com.portfolio.payroll.repository.EmployeeRepository;
import com.portfolio.payroll.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public Page<EmployeeResponse> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(this::toDetailedResponse);
    }

    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id));
        return toDetailedResponse(employee);
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> getEmployeesByDepartment(String departmentName) {
        return employeeRepository.findActiveByDepartmentName(departmentName)
                .stream().map(this::toDetailedResponse).collect(Collectors.toList());
    }

    @Transactional
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        if (employeeRepository.existsByEmail(request.getEmail()))
            throw new DuplicateResourceException("Employee", "email", request.getEmail());
        if (employeeRepository.existsByEmployeeNumber(request.getEmployeeNumber()))
            throw new DuplicateResourceException("Employee", "employeeNumber", request.getEmployeeNumber());

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("Job", request.getJobId()));
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", request.getDepartmentId()));

        var jobClass = job.getJobClass();
        if (request.getBasicSalary().compareTo(jobClass.getMinSalary()) < 0 ||
                request.getBasicSalary().compareTo(jobClass.getMaxSalary()) > 0) {
            throw new BusinessValidationException(
                    String.format("Salary %.2f is outside the band [%.2f - %.2f] for class %s",
                            request.getBasicSalary(), jobClass.getMinSalary(),
                            jobClass.getMaxSalary(), jobClass.getCode()));
        }

        Employee employee = new Employee();
        employee.setEmployeeNumber(request.getEmployeeNumber());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setJob(job);
        employee.setDepartment(department);
        employee.setBasicSalary(request.getBasicSalary());
        employee.setHireDate(request.getHireDate());
        employee.setStatus("ACTIVE");

        return toResponse(employeeRepository.save(employee));
    }

    @Transactional
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id));

        employeeRepository.findByEmail(request.getEmail()).ifPresent(existing -> {
            if (!existing.getId().equals(id))
                throw new DuplicateResourceException("Employee", "email", request.getEmail());
        });

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("Job", request.getJobId()));
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", request.getDepartmentId()));

        var jobClass = job.getJobClass();
        if (request.getBasicSalary().compareTo(jobClass.getMinSalary()) < 0 ||
                request.getBasicSalary().compareTo(jobClass.getMaxSalary()) > 0) {
            throw new BusinessValidationException(
                    String.format("Salary %.2f is outside the band [%.2f - %.2f] for class %s",
                            request.getBasicSalary(), jobClass.getMinSalary(),
                            jobClass.getMaxSalary(), jobClass.getCode()));
        }

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setJob(job);
        employee.setDepartment(department);
        employee.setBasicSalary(request.getBasicSalary());
        employee.setHireDate(request.getHireDate());

        return toResponse(employeeRepository.save(employee));
    }

    @Transactional
    public EmployeeResponse updateStatus(Long id, StatusUpdateRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id));
        employee.setStatus(request.getStatus());
        return toResponse(employeeRepository.save(employee));
    }

    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id));
        employee.setStatus("INACTIVE");
        employeeRepository.save(employee);
    }

    private EmployeeResponse toResponse(Employee e) {
        return EmployeeResponse.builder()
                .id(e.getId())
                .employeeNumber(e.getEmployeeNumber())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .email(e.getEmail())
                .basicSalary(e.getBasicSalary())
                .hireDate(e.getHireDate())
                .status(e.getStatus())
                .createdAt(e.getCreatedAt())
                .build();
    }

    private EmployeeResponse toDetailedResponse(Employee e) {
        var job      = e.getJob();
        var jobClass = job.getJobClass();
        var dept     = e.getDepartment();
        return EmployeeResponse.builder()
                .id(e.getId())
                .employeeNumber(e.getEmployeeNumber())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .email(e.getEmail())
                .jobTitle(job.getTitle())
                .jobCode(job.getJobCode())
                .departmentName(dept.getName())
                .divisionName(dept.getDivision().getName())
                .organizationName(dept.getDivision().getOrganization().getName())
                .careerLevel(jobClass.getCareerLevel().getName())
                .jobFamily(jobClass.getJobFamily().getName())
                .basicSalary(e.getBasicSalary())
                .minSalaryBand(jobClass.getMinSalary())
                .maxSalaryBand(jobClass.getMaxSalary())
                .hireDate(e.getHireDate())
                .status(e.getStatus())
                .createdAt(e.getCreatedAt())
                .build();
    }
}