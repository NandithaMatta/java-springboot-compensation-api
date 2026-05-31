package com.portfolio.payroll.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "market_surveys")
public class MarketSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "survey_name", nullable = false, length = 200)
    private String surveyName;

    @Column(nullable = false, length = 100)
    private String provider;

    @Column(name = "survey_year", nullable = false)
    private Integer surveyYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private JobClass jobClass;

    @Column(precision = 15, scale = 2)
    private BigDecimal p25;

    @Column(precision = 15, scale = 2)
    private BigDecimal p50;

    @Column(precision = 15, scale = 2)
    private BigDecimal p75;

    @Column(precision = 15, scale = 2)
    private BigDecimal p90;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }
}
