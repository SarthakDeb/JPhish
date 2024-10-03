package com.example.jphishemailservice.Controllers;

import com.example.jphishemailservice.Exceptions.ResourceNotFoundException;
import com.example.jphishemailservice.Models.LandingPage;
import com.example.jphishemailservice.Services.LandingPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/land")
public class LandingPageController {

    @Autowired
    private LandingPageService landingPageService;

    @GetMapping
    public List<LandingPage> getAllPages() {
        return landingPageService.getAllPages();
    }

    @PostMapping("/addpage")
    public LandingPage addPage(@RequestBody LandingPage landingPage) {
        return landingPageService.addPage(landingPage);
    }

    @PutMapping("/{id}")
    public LandingPage updatePage(@PathVariable Long id, @RequestBody LandingPage landingPage) throws ResourceNotFoundException {
        return landingPageService.updatePage(id, landingPage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePage(@PathVariable Long id) throws ResourceNotFoundException {
        landingPageService.deletePage(id);
        return ResponseEntity.noContent().build();
    }
}

