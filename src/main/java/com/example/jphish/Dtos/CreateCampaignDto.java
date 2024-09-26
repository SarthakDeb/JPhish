package com.example.jphish.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCampaignDto {
    private String campaignName;
    private String campaignDescription;
    private String campaignTargetEmail;
    private String campaignEmailSubject;
    private String campaignEmailBody;

}
