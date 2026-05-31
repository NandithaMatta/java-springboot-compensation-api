package com.portfolio.payroll.controller;

import com.portfolio.payroll.dto.MarketSurveyResponse;
import com.portfolio.payroll.service.MarketSurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/market-surveys")
@RequiredArgsConstructor
public class MarketSurveyController {

    private final MarketSurveyService marketSurveyService;

    @GetMapping
    public ResponseEntity<List<MarketSurveyResponse>> getAllSurveys() {
        return ResponseEntity.ok(marketSurveyService.getAllSurveys());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarketSurveyResponse> getSurveyById(@PathVariable Long id) {
        return ResponseEntity.ok(marketSurveyService.getSurveyById(id));
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<MarketSurveyResponse>> getSurveysByYear(@PathVariable Integer year) {
        return ResponseEntity.ok(marketSurveyService.getSurveysByYear(year));
    }
}