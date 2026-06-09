package com.supplychain.management.controller;

import com.supplychain.management.dto.DashboardDTO;
import com.supplychain.management.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService
    ) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public DashboardDTO getDashboard() {
        return dashboardService.getDashboard();
    }
}