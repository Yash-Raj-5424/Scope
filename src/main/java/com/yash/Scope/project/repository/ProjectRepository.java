package com.yash.Scope.project.repository;

import com.yash.Scope.project.entity.Project;
import com.yash.Scope.project.enums.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("select p.status as status, count(p) as total from Project p group by p.status")
    List<ProjectStatusCount> countGroupedByStatus();

    @Query("select p from Project p " +
            "where p.status not in :excludedStatuses " +
            "and p.deadline >= :today " +
            "order by p.deadline asc")
    List<Project> findUpcomingDeadlines(
            @Param("excludedStatuses") List<Status> excludedStatuses,
            @Param("today") LocalDate today,
            Pageable pageable);

    interface ProjectStatusCount {
        Status getStatus();
        long getTotal();
    }
}
