package com.organization_creative_competition.service.impl;

import com.organization_creative_competition.model.Participant;
import com.organization_creative_competition.repository.ParticipantRepository;
import com.organization_creative_competition.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;

    @Transactional(readOnly = true)
    @Override
    public Participant getById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Participant id is null");
        }
        return participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant with id=" + id + " not found"));
    }

    @Override
    public Participant save(Participant participant) {
        Optional<Participant> participantOptional = participantRepository.findByCompetitionIdAndUserId(participant.getCompetition().getId(),
                participant.getUser().getId());
        if (participantOptional.isPresent()) {
            participant.setId(participantOptional.get().getId());
            return update(participant);
        }
        return participantRepository.save(participant);
    }

    @Override
    public Participant update(Participant participant) {
        getById(participant.getId());
        return participantRepository.save(participant);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        participantRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Participant> findAll() {
        return null;
    }

    @Override
    public List<Participant> getAllByCompetitionId(Long id) {
        //(x < y) ? -1 : ((x == y) ? 0 : 1);
        Comparator<Participant> comparator = (Participant participant1, Participant participant2) -> {
            if (Objects.isNull(participant1.getIsWinner()) && Objects.isNull(participant2.getIsWinner())) {
                return 0;
            }
            if (Objects.isNull(participant1.getIsWinner()) && Objects.nonNull(participant2.getIsWinner())) {
                return 1;
            }
            if (Objects.nonNull(participant1.getIsWinner()) && Objects.isNull(participant2.getIsWinner())) {
                return -1;
            }
            return Boolean.compare(participant2.getIsWinner(), participant1.getIsWinner());
        };

        return participantRepository.findByCompetitionId(id)
                .stream().sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public Participant markAsWinner(Long id, Boolean isWinner) {
        Participant participant = getById(id);
        participant.setIsWinner(isWinner);
        return participantRepository.save(participant);
    }
}
