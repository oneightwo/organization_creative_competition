package com.organization_creative_competition.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "competitions")
public class Competition {

    private static final String COMPETITION_ID_SEQ = "competition_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = COMPETITION_ID_SEQ)
    @SequenceGenerator(name = COMPETITION_ID_SEQ, sequenceName = COMPETITION_ID_SEQ, allocationSize = 1)
    private Long id;

    private String name;

    @Column(columnDefinition = "varchar(4000)")
    private String description;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    private String prize;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Participant> participants;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by_id", referencedColumnName = "id")
    private User createdBy;

}
