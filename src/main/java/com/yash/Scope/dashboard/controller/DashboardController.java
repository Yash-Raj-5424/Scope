package com.yash.Scope.dashboard.controller;

import com.yash.Scope.dashboard.dto.DashboardResponse;
import com.yash.Scope.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboardResponse(){
        return ResponseEntity.ok(dashboardService.getDashboardSummary());
    }
}
