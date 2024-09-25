package com.example.jphish.Services;

import com.example.jphish.Dtos.CreateCampaignDto;
import com.example.jphish.Exceptions.CampaignExistsException;
import com.example.jphish.Models.Campaign;

import java.util.List;

public interface CampaignService {
    Campaign createCampaign(CreateCampaignDto createCampaignDto) throws CampaignExistsException;
    List<Campaign> getCampaigns();
    Campaign getCampaignById(Long id);
    Campaign getCampaignByName(String name);
}
