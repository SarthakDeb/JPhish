package com.example.jphish.Repositories;

import com.example.jphish.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
