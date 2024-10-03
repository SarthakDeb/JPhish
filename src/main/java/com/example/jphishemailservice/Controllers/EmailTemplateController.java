package com.example.jphishemailservice.Controllers;

import com.example.jphishemailservice.Exceptions.ResourceNotFoundException;
import com.example.jphishemailservice.Models.EmailTemplate;
import com.example.jphishemailservice.Services.EmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mailtemp")
public class EmailTemplateController {

    @Autowired
    private EmailTemplateService emailTemplateService;

    @GetMapping("/alltemp")
    public List<EmailTemplate> getAllTemplates() {
        return emailTemplateService.getAllTemplates();
    }

    @PostMapping("/addtemp")
    public EmailTemplate addTemplate(@RequestBody EmailTemplate emailTemplate) {
        return emailTemplateService.addTemplate(emailTemplate);
    }

    @PutMapping("/{id}")
    public EmailTemplate updateTemplate(@PathVariable Long id, @RequestBody EmailTemplate emailTemplate) throws ResourceNotFoundException {
        return emailTemplateService.updateTemplate(id, emailTemplate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Long id) throws ResourceNotFoundException {
        emailTemplateService.deleteTemplate(id);
        return ResponseEntity.noContent().build();
    }
}

