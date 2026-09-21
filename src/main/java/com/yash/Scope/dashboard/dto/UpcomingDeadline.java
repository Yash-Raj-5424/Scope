package com.yash.Scope.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpcomingDeadline {

    private Long projectId;
    private String projectName;
    private LocalDate deadline;
}
