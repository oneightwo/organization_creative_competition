package com.organization_creative_competition.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ParticipantDto {

    private Long id;

    private String message;

    private CompetitionDto competition;

    private UserDto user;

    private Boolean isWinner;

}
