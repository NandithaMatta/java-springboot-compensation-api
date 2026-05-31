package com.portfolio.payroll.service;

import com.portfolio.payroll.dto.MarketSurveyResponse;
import com.portfolio.payroll.entity.MarketSurvey;
import com.portfolio.payroll.exception.ResourceNotFoundException;
import com.portfolio.payroll.repository.MarketSurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarketSurveyService {

    private final MarketSurveyRepository marketSurveyRepository;

    @Transactional(readOnly = true)
    public List<MarketSurveyResponse> getAllSurveys() {
        return marketSurveyRepository.findAll()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MarketSurveyResponse> getSurveysByYear(Integer year) {
        return marketSurveyRepository.findByYearWithDetails(year)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MarketSurveyResponse getSurveyById(Long id) {
        MarketSurvey ms = marketSurveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MarketSurvey", id));
        return toResponse(ms);
    }

    private MarketSurveyResponse toResponse(MarketSurvey ms) {
        return MarketSurveyResponse.builder()
                .id(ms.getId())
                .surveyName(ms.getSurveyName())
                .provider(ms.getProvider())
                .surveyYear(ms.getSurveyYear())
                .classCode(ms.getJobClass().getCode())
                .jobFamilyName(ms.getJobClass().getJobFamily().getName())
                .careerLevelName(ms.getJobClass().getCareerLevel().getName())
                .p25(ms.getP25())
                .p50(ms.getP50())
                .p75(ms.getP75())
                .p90(ms.getP90())
                .build();
    }
}