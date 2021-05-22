package com.organization_creative_competition.repository;

import com.organization_creative_competition.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Optional<Participant> findByCompetitionIdAndUserId(Long competitionId, Long userId);

    List<Participant> findByCompetitionId(Long competitionId);
}
