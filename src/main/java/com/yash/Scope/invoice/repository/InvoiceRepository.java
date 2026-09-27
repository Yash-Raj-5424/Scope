package com.yash.Scope.invoice.repository;

import com.yash.Scope.invoice.entity.Invoice;
import com.yash.Scope.invoice.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

    boolean existsByInvoiceNumber(String invoiceNumber);

    List<Invoice> findByClientIdOrderByIssueDateDesc(Long clientId);


    @Query("select i.status as status, count(i) as total from Invoice i group by i.status")
    List<InvoiceStatusCount> countGroupedByStatus();

    interface InvoiceStatusCount {
        Status getStatus();
        long getTotal();
    }


    // use atomic conditional bulk update
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
        update Invoice i
            set i.status = :overdueStatus
                where i.status = :sentStatus
                    and i.dueDate < :today
    """)
    int markInvoicesOverdue(
            @Param("sentStatus") Status sentStatus,
            @Param("overdueStatus") Status overdueStatus,
            @Param("today") LocalDate today
    );
}
