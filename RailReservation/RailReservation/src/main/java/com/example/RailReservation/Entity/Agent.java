package com.example.RailReservation.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agents")
@Builder
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column
    private String password;
    @Email
    @Column(unique = true)
    private String email;
    private String phone;
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;
    public enum Role {
        AGENT, ADMIN
    }









}
