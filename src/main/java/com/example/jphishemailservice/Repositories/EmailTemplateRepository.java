package com.example.jphishemailservice.Repositories;

import com.example.jphishemailservice.Models.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {
}

