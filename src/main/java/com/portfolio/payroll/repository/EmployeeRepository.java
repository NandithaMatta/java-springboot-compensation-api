package com.portfolio.payroll.repository;

import com.portfolio.payroll.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByEmployeeNumber(String employeeNumber);
    Page<Employee> findByStatus(String status, Pageable pageable);
    List<Employee> findByDepartmentId(Long departmentId);
    boolean existsByEmail(String email);
    boolean existsByEmployeeNumber(String employeeNumber);

    @Query("SELECT e FROM Employee e " +
            "JOIN FETCH e.job j " +
            "JOIN FETCH j.jobClass jc " +
            "JOIN FETCH jc.jobFamily jf " +
            "JOIN FETCH jc.careerLevel cl " +
            "JOIN FETCH e.department d " +
            "JOIN FETCH d.division div " +
            "JOIN FETCH div.organization " +
            "WHERE e.id = :id")
    Optional<Employee> findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT e FROM Employee e " +
            "JOIN FETCH e.job j " +
            "JOIN FETCH j.jobClass jc " +
            "JOIN FETCH jc.jobFamily jf " +
            "JOIN FETCH jc.careerLevel cl " +
            "JOIN FETCH e.department d " +
            "JOIN FETCH d.division div " +
            "JOIN FETCH div.organization " +
            "WHERE d.name = :departmentName AND e.status = 'ACTIVE'")
    List<Employee> findActiveByDepartmentName(@Param("departmentName") String departmentName);

    @Query("SELECT COUNT(e) FROM Employee e WHERE e.status = :status")
    Long countByStatus(@Param("status") String status);
}