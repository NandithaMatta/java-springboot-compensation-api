package com.portfolio.payroll.repository;

import com.portfolio.payroll.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByJobCode(String jobCode);
    boolean existsByJobCode(String jobCode);
    List<Job> findByStatus(String status);
    List<Job> findByDepartmentId(Long departmentId);

    @Query("SELECT j FROM Job j " +
            "JOIN FETCH j.jobClass jc " +
            "JOIN FETCH jc.jobFamily jf " +
            "JOIN FETCH jf.familyGroup fg " +
            "JOIN FETCH jc.careerLevel cl " +
            "JOIN FETCH j.department d " +
            "JOIN FETCH d.division div " +
            "WHERE j.status = 'ACTIVE'")
    List<Job> findAllActiveWithDetails();
}