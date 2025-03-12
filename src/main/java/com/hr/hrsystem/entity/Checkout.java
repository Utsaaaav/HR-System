package com.hr.hrsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkout")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", foreignKey = @ForeignKey(name = "FK_CHECKOUT_USER_ID"))
    private User user;

    @Column(nullable = false)
    private LocalDateTime checkoutTime;

}
