package com.hr.hrsystem.entity;

import com.hr.hrsystem.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkin")

@Getter
@Setter
@AllArgsConstructor
@Builder

public class Checkin extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", foreignKey = @ForeignKey(name = "FK_CHECKIN_USER_ID"))
    private User user;

    @Column(nullable = false)
    private LocalDateTime checkinTime;

    public Checkin(){
        this.checkinTime = LocalDateTime.now();
    }

}
