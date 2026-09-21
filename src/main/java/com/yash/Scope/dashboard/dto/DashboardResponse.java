package com.yash.Scope.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {

    private long totalClients;
    private long  activeProjects;
    private long pendingInvoices;
    private long paidInvoices;
    private long overdueInvoices;

    private List<UpcomingDeadline> upcomingDeadlines;
}
