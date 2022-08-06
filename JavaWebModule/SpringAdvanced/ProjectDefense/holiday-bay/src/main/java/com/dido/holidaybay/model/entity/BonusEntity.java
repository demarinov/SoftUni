package com.dido.holidaybay.model.entity;

import com.dido.holidaybay.model.enums.BonusTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="bonus")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class BonusEntity extends BaseEntity {

    @Column(name="bonus_type")
    @Enumerated(EnumType.STRING)
    private BonusTypeEnum bonusType;


    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private UserEntity user;

    @Column(name="info")
    private String info;

    @Column(name="not_given")
    private boolean notGiven;

    @Column(name="start_date")
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;
}
