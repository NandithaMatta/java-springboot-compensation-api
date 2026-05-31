package com.portfolio.payroll.repository;

import com.portfolio.payroll.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByCode(String code);
    List<Department> findByDivisionId(Long divisionId);

    @Query("SELECT d FROM Department d " +
            "JOIN FETCH d.division div " +
            "JOIN FETCH div.organization")
    List<Department> findAllWithDetails();
}