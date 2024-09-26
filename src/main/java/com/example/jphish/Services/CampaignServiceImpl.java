package com.example.jphish.Services;

import com.example.jphish.Dtos.CreateCampaignDto;
import com.example.jphish.Dtos.SendEmailEventDto;
import com.example.jphish.Exceptions.CampaignExistsException;
import com.example.jphish.Exceptions.CampaignNotFound;
import com.example.jphish.Models.Campaign;
import com.example.jphish.Repositories.CampaignRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {

    private  CampaignRepository campaignRepository;
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public CampaignServiceImpl(CampaignRepository campaignRepository,
                               KafkaTemplate<String, String> kafkaTemplate,
                               ObjectMapper objectMapper) {
        this.campaignRepository = campaignRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Campaign createCampaign(CreateCampaignDto createCampaignDto) throws CampaignExistsException {
        Optional<Campaign> optionalCampaign = campaignRepository.findByCampaignName(createCampaignDto.getCampaignName());
//        if (optionalCampaign.isPresent()) {throw new CampaignExistsException("Campaign already exists");
//        }
        Campaign campaign = new Campaign();
        campaign.setCampaignName(createCampaignDto.getCampaignName());
        campaign.setCampaignDescription(createCampaignDto.getCampaignDescription());
        campaign.setCampaignTargetEmail(createCampaignDto.getCampaignTargetEmail());
        campaign.setCampaignEmailSubject(createCampaignDto.getCampaignEmailSubject());
        campaign.setCampaignEmailBody(createCampaignDto.getCampaignEmailBody());
        Campaign savedCampaign = campaignRepository.save(campaign);

        SendEmailEventDto sendEmailEventDto = new SendEmailEventDto();
        sendEmailEventDto.setTo(createCampaignDto.getCampaignTargetEmail());
        sendEmailEventDto.setFrom("me@sarthakdev.tech");
        sendEmailEventDto.setSubject(createCampaignDto.getCampaignEmailSubject());
        sendEmailEventDto.setBody(createCampaignDto.getCampaignEmailBody());

        try {
            kafkaTemplate.send(
                    "SendEmail",
                    objectMapper.writeValueAsString(sendEmailEventDto)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

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
