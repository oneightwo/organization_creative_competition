package com.organization_creative_competition.service.impl;

import com.organization_creative_competition.model.Competition;
import com.organization_creative_competition.model.User;
import com.organization_creative_competition.repository.CompetitionRepository;
import com.organization_creative_competition.security.SecurityUtils;
import com.organization_creative_competition.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;

    @Transactional(readOnly = true)
    @Override
    public Competition getById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Competition id is null");
        }
        return competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition with id=" + id + " not found"));
    }

    @Override
    public Competition save(Competition competition) {
        competition.setIsDeleted(Boolean.FALSE);
        competition.setCreationDate(LocalDateTime.now());
        User userFromContext = SecurityUtils.getUserFromContext();
        if (Objects.nonNull(userFromContext)) {
            competition.setCreatedBy(userFromContext);
        }
        return competitionRepository.save(competition);
    }

    @Override
    public Competition update(Competition competition) {
        Competition byId = getById(competition.getId());
        competition.setCreatedBy(byId.getCreatedBy());
        competition.setIsDeleted(byId.getIsDeleted());
        competition.setCreationDate(byId.getCreationDate());
        competition.setParticipants(byId.getParticipants());
        if (!competition.getIsActive()) {
            competition.getParticipants().forEach(participant -> {
                if (Objects.isNull(participant.getIsWinner())) {
                    participant.setIsWinner(Boolean.FALSE);
                }
            });
        }
        return competitionRepository.save(competition);
    }

    @Override
    public void delete(Long id) {
        Competition competition = getById(id);
        competition.setIsActive(Boolean.FALSE);
        competition.setIsDeleted(Boolean.TRUE);
        competitionRepository.save(competition);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Competition> findAll() {
        return competitionRepository.findAll().stream()
                .filter(competition -> !competition.getIsDeleted())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Competition> getActiveCompetitions() {
        return competitionRepository.findAllByIsActiveAndIsDeletedFalseOrIsDeletedNull(Boolean.TRUE);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Competition> getActiveCompetitionsByCreated() {
        User userFromContext = SecurityUtils.getUserFromContext();
        if (Objects.nonNull(userFromContext)) {
            return findAll().stream()
                    .filter(competition -> competition.getCreatedBy().getId().equals(userFromContext.getId()))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
