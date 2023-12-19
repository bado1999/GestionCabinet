package com.gestioncabinet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.StringReader;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Soin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String image;
    private Double prix;
}
