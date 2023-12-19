package com.gestioncabinet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@ToString
public class RendezVous {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateRDV;
    private Integer heureRDV;
    @OneToOne
    private Soin consultation;
    @ManyToOne
    private  User user;
}
