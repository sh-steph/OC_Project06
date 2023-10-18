package com.openclassrooms.mdd.mddapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {
    private Long id;

    private UserDto user;

    private ThemeDto theme;
}

