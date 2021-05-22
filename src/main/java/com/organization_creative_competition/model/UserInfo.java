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
import java.time.LocalDate;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "users_info")
public class UserInfo {

    private static final String USER_INFO_ID_SEQ = "user_info_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = USER_INFO_ID_SEQ)
    @SequenceGenerator(name = USER_INFO_ID_SEQ, sequenceName = USER_INFO_ID_SEQ, allocationSize = 1)
    private Long id;

    private String surname;

    private String name;

    private String patronymic;

    @Column(columnDefinition = "varchar(4000)")
    private String description;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

}
