package com.example.jphish.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Campaign extends BaseModel{
    private String campaignName;
    private String campaignDescription;
    private Time startTime;
    private Time endTime;
    @OneToOne
    private User targetUser;
}
