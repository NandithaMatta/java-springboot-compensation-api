package com.portfolio.payroll.repository;

import com.portfolio.payroll.entity.MarketSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MarketSurveyRepository extends JpaRepository<MarketSurvey, Long> {
    List<MarketSurvey> findByJobClassId(Long classId);
    List<MarketSurvey> findBySurveyYear(Integer year);
    List<MarketSurvey> findByProvider(String provider);

    @Query("SELECT ms FROM MarketSurvey ms " +
            "JOIN FETCH ms.jobClass jc " +
            "JOIN FETCH jc.jobFamily jf " +
            "JOIN FETCH jc.careerLevel cl " +
            "WHERE ms.surveyYear = :year")
    List<MarketSurvey> findByYearWithDetails(@Param("year") Integer year);
}