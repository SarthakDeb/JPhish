package com.example.jphish.Repositories;

import com.example.jphish.Models.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    Optional<Campaign> findByCampaignName(String campaignName);
}
