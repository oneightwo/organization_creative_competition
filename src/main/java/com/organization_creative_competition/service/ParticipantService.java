package com.organization_creative_competition.service;

import com.organization_creative_competition.model.Participant;

import java.util.List;

public interface ParticipantService extends CrudService<Participant, Long>{

    List<Participant> getAllByCompetitionId(Long id);

    Participant markAsWinner(Long id, Boolean isWinner);
}
