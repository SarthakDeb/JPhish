package com.example.jphish.Services;

import com.example.jphish.Dtos.CreateCampaignDto;
import com.example.jphish.Exceptions.CampaignExistsException;
import com.example.jphish.Exceptions.CampaignNotFound;
import com.example.jphish.Models.Campaign;
import com.example.jphish.Repositories.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {
    private  CampaignRepository campaignRepository;
    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign createCampaign(CreateCampaignDto createCampaignDto) throws CampaignExistsException {
        Optional<Campaign> optionalCampaign = campaignRepository.findByCampaignName(createCampaignDto.getCampaignName());
        if (optionalCampaign.isPresent()) {throw new CampaignExistsException("Campaign already exists");
        }
        Campaign campaign = new Campaign();
        campaign.setCampaignName(createCampaignDto.getCampaignName());
        campaign.setCampaignDescription(createCampaignDto.getCampaignDescription());
        Campaign savedCampaign = campaignRepository.save(campaign);
        return savedCampaign;
    }

    @Override
    public List<Campaign> getCampaigns() {
        return campaignRepository.findAll();
    }

    @Override
    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new CampaignNotFound("Campaign not found"));
    }

    @Override
    public Campaign getCampaignByName(String name) {
        return campaignRepository.findByCampaignName(name)
                .orElseThrow(() -> new CampaignNotFound("Campaign not found"));
    }
}
