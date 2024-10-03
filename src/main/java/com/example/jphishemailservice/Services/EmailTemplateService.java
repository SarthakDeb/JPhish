package com.example.jphishemailservice.Services;

import com.example.jphishemailservice.Exceptions.ResourceNotFoundException;
import com.example.jphishemailservice.Models.EmailTemplate;
import com.example.jphishemailservice.Repositories.EmailTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailTemplateService {

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    public List<EmailTemplate> getAllTemplates() {
        return emailTemplateRepository.findAll();
    }

    public EmailTemplate addTemplate(EmailTemplate emailTemplate) {
        return emailTemplateRepository.save(emailTemplate);
    }

    public EmailTemplate updateTemplate(Long id, EmailTemplate emailTemplate) throws ResourceNotFoundException {
        if (emailTemplateRepository.existsById(id)) {
            emailTemplate.setId(id);
            return emailTemplateRepository.save(emailTemplate);
        }
        throw new ResourceNotFoundException("Template not found with id: " + id);
    }

    public void deleteTemplate(Long id) throws ResourceNotFoundException {
        if (emailTemplateRepository.existsById(id)) {
            emailTemplateRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Template not found with id: " + id);
        }
    }
}

