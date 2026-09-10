package com.yash.Scope.notes.repository;

import com.yash.Scope.notes.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes, Long> {
}
