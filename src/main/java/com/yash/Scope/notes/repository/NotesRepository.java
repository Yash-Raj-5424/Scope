package com.yash.Scope.notes.repository;

import com.yash.Scope.notes.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepository extends JpaRepository<Notes, Long> {

    List<Notes> findByClientIdOrderByCreatedAtDesc(Long clientId);
    List<Notes> findByProjectIdOrderByCreatedAtDesc(Long projectId);
}
