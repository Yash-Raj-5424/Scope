package com.yash.Scope.dashboard.service;

import com.yash.Scope.client.repository.ClientRepository;
import com.yash.Scope.dashboard.dto.DashboardResponse;
import com.yash.Scope.dashboard.dto.UpcomingDeadline;
import com.yash.Scope.invoice.repository.InvoiceRepository;
import com.yash.Scope.invoice.repository.InvoiceRepository.InvoiceStatusCount;
import com.yash.Scope.project.entity.Project;
import com.yash.Scope.project.repository.ProjectRepository;
import com.yash.Scope.project.repository.ProjectRepository.ProjectStatusCount;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ClientRepository clientRepository;
    private final ProjectRepository projectRepository;
    private final InvoiceRepository invoiceRepository;

    public DashboardResponse getDashboardSummary(){

        List<ProjectStatusCount> projectCounts = projectRepository.countGroupedByStatus();
        List<InvoiceStatusCount> invoiceCounts = invoiceRepository.countGroupedByStatus();

        // i'll consider invoices with DRAFT and SENT status as pending
        long pendingInvoices = countFor(invoiceCounts, com.yash.Scope.invoice.enums.Status.DRAFT)
                + countFor(invoiceCounts, com.yash.Scope.invoice.enums.Status.SENT);

        // we'll consider projects neither COMPLETED/CANCELLED as upcoming
        List<UpcomingDeadline> upcomingDeadlines = projectRepository
                .findUpcomingDeadlines(
                        List.of(com.yash.Scope.project.enums.Status.COMPLETED,
                                com.yash.Scope.project.enums.Status.CANCELLED   // exclude these
                        ),

                        LocalDate.now(),
                        PageRequest.of(0, 5))
                .stream()
                .map(DashboardService::toUpcomingDeadline)
                .toList();

        return DashboardResponse.builder()
                .totalClients(clientRepository.count())
                .activeProjects(countFor(projectCounts, com.yash.Scope.project.enums.Status.ACTIVE))
                .pendingInvoices(pendingInvoices)
                .paidInvoices(countFor(invoiceCounts, com.yash.Scope.invoice.enums.Status.PAID))
                .overdueInvoices(countFor(invoiceCounts, com.yash.Scope.invoice.enums.Status.OVERDUE))
                .upcomingDeadlines(upcomingDeadlines)
                .build();
    }

    private long countFor(List<ProjectStatusCount> counts, com.yash.Scope.project.enums.Status status) {
        return counts.stream()
                .filter(c -> c.getStatus() == status)
                .mapToLong(ProjectStatusCount::getTotal)
                .sum();
    }

    private long countFor(List<InvoiceStatusCount> counts, com.yash.Scope.invoice.enums.Status status) {
        return counts.stream()
                .filter(c -> c.getStatus() == status)
                .mapToLong(InvoiceStatusCount::getTotal)
                .sum();
    }

    private static UpcomingDeadline toUpcomingDeadline(Project project) {
        return UpcomingDeadline.builder()
                .projectId(project.getId())
                .projectName(project.getName())
                .deadline(project.getDeadline())
                .build();
    }
}