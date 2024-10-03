package com.example.jphishemailservice.Services;

import com.example.jphishemailservice.Exceptions.ResourceNotFoundException;
import com.example.jphishemailservice.Models.LandingPage;
import com.example.jphishemailservice.Repositories.LandingPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandingPageService {

    @Autowired
    private LandingPageRepository landingPageRepository;

    public List<LandingPage> getAllPages() {
        return landingPageRepository.findAll();
    }

    public LandingPage addPage(LandingPage landingPage) {
        return landingPageRepository.save(landingPage);
    }

    public LandingPage updatePage(Long id, LandingPage landingPage) throws ResourceNotFoundException {
        if (landingPageRepository.existsById(id)) {
            landingPage.setId(id);
            return landingPageRepository.save(landingPage);
        }
        throw new ResourceNotFoundException("Landing page not found with id: " + id);
    }

    public void deletePage(Long id) throws ResourceNotFoundException {
        if (landingPageRepository.existsById(id)) {
            landingPageRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Landing page not found with id: " + id);
        }
    }
}
