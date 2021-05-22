package com.organization_creative_competition.service;

import com.organization_creative_competition.model.Competition;

import java.util.List;

public interface CompetitionService extends CrudService<Competition, Long> {

    List<Competition> getActiveCompetitions();

    List<Competition> getActiveCompetitionsByCreated();

}
