package com.sam.crickstatloginservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long userId;

   @Column(nullable = false)
   private String username;

    @Column(nullable = false , unique = true)
   private String email;

    @Column(nullable = false)
   private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ERole role;

}
