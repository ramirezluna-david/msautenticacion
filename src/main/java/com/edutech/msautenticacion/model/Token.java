package com.edutech.msautenticacion.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "token")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Token {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int idToken;

    @Column(nullable = false)
    private LocalDateTime emisionToken;

    @Column(nullable = false)
    private LocalDateTime revocacionToken;

    @Column(nullable = false)
    private Boolean isRevoked;

    @ManyToOne()
    @JoinColumn(name = "idUsuario")
    @JsonBackReference
    private Cred user;
}
