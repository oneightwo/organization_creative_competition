package com.organization_creative_competition.repository;

import com.organization_creative_competition.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    List<Competition> findAllByIsActiveAndIsDeletedFalseOrIsDeletedNull(Boolean isActive);
}
