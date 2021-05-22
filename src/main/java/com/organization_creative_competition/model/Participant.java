package com.organization_creative_competition.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "participants")
public class Participant {

    private static final String PARTICIPANT_ID_SEQ = "participant_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PARTICIPANT_ID_SEQ)
    @SequenceGenerator(name = PARTICIPANT_ID_SEQ, sequenceName = PARTICIPANT_ID_SEQ, allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "varchar(4000)")
    private String message;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "is_winner")
    private Boolean isWinner;

}
