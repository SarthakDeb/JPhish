package com.example.jphish.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Admin extends BaseModel {
    private String username;
    private String password;
    private String email;
}
