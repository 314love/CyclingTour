package com.cyclingTour.entity;

import lombok.Data;

@Data
public class ChallengeDetails {
    private Long id;
    private String routeName;
    private String routeImageUrl;
    private String routeDetails;
    private String username;
    private String phone;
    private String avatar;

    // Getters and setters
}
