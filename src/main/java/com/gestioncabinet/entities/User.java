package com.gestioncabinet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data  @AllArgsConstructor @NoArgsConstructor
public class User implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String Nomprenom;
    private String password;
    private String phone;
    @Column(unique = true)
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles=new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER)
    private List<RendezVous> mesRDVs=new ArrayList<>();
}
