package com.gestioncabinet.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor @NoArgsConstructor
@Document("soin")
public class Soin {
    @Id
    private String id;
    private String nom;
    private String image;
    private Double prix;
}
