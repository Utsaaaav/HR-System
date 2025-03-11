package com.hr.hrsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tbl_user", uniqueConstraints = {

        @UniqueConstraint(name = "unique_username", columnNames = "username")

})

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name = "username", length = 100)
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "imageUrl")
    private String imageUrl;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "role")
    private List<String> roles;

}
