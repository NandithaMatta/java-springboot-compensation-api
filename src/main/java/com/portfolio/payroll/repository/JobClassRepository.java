package com.portfolio.payroll.repository;

import com.portfolio.payroll.entity.JobClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobClassRepository extends JpaRepository<JobClass, Long> {
    Optional<JobClass> findByCode(String code);
    List<JobClass> findByJobFamilyId(Long jobFamilyId);
    List<JobClass> findByCareerLevelId(Long careerLevelId);

    @Query("SELECT jc FROM JobClass jc " +
            "JOIN FETCH jc.jobFamily jf " +
            "JOIN FETCH jf.familyGroup fg " +
            "JOIN FETCH jc.careerLevel cl " +
            "JOIN FETCH cl.careerGroup cg")
    List<JobClass> findAllWithDetails();
}