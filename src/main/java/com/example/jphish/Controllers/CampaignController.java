package com.example.jphish.Controllers;

import com.example.jphish.Dtos.CreateCampaignDto;
import com.example.jphish.Exceptions.CampaignExistsException;
import com.example.jphish.Models.Campaign;
import com.example.jphish.Services.CampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/camp")
public class CampaignController {
    private  CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping("/start")
    public ResponseEntity<Campaign> startCampaign(@RequestBody CreateCampaignDto createCampaignDto) throws CampaignExistsException {
        Campaign campaign = campaignService.createCampaign(createCampaignDto);
        return new ResponseEntity<>(campaign, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Campaign>> getAllCampaigns() {
        List<Campaign> campaignList = campaignService.getCampaigns();
        return new ResponseEntity<>(campaignList, HttpStatus.OK);
    }

    @GetMapping("/Id/{Id}")
    public Campaign getByCampaignId(@PathVariable("Id") Long id) {
        return campaignService.getCampaignById(id);
    }

    @GetMapping("/name/{name}")
    public Campaign getByCampaignName(@PathVariable("name") String campaignName) {
        return campaignService.getCampaignByName(campaignName);
    }
}
